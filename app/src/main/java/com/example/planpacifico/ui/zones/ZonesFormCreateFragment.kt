package com.example.planpacifico.ui.zones

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.location.LocationManager
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.util.Base64
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.getSystemService
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.planpacifico.R
import com.example.planpacifico.data.localdb.AppDatabase
import com.example.planpacifico.data.models.entities.*
import com.example.planpacifico.data.rest.RetrofitClient
import com.example.planpacifico.databinding.FragmentZonesFormCreateBinding
import com.example.planpacifico.domain.departments.DepartmentsRepoImpl
import com.example.planpacifico.domain.ethnicGroups.EthnicGroupRepoImpl
import com.example.planpacifico.domain.municipalities.MunicipalitiesRepoImpl
import com.example.planpacifico.domain.populatedCenters.PopulatedCentersRepoImpl
import com.example.planpacifico.domain.populations.PopulationsRepoImpl
import com.example.planpacifico.presentation.*
import com.example.planpacifico.ui.zones.adapter.EthnicGroupAdapter
import com.fabricaswsenactpi.com.construyendopacifico.core.Result
import com.fabricaswsenactpi.com.construyendopacifico.data.models.room.DepartmentEntity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import java.io.ByteArrayOutputStream

class ZonesFormCreateFragment : Fragment(R.layout.fragment_zones_form_create) {
    private lateinit var binding: FragmentZonesFormCreateBinding
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private var bitmap: Bitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888)
    private val resultRegister =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                val imageBitmap = it?.data?.extras?.get("data") as Bitmap
                bitmap = imageBitmap
                binding.idImageZone.setImageBitmap(bitmap)
            }
        }

    private lateinit var departmentList: List<DepartmentEntity>
    private lateinit var ethnicGroupList: List<EthnicGroupEntity>
    private lateinit var municipalitiesList: List<MunicipalitiesEntity>
    private lateinit var populatedCenterList: List<PopulatedCentersEntity>
    private var ethnicInfo = mutableListOf<EthnicGroupEntity>()
    private lateinit var selectedEthnicGroup: EthnicGroupEntity
    private lateinit var selectedPopulatedGroup: PopulatedCentersEntity


    private val viewModelEthnicGroup by viewModels<EthnicGroupsViewModel> {
        EthnicGroupsViewModelFactory(
            EthnicGroupRepoImpl(
                AppDatabase.getDatabase(requireContext()).EthnicGroupsDao(),
                RetrofitClient.webService
            )
        )
    }
    private val viewModelDepartment by viewModels<DepartmentsViewModel> {
        DepartmentsViewModelFactory(
            DepartmentsRepoImpl(
                AppDatabase.getDatabase(requireContext()).DepartmentsDao(),
                RetrofitClient.webService
            )
        )
    }
    private val viewModelMunicipality by viewModels<MunicipalitiesViewModel> {
        MunicipalitiesViewModelFactory(
            MunicipalitiesRepoImpl(
                AppDatabase.getDatabase(requireContext()).MunicipalitiesDao(),
                RetrofitClient.webService
            )
        )
    }
    private val viewModelPopulatedCenter by viewModels<PopulatedCentersViewModel> {
        PopulatedCentersViewModelFactory(
            PopulatedCentersRepoImpl(
                AppDatabase.getDatabase(requireContext()).PopulatedCentersDao(),
                RetrofitClient.webService
            )
        )
    }
    private val viewModelPopulation by viewModels<PopulationsViewModel> {
        PopulationsViewModelFactory(
            PopulationsRepoImpl(
                AppDatabase.getDatabase(requireContext()).PopulationsDao(),
                RetrofitClient.webService
            )
        )
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentZonesFormCreateBinding.bind(view)

        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireActivity())

        setUpCoordinates()
        obtainDepartments()
        obtainEthnicGroup()
        clicks()

    }

    private fun clicks() {
        binding.btnBack.setOnClickListener { findNavController().popBackStack() }
        binding.btnImgCamera.setOnClickListener {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            try {
                resultRegister.launch(takePictureIntent)
            } catch (e: Exception) {
                Snackbar.make(
                    binding.root, "No se encontro ninguna app para abrir la camara",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
        binding.btnUpdateCoordinates.setOnClickListener { setUpCoordinates() }
        binding.autocompleteSelectDepartments.setOnItemClickListener { parent, view, position, id ->
            obtainMunicipalities(
                position
            )
        }
        binding.autocompleteSelectMunicipalities.setOnItemClickListener { parent, view, position, id ->
            obtainPopulatedCenters(
                position
            )
        }
        binding.autocompleteSelectEthnicGroup.setOnItemClickListener { parent, view, position, id ->
            selectedEthnicGroup = ethnicGroupList[position]

            if (ethnicInfo.contains(ethnicGroupList[position])) {
                Snackbar.make(
                    binding.root,
                    "Este grupo etnico ya se encuentra agregado",
                    Snackbar.LENGTH_SHORT
                ).show()
            } else {
                ethnicInfo.add(ethnicGroupList[position])
            }
            binding.rvEthnicGroup.layoutManager = LinearLayoutManager(
                requireContext(),
                RecyclerView.HORIZONTAL, false
            )
            binding.rvEthnicGroup.adapter = EthnicGroupAdapter(ethnicInfo)
        }
        binding.imgUpdateEthnic.setOnClickListener {
            val data = (binding.rvEthnicGroup.adapter as EthnicGroupAdapter).updateInfo()
            ethnicInfo = data
            binding.rvEthnicGroup.layoutManager =
                LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            binding.rvEthnicGroup.adapter = EthnicGroupAdapter(ethnicInfo)
        }
        binding.autocompleteSelectVeredas.setOnItemClickListener { parent, view, position, id ->
            selectedPopulatedGroup = populatedCenterList[position]
        }
        binding.btnGuardar.setOnClickListener { validate() }


    }

    private fun validate() {
        val results = arrayOf(
            validateDepartment(),
            validateMunicipality(),
            validatePopulatedCenter(),
            validateEthnicGroup(),
            validateInhabitantsNumber(),
        )
        if (false in results) {
            return
        }
        obtainPopulationById()
    }

    private fun obtainPopulationById() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModelPopulation.getPopulationByPopulatedCenter(selectedPopulatedGroup.id_populated_center).collect{
                    when (it) {
                        is Result.Loading -> {
                        }
                        is Result.Success -> {
                            if (it.data == null){
                                saveWebData()
                            }else{
                                Snackbar.make(binding.root,"Esta poblacion ya se encuentra agregada",Snackbar.LENGTH_SHORT).show()
                            }
                        }
                        is Result.Failure -> {
                            Log.e("Error", "obtainPopulationById: ${it.exception}")
                        }
                    }
            }
        }
    }
}

