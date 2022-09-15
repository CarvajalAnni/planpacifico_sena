package com.example.planpacifico.ui.modules.water

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.RadioGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.planpacifico.R
import com.example.planpacifico.core.Constants
import com.example.planpacifico.core.hide
import com.example.planpacifico.core.show
import com.example.planpacifico.data.localdb.AppDatabase
import com.example.planpacifico.data.models.entities.AnalysisEntity
import com.example.planpacifico.data.models.entities.ParameterEntity
import com.example.planpacifico.data.models.entities.SamplesEntity
import com.example.planpacifico.data.rest.RetrofitClient
import com.example.planpacifico.databinding.AddSamplesBinding
import com.example.planpacifico.databinding.FragmentAnalyzeWaterBinding
import com.example.planpacifico.domain.analysis.AnalysisRepoImpl
import com.example.planpacifico.domain.measures.MeasuresRepoImpl
import com.example.planpacifico.domain.parameters.ParametersRepo
import com.example.planpacifico.domain.parameters.ParametersRepoImpl
import com.example.planpacifico.domain.samples.SamplesRepoImpl
import com.example.planpacifico.presentation.*
import com.example.planpacifico.ui.modules.water.adapter.WaterSamplesAdapter
import com.fabricaswsenactpi.com.construyendopacifico.core.Result
import com.fabricaswsenactpi.com.construyendopacifico.data.models.room.MeasureEntity
import com.fabricaswsenactpi.com.construyendopacifico.data.models.web.ParameterResponse
import com.fabricaswsenactpi.com.construyendopacifico.data.models.web.analysis.AnalysisResponse
import com.fabricaswsenactpi.com.construyendopacifico.data.models.web.sample.SampleBody
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collect
import java.text.SimpleDateFormat
import java.util.*


