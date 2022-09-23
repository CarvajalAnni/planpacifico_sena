package com.example.planpacifico.ui.zones

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.planpacifico.R
import com.example.planpacifico.core.Constants.ID_ZONE
import com.example.planpacifico.core.hide
import com.example.planpacifico.core.show
import com.example.planpacifico.data.localdb.AppDatabase
import com.example.planpacifico.data.models.entities.PopulationAndPopulatedCenterAndMunicipality
import com.example.planpacifico.data.rest.RetrofitClient
import com.example.planpacifico.databinding.FragmentZonesMenuBinding
import com.example.planpacifico.domain.populations.PopulationsRepoImpl
import com.example.planpacifico.presentation.PopulationsViewModel
import com.example.planpacifico.presentation.PopulationsViewModelFactory
import com.fabricaswsenactpi.com.construyendopacifico.core.Result
import com.example.planpacifico.ui.zones.adapter.ZonesMenuAdapter
import com.google.android.material.snackbar.Snackbar


class ZonesMenuFragment : Fragment(R.layout.fragment_zones_menu) {
    private lateinit var binding : FragmentZonesMenuBinding
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
        binding = FragmentZonesMenuBinding.bind(view)

        clicks()
        obtainPopulations()
    }

    private fun obtainPopulations() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModelPopulation.getDbPopulations().collect {
                    when(it){
                        is Result.Loading -> {}
                        is Result.Success -> {
                            if (it.data.isNullOrEmpty()) {
                                binding.noData.show()
                                binding.rvPopulation.hide()
                            } else {
                                binding.noData.hide()
                                binding.rvPopulation.show()
                            }
                            val adapter =
                                ZonesMenuAdapter(it.data, viewModelPopulation, requireContext())
                            binding.rvPopulation.layoutManager =
                                GridLayoutManager(requireContext(), 3)
                            binding.rvPopulation.adapter = adapter
                        }
                        is Result.Failure -> {
                            binding.progressBar.hide()
                            Snackbar.make(binding.root,"Error del servidor ${it.exception}",
                                Snackbar.LENGTH_SHORT).show()
                            Log.e("Errorrrrrrrrrrrrrrr","${it.exception}")
                        }
                    }
                }
            }
        }
    }

    private fun clicks() {
        binding.btnRegisterNewZone.setOnClickListener { findNavController().navigate(R.id.action_zonesMenuFragment_to_zonesFormCreateFragment) }
    }

}