private fun saveWebData() {
    var begin = 0
    var ethnicFinal = ""
    ethnicInfo.forEach {
        if (begin == 0) {
            begin = 1
            ethnicFinal = it.id_ethnic_group.toString()
        } else {
            ethnicFinal = "$ethnicFinal,${it.id_ethnic_group}"
        }
    }
    val byteArrayOutputStream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
    val byteArray = byteArrayOutputStream.toByteArray()
    val base64 = Base64.encodeToString(byteArray, Base64.DEFAULT)
    viewLifecycleOwner.lifecycleScope.launchWhenCreated {
        viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModelPopulation.savePopulation(
                PopulationsEntity(
                    0,
                    selectedPopulatedGroup.id_populated_center,
                    ethnicFinal,
                    binding.longitude.text.toString(),
                    binding.latitude.text.toString(),
                    "0",
                    base64,
                    binding.txtInhabitantsNumber.text.toString(),
                )
            ).collect {
                when (it) {
                    is Result.Loading -> {
                    }
                    is Result.Success -> {
                        Log.e("saveWebData: ", it.data.toString())
                        findNavController().popBackStack()
                    }
                    is Result.Failure -> {
                        Snackbar.make(
                            binding.root,
                            "Error al registrarse",
                            Snackbar.LENGTH_SHORT
                        ).show()
                        Log.e("Error", "sendUser: ${it.exception}")
                    }
                }
            }
        }
    }
}