class AnalyzeWaterFragment : Fragment(R.layout.fragment_analyze_water),
    RadioGroup.OnCheckedChangeListener {
    private lateinit var binding: FragmentAnalyzeWaterBinding
    private val args by navArgs<AnalyzeWaterFragmentArgs>()

    private lateinit var parametersList: List<ParameterEntity>
    private lateinit var analysis: AnalysisEntity
    private lateinit var sample: SamplesEntity


    private var waterType: Int = 0
    private lateinit var selectedParameter: ParameterEntity
    private val acceptableSamplesList = listOf("Aceptable", "No Aceptable")
    private var existSample = false


    private val viewModelAnalysis by viewModels<AnalysisViewModel> {
        AnalysisViewModelFactory(
            AnalysisRepoImpl(AppDatabase.getDatabase(requireContext()).AnalysisDao())
        )


    }
    private val viewModelSample by viewModels<SamplesViewModel> {
        SamplesViewModelFactory(
            SamplesRepoImpl(
                AppDatabase.getDatabase(requireContext()).SamplesDao()
            )
        )


    }
    private val viewModelMeasure by viewModels<MeasuresViewModel> {
        MeasuresViewModelFactory(
            MeasuresRepoImpl(
                AppDatabase.getDatabase(requireContext()).MeasuresDao()
            )
        )


    }
    private val viewModelParameters by viewModels<ParametersViewModel> {
        ParametersViewModelFactory(
            ParametersRepoImpl(
                AppDatabase.getDatabase(requireContext()).ParametersDao(),
                RetrofitClient.webService
            )
        )
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAnalyzeWaterBinding.bind(view)

        referenceData()
        obtainAnalysis()
        clicks()
        obtainParameters()

    }

    private fun clicks() {
        binding.imgBtnBack.setOnClickListener { findNavController().popBackStack() }
        binding.radioGroup.setOnCheckedChangeListener(this)
        binding.txtFParameter.setOnItemClickListener { parent, view, position, id ->
            selectedParameter = parametersList[position]
        }
        binding.btnSaveAnalysis.setOnClickListener { validateSaveAnalysis() }
        binding.btnAddSample.setOnClickListener { validateSample() }


    }

    private fun validateSample() {
        val results = arrayOf(
            validateParameter(),
        )
        if (false in results) {
            return
        }
        addSample()
    }

    private fun addSample() {

        val dialogBinding = AddSamplesBinding.inflate(layoutInflater)

        val dialog = AlertDialog.Builder(requireContext()).apply {
            setView(dialogBinding.root)
        }.create()

        dialogBinding.txtTitleParameter.text =
            "Ingrese el dato de la muestra de ${binding.txtFParameter.text.toString()} :"
        if (selectedParameter.id_parameter == 2) {

            val adapter = ArrayAdapter(requireContext(), R.layout.list_item, acceptableSamplesList)
            dialogBinding.autocompleteSelectSamplesAcceptable.setAdapter(adapter)

            dialogBinding.txtILSampleAcceptable.show()
            dialogBinding.txtILSample.hide()
        }
        dialogBinding.imgClose.setOnClickListener { dialog.dismiss() }
        dialogBinding.btnSample.setOnClickListener {
            var valueSample = ""
            if (!dialogBinding.edtSample.text.toString().isNullOrEmpty()) {
                valueSample = dialogBinding.edtSample.text.toString()
            } else if (!dialogBinding.autocompleteSelectSamplesAcceptable.text.toString()
                    .isNullOrEmpty()
            ) {
                valueSample = dialogBinding.autocompleteSelectSamplesAcceptable.text.toString()
            }
            val results = if (valueSample.isNullOrEmpty()) {
                dialogBinding.txtILSample.error = "Este campo no puede estar vacio"
                dialogBinding.txtILSampleAcceptable.error = "Este campo no puede estar vacio"
                false
            } else {
                dialogBinding.txtILSample.error = null
                dialogBinding.txtILSampleAcceptable.error = null
                true
            }
            if (!results) {
                return@setOnClickListener
            }
            getExistingSample(valueSample, dialogBinding, dialog)
        }
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawableResource(R.color.transparent)
        dialog.show()


    }

    private fun getExistingSample(
        value: String,
        dialog: AddSamplesBinding,
        alertDialog: AlertDialog
    ) {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModelSample.getExistingSample(
                    analysis.id_analysis,
                    selectedParameter.id_parameter
                ).collect {
                    when (it) {
                        is Result.Loading -> {}
                        is Result.Success -> {
                            if (it.data == null) {
                                saveSample(value, dialog, alertDialog)
                            } else {
                                if (it.data.parameter_id == 2) {
                                    alertDialog.dismiss()
                                    Snackbar.make(
                                        binding.root,
                                        "Este parametro solo se puede registrar una vez",
                                        Snackbar.LENGTH_SHORT
                                    ).show()
                                } else {
                                    sample = it.data
                                    existSample = true
                                    saveMeasure(it.data.id_sample, value, alertDialog)
                                }
                            }
                        }
                        is Result.Failure -> {
                            Log.e(
                                "getExistingSample: ",
                                "Error al buscar una muestra ${it.exception.message}"
                            )
                        }
                    }
                }
            }
        }
    }

    private fun saveSample(value: String, dialog: AddSamplesBinding, alertDialog: AlertDialog) {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModelSample.saveSample(
                    SamplesEntity(
                        0,
                        selectedParameter.id_parameter,
                        analysis.id_analysis,
                        value,
                    )
                ).collect { sample ->
                    when (sample) {
                        is Result.Loading -> {
                            dialog.progressBar?.show()
                            dialog.btnSample?.hide()
                        }
                        is Result.Success -> {
                            dialog.btnSample?.show()
                            dialog.progressBar?.hide()
                            alertDialog.dismiss()
                            obtainLastSample(value)
                        }
                        is Result.Failure -> {
                            dialog.btnSample?.show()
                            dialog.progressBar?.hide()
                            Snackbar.make(
                                binding.root,
                                "Error al registrarse",
                                Snackbar.LENGTH_SHORT
                            ).show()
                            Log.e("Error", "sendUser: ${sample.exception}")
                        }
                    }
                }
            }
        }
    }

    private fun obtainLastSample(value: String) {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModelSample.getLastSample().collect {
                    when (it) {
                        is Result.Loading -> {}
                        is Result.Success -> {
                            saveMeasure(it.data.id_sample, value, null)
                        }
                        is Result.Failure -> {
                            Log.e(
                                "obtainLastSample: ",
                                "Error al obtener la ultima muestra ${it.exception.message}"
                            )
                        }
                    }
                }
            }
        }
    }

    private fun saveMeasure(sample_id: Int, value: String, alertDialog: AlertDialog?) {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModelMeasure.saveMeasure(
                    MeasureEntity(
                        0,
                        sample_id,
                        value,
                        SimpleDateFormat("dd/MM/yyyy KK:mma").format(Date()).toString()
                    )
                ).collect {
                    when (it) {
                        is Result.Loading -> {}
                        is Result.Success -> {
                            Snackbar.make(
                                binding.root,
                                "Se guardo correctamente las medidas",
                                Snackbar.LENGTH_SHORT
                            ).show()
                            alertDialog?.dismiss()
                            if (existSample) {
                                obtainMeasures(sample_id)
                            } else {
                                obtainSamples()
                            }
                        }
                        is Result.Failure -> {
                            Log.e(
                                "obtainLastSample: ",
                                "saveMeasure ${it.exception.message}"
                            )
                        }
                    }
                }
            }
        }
    }

    private fun obtainMeasures(sample_id: Int) {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModelMeasure.getMeasuresBySample(sample_id).collect {
                    when (it) {
                        is Result.Loading -> {}
                        is Result.Success -> {
                            var totalValues = 0
                            it.data.forEach { m ->
                                totalValues += m.value.toInt()
                            }
                            updateSampleAverage(it.data.size,totalValues)
                        }
                        is Result.Failure -> {
                            Log.e(
                                "obtainMeasures: ",
                                "obtainMeasures ${it.exception.message}"
                            )
                        }
                    }
                }
            }
        }
    }

    private fun updateSampleAverage(totalMeasures : Int,totalValuesSum:Int) {
        val average = totalValuesSum / totalMeasures
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModelSample.updateSample(SamplesEntity(sample.id_sample,sample.parameter_id,sample.analysis_id,average.toString())).collect{
                    when(it){
                        is Result.Loading->{}
                        is Result.Success->{
                            obtainSamples()
                        }
                        is Result.Failure->{
                            Log.e(
                                "updateSampleAverage: ",
                                "updateSampleAverage ${it.exception.message}"
                            )
                        }
                    }
            }
        }
    }
}

