package com.example.planpacifico.ui.modules

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.planpacifico.R
import com.example.planpacifico.databinding.FragmentMenuModelsBinding
import com.example.planpacifico.databinding.FragmentZonesMenuBinding
import com.example.planpacifico.ui.zones.ZonesMenuFragmentDirections


class MenuModelsFragment : Fragment(R.layout.fragment_menu_models) {
    private lateinit var binding: FragmentMenuModelsBinding
    private val args by navArgs<MenuModelsFragmentArgs>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMenuModelsBinding.bind(view)

        clicks()
    }

    private fun clicks() {
        binding.btnBackToMenu.setOnClickListener { findNavController().popBackStack(R.id.zonesMenuFragment,false) }
        binding.btnCardWater.setOnClickListener {
            val action = MenuModelsFragmentDirections.actionMenuModelsFragmentToAnalyzeWaterFragment(args.ppmObject)
            findNavController().navigate(action)
        }
        binding.idCardArchitecture.setOnClickListener {
            findNavController().navigate(R.id.action_menuModelsFragment_to_architectureModuleFragment)
        }

    }

}