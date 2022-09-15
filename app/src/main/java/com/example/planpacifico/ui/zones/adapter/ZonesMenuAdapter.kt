package com.example.planpacifico.ui.zones.adapter

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.planpacifico.R
import com.example.planpacifico.core.hide
import com.example.planpacifico.core.show
import com.example.planpacifico.data.models.entities.PopulationAndPopulatedCenterAndMunicipality
import com.example.planpacifico.data.models.entities.PopulationsEntity
import com.example.planpacifico.databinding.ConfirmDeleteZoneBinding
import com.example.planpacifico.databinding.ItemMenuZonesBinding
import com.example.planpacifico.presentation.PopulationsViewModel
import com.example.planpacifico.ui.zones.ZonesMenuFragmentDirections
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.fabricaswsenactpi.com.construyendopacifico.core.Result
import com.google.android.material.snackbar.Snackbar

class ZonesMenuAdapter(
    private val populationList: List<PopulationAndPopulatedCenterAndMunicipality>,
    private val viewModel: PopulationsViewModel,
    private val context: Context
) :
    RecyclerView.Adapter<ZonesMenuAdapter.ZonesMenuViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ZonesMenuViewHolder {
        val itemBinding =
            ItemMenuZonesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ZonesMenuViewHolder(itemBinding, viewModel,context)
    }

    override fun onBindViewHolder(holder: ZonesMenuViewHolder, position: Int) {
        holder.bind(populationList[position])
    }

    override fun getItemCount(): Int = populationList.size

    inner class ZonesMenuViewHolder(
        private val binding: ItemMenuZonesBinding,
        private val viewModel: PopulationsViewModel,
        private val context: Context
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(population: PopulationAndPopulatedCenterAndMunicipality) {
            binding.txtNameMunicipalities.text = population.municipality_name
            binding.txtVereda.text = population.populated_center_name
            binding.btnNextProcessZone.setOnClickListener {
                val action = ZonesMenuFragmentDirections.actionZonesMenuFragmentToMenuModelsFragment(population)
                Navigation.findNavController(binding.root).navigate(action)
            }
            binding.btnEditZone.setOnClickListener {
                val action = ZonesMenuFragmentDirections.actionZonesMenuFragmentToZonesFormUpdateFragment(population)
                Navigation.findNavController(binding.root).navigate(action)
            }
            binding.btnDeleteZone.setOnClickListener {
                val dialogBinding = ConfirmDeleteZoneBinding.inflate(LayoutInflater.from(context))

                val dialog = AlertDialog.Builder(context).apply {
                    setView(dialogBinding.root)
                }.create()
//        dialog.window?.setBackgroundDrawableResource(R.color.transparent)

                dialogBinding.textView8.text = "Desea eliminar esta zona?"
                dialogBinding.textView8.setTextColor(R.color.blueDarkSena)

                dialogBinding.idBtnCancelar.setOnClickListener { dialog.dismiss() }
                dialogBinding.idBtnEliminarZona.setOnClickListener {
                    val scope = CoroutineScope(Dispatchers.Main)
                    scope.launch {
                        viewModel.deletePopulation(PopulationsEntity(population.id_population,population.populated_center_id,population.ethnic_groups,population.length,population.latitude,population.altitude,population.photography,population.inhabitants_number)).collect {
                            when (it) {
                                is Result.Loading -> {
                                    binding.progressBar.show()
                                    binding.options.hide()
                                }
                                is Result.Success -> {
                                    binding.options.show()
                                    binding.progressBar.hide()
                                    Snackbar.make(
                                        binding.root,
                                        "Eliminado correctamente",
                                        Snackbar.LENGTH_SHORT
                                    ).show()
                                    Navigation.findNavController(binding.root).navigate(R.id.action_zonesMenuFragment_self)
                                }
                                is Result.Failure -> {
                                    binding.options.show()
                                    binding.progressBar.hide()
                                    Snackbar.make(
                                        binding.root,
                                        "Error al eliminar los datos",
                                        Snackbar.LENGTH_SHORT
                                    ).show()
                                    Log.e("Error", "bind: ${it.exception}")
                                }
                            }
                        }
                    }
                    dialog.dismiss()
                }
                dialog.setCancelable(false)
                dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
                dialog.show()
            }
        }

    }
}