private fun obtainSamples() {
    viewLifecycleOwner.lifecycleScope.launchWhenCreated {
        viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModelSample.getSampleParametersAndMeasures(analysis.id_analysis).collect {
                when (it) {
                    is Result.Loading -> {
                        binding.progressBar?.show()
                        binding.screen?.hide()
                    }
                    is Result.Success -> {
                        binding.progressBar?.hide()
                        binding.screen?.show()
                        if (it.data == null) {
                            binding.noSamples.show()
                            binding.rvSamples.hide()
                        } else {
                            binding.noSamples.hide()
                            binding.rvSamples.show()
                        }
                        val adapter = WaterSamplesAdapter(it.data, requireContext())
                        binding.rvSamples.layoutManager = LinearLayoutManager(requireContext())
                        binding.rvSamples.adapter = adapter

                    }
                    is Result.Failure -> {
                        binding.progressBar?.hide()
                        binding.screen?.show()
                        Snackbar.make(
                            binding.root,
                            "Error al obtener los datos",
                            Snackbar.LENGTH_SHORT
                        ).show()
                        Log.e("Error", "obtainAbilities: ${it.exception.message}")
                    }
                }
            }
        }
    }
}

private fun validateParameter(): Boolean {
    return if (binding.txtFParameter.text.toString().isNullOrEmpty()) {
        binding.txtFParameter.error = "Este campo no puede estar vacio"
        false
    } else {
        binding.txtFParameter.error = null
        true
    }
}

private fun validateSaveAnalysis() {
    val results = arrayOf(validateSup(), validateSub(), validateCap())
    if (false in results)
        return

    if (waterType == 0) {
        binding.cbNaturalWater.error = "Debe estar minimo un campo seleccionado"
        return
    }
    saveAnalysis()
}

private fun validateCap(): Boolean {
    return if (binding.txtCatchemntType.text.toString().isNullOrEmpty()) {
        binding.txtILCatchmentType.error = "Este campo es obligatorio"
        false
    } else {
        binding.txtILCatchmentType.error = null
        true
    }
}

private fun validateSub(): Boolean {
    return if (binding.txtUndergroundSources.text.toString().isNullOrEmpty()) {
        binding.txtILUnderGroundSources.error = "Este campo es obligatorio"
        false
    } else {
        binding.txtILUnderGroundSources.error = null
        true
    }
}

private fun validateSup(): Boolean {
    return if (binding.txtSurfaceSources.text.toString().isNullOrEmpty()) {
        binding.txtILSurfaceSources.error = "Este campo es obligatorio"
        false
    } else {
        binding.txtILSurfaceSources.error = null
        true
    }
}

private fun saveAnalysis() {
    val shared =
        requireActivity().getSharedPreferences(Constants.SHARED_USER, Context.MODE_PRIVATE)
    val idUser = shared.getInt("idUser", 0)
    viewLifecycleOwner.lifecycleScope.launchWhenCreated {
        viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModelAnalysis.saveAnalysis(
                AnalysisEntity(
                    0,
                    idUser,
                    args.ppmObject.id_population,
                    1,
                    waterType,
                    SimpleDateFormat("dd/mm/yyyy").format(Date()).toString(),
                    SimpleDateFormat("KK:mma").format(Date()).toString(),
                    "prueba",
                    binding.txtSurfaceSources.text.toString(),
                    binding.txtUndergroundSources.text.toString(),
                    binding.txtCatchemntType.text.toString(),

                    )
            ).collect {
                when (it) {
                    is Result.Loading -> {
                        enableCheckButtons(false)
                        binding.btnSaveAnalysis.hide()
                        binding.btnSaveProgressBar.show()
                    }
                    is Result.Success -> {
                        binding.screenNoAnalysis.hide()
                        binding.screenSamples.show()
                        binding.btnSaveProgressBar.hide()
                        findNavController().popBackStack()
                    }
                    is Result.Failure -> {
                        enableCheckButtons(true)
                        binding.btnSaveProgressBar.hide()
                        Snackbar.make(
                            binding.root,
                            "Error al guardar el analisis",
                            Snackbar.LENGTH_SHORT
                        ).show()
                        Log.e("Error", "save Analisis: ${it.exception.message}")
                    }
                }
            }
        }
    }
}