private fun validateDepartment(): Boolean {
    return if (binding.autocompleteSelectDepartments!!.text.isNullOrEmpty()) {
        binding.autocompleteSelectDepartments!!.error = "Este campo es obligatorio"
        false
    } else {
        binding.autocompleteSelectDepartments!!.error = null
        true
    }
}

private fun validateMunicipality(): Boolean {
    return if (binding.autocompleteSelectMunicipalities.text.toString().isNullOrEmpty()) {
        binding.autocompleteSelectMunicipalities.error = "Este campo es obligatorio"
        false
    } else {
        binding.autocompleteSelectMunicipalities.error = null
        true
    }
}

private fun validatePopulatedCenter(): Boolean {
    return if (binding.autocompleteSelectVeredas.text.toString().isNullOrEmpty()) {
        binding.autocompleteSelectVeredas.error = "Este campo es obligatorio"
        false
    } else {
        binding.autocompleteSelectVeredas.error = null
        true
    }
}

private fun validateEthnicGroup(): Boolean {
    return if (binding.autocompleteSelectEthnicGroup.text.toString().isEmpty()) {
        binding.autocompleteSelectEthnicGroup.error = "Este campo es obligatorio"
        false
    } else {
        binding.autocompleteSelectEthnicGroup.error = null
        true
    }
}

private fun validateInhabitantsNumber(): Boolean {
    return if (binding.txtInhabitantsNumber?.text.toString().isEmpty()) {
        binding.txtILInhabitantsNumber!!.error = "Este campo es obligatorio"
        false
    } else {
        binding.txtILInhabitantsNumber!!.error = null
        true
    }
}

private fun setUpCoordinates() {
    if (ContextCompat.checkSelfPermission(
            requireContext(),
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION),
            1
        )
    } else {
        getLocations()
    }
}

@SuppressLint("MissingPermission")
private fun getLocations() {
    fusedLocationProviderClient.lastLocation?.addOnSuccessListener {
        if (it == null) {
            Toast.makeText(
                requireContext(),
                "no se puede obtener la locacion",
                Toast.LENGTH_SHORT
            ).show()
        } else it.apply {
            binding.latitude?.text = it.latitude.toString()
            binding.longitude?.text = it.longitude.toString()
        }
    }
}

override fun onRequestPermissionsResult(
    requestCode: Int,
    permissions: Array<out String>,
    grantResults: IntArray
) {
    if (requestCode == 1) {
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                Toast.makeText(requireContext(), "aprobado", Toast.LENGTH_SHORT).show()
                getLocations()
            } else {
                Toast.makeText(requireContext(), "denegado", Toast.LENGTH_SHORT).show()
            }
        }
    }
}