private fun obtainActualDate() {
    val date = SimpleDateFormat("MMM dd,yyyy KK:mma").format(Date())
    binding.txtVDate.text = date
}

private fun referenceData() {
    obtainActualDate()
    binding.txtVMunicipality.text = args.ppmObject.municipality_name
    binding.txtVPopulatedCenter.text = args.ppmObject.populated_center_name
}

private fun obtainAnalysis() {
    viewLifecycleOwner.lifecycleScope.launchWhenCreated {
        viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModelAnalysis.getOneAnalysis(args.ppmObject.id_population).collect {
                when (it) {
                    is Result.Loading -> {

                    }
                    is Result.Success -> {
                        Log.e("obtainAnalysis: ", it.toString())
                        if (it.data != null) {
                            binding.btnSaveAnalysis.hide()
                            binding.screenNoAnalysis.hide()
                            binding.screenSamples.show()
                            enableCheckButtons(false)
                            selectedCheckButton(it.data.water_type_id)
                            binding.txtVDate.text = "${it.data.date} ${it.data.hour}"
                            binding.txtUndergroundSources.setText(it.data.underground_sources)
                            binding.txtSurfaceSources.setText(it.data.surface_sources)
                            binding.txtCatchemntType.setText(it.data.catchment_type)
                            analysis = it.data
                            obtainSamples()
                        }
                    }
                    is Result.Failure -> {
                        binding.progressBar?.hide()
                        binding.screen?.show()
                        Snackbar.make(
                            binding.root,
                            "Error al obtener los datos",
                            Snackbar.LENGTH_SHORT
                        ).show()
                        Log.e("Error", "obtainAbilities: ${it.exception.message}")
                    }
                }
            }
        }
    }
}

private fun selectedCheckButton(waterId: Int) {
    when (waterId) {
        1 -> {
            binding.cbNaturalWater.isChecked = true
        }
        2 -> {
            binding.cbTreatedWater.isChecked = true
        }
        3 -> {
            binding.cbResidualWater.isChecked = true
        }
        4 -> {
            binding.cbOtherWater.isChecked = true
        }
    }
}

private fun enableCheckButtons(param: Boolean) {
    binding.cbNaturalWater.isEnabled = param
    binding.cbTreatedWater.isEnabled = param
    binding.cbResidualWater.isEnabled = param
    binding.cbOtherWater.isEnabled = param
    binding.txtILCatchmentType.isEnabled = param
    binding.txtILSurfaceSources.isEnabled = param
    binding.txtILUnderGroundSources.isEnabled = param
}

private fun obtainParameters() {
    viewLifecycleOwner.lifecycleScope.launchWhenCreated {
        viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModelParameters.getDbParameters().collect {
                when (it) {
                    is Result.Loading -> {
                        binding.progressBar?.show()
                        binding.screen?.hide()
                    }
                    is Result.Success -> {
                        binding.progressBar?.hide()
                        binding.screen?.show()
                        val parameters: MutableList<String> = mutableListOf()
                        parametersList = it.data
                        it.data.forEach {
                            parameters.add(it.parameter_name)
                        }
                        setUpParameters(parameters)
                    }
                    is Result.Failure -> {
                        binding.progressBar?.hide()
                        binding.screen?.show()
                        Snackbar.make(
                            binding.root,
                            "Error al obtener los datos",
                            Snackbar.LENGTH_SHORT
                        ).show()
                        Log.e("Error", "obtainAbilities: ${it.exception.message}")
                    }
                }
            }
        }
    }
}

private fun setUpParameters(parameters: MutableList<String>) {
    val adapter = ArrayAdapter(requireContext(), R.layout.list_item, parameters)
    binding.txtFParameter.setAdapter(adapter)
}

override fun onCheckedChanged(p0: RadioGroup?, checkedId: Int) {
    when (checkedId) {
        R.id.cbNaturalWater -> {
            waterType = 1
        }
        R.id.cbTreatedWater -> {
            waterType = 2
        }
        R.id.cbResidualWater -> {
            waterType = 3
        }
        R.id.cbOtherWater -> {
            waterType = 4
        }
    }
}
}