//    private fun setUpCoordinates() {
//        if (checkPermissions()) {
//            if (isLocationEnabled()) {
//                if (ActivityCompat.checkSelfPermission(
//                        requireContext(),
//                        Manifest.permission.ACCESS_FINE_LOCATION
//                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
//                        requireContext(),
//                        Manifest.permission.ACCESS_COARSE_LOCATION
//                    ) != PackageManager.PERMISSION_GRANTED
//                ) {
//                    requestPermission()
//                    return
//                }
//                fusedLocationProviderClient.lastLocation.addOnCompleteListener {
//                    if (it.result==null){
//                        Log.e("setUpCoordinates: ","null recived" )
//                    }else{
//                        binding.longitude.text = it.result.longitude.toString()
//                        binding.latitude.text = it.result.latitude.toString()
//                    }
//                }
//            } else {
//                Snackbar.make(binding.root,"Prender la ubicacion",Snackbar.LENGTH_SHORT).show()
//                val i = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
//                startActivity(i)
//            }
//        } else {
//            requestPermission()
//        }
//    }
//
//    private fun isLocationEnabled(): Boolean {
//        val locationManager = requireContext().getSystemService(Context.LOCATION_SERVICE)  as LocationManager
//        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
//    }
//
//    private fun requestPermission() {
//        ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION,android.Manifest.permission.ACCESS_FINE_LOCATION),100)
//    }
//
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        if (requestCode == 100){
//            if (grantResults.isNotEmpty() && grantResults[0]==PackageManager.PERMISSION_GRANTED){
//                setUpCoordinates()
//            }else{
//
//            }
//        }
//    }
//
//    private fun checkPermissions(): Boolean {
//        return ActivityCompat.checkSelfPermission(
//            requireContext(),
//            android.Manifest.permission.ACCESS_COARSE_LOCATION
//        ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
//            requireContext(),
//            android.Manifest.permission.ACCESS_FINE_LOCATION
//        ) == PackageManager.PERMISSION_GRANTED
//    }

private fun obtainDepartments() {
    viewLifecycleOwner.lifecycleScope.launchWhenCreated {
        viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModelDepartment.getDbDepartments().collect {
                when (it) {
                    is Result.Loading -> {}
                    is Result.Success -> {
                        val departments: MutableList<String> = mutableListOf()
                        departmentList = it.data
                        it.data.forEach {
                            departments.add(it.department_name)
                        }

                        setUpDepartments(departments)
                    }
                    is Result.Failure -> {
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

private fun obtainEthnicGroup() {
    viewLifecycleOwner.lifecycleScope.launchWhenCreated {
        viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModelEthnicGroup.getDbEthnicGroups().collect {
                when (it) {
                    is Result.Loading -> {
                    }
                    is Result.Success -> {

                        val ethnicGroups: MutableList<String> = mutableListOf()
                        ethnicGroupList = it.data
                        it.data.forEach { e ->
                            ethnicGroups.add(e.ethnic_group_name)
                        }

                        setUpEthnicGroup(ethnicGroups)
                    }
                    is Result.Failure -> {
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

private fun setUpDepartments(departments: MutableList<String>) {
    val adapter = ArrayAdapter(requireContext(), R.layout.list_item, departments)
    binding.autocompleteSelectDepartments.setAdapter(adapter)
}

private fun setUpEthnicGroup(ethnic: MutableList<String>) {
    val adapter = ArrayAdapter(requireContext(), R.layout.list_item, ethnic)
    binding.autocompleteSelectEthnicGroup.setAdapter(adapter)
}

private fun obtainMunicipalities(position: Int) {
    val idDepartment = departmentList[position]
    viewLifecycleOwner.lifecycleScope.launchWhenCreated {
        viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModelMunicipality.getDbDepartments(idDepartment.id_department)
                .collect {
                    when (it) {
                        is Result.Loading -> {}
                        is Result.Success -> {

                            val municipalities: MutableList<String> = mutableListOf()
                            municipalitiesList = it.data
                            it.data.forEach {
                                municipalities.add(it.municipality_name)
                            }

                            setUpMunicipalities(municipalities)
                        }
                        is Result.Failure -> {
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

private fun setUpMunicipalities(municipalities: MutableList<String>) {
    val adapter = ArrayAdapter(requireContext(), R.layout.list_item, municipalities)
    binding.autocompleteSelectMunicipalities?.setAdapter(adapter)
}

private fun obtainPopulatedCenters(position: Int) {
    val idMunicipality = municipalitiesList[position]
    viewLifecycleOwner.lifecycleScope.launchWhenCreated {
        viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModelPopulatedCenter.getDbPopulatedCenters(idMunicipality.id_municipality)
                .collect {
                    when (it) {
                        is Result.Loading -> {}
                        is Result.Success -> {
                            val populatedCenters: MutableList<String> = mutableListOf()
                            populatedCenterList = it.data
                            it.data.forEach {
                                populatedCenters.add(it.populated_center_name)
                            }

                            setUpPopulatedCenters(populatedCenters)
                        }
                        is Result.Failure -> {
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

private fun setUpPopulatedCenters(populatedCenters: MutableList<String>) {
    val adapter = ArrayAdapter(requireContext(), R.layout.list_item, populatedCenters)
    binding.autocompleteSelectVeredas?.setAdapter(adapter)
}


}