package com.example.planpacifico.ui.modules.architecture

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.planpacifico.R
import com.example.planpacifico.databinding.FragmentArchitectureModuleBinding


class ArchitectureModuleFragment : Fragment(R.layout.fragment_architecture_module) {

    private lateinit var binding :FragmentArchitectureModuleBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentArchitectureModuleBinding.bind(view)

        binding.btnBackToMenu.setOnClickListener { findNavController().popBackStack(R.id.menuModelsFragment,false) }

        binding.cardChar.setOnClickListener {
            findNavController().navigate(R.id.action_architectureModuleFragment_to_characterizationFragment)
        }

        binding.cardObser.setOnClickListener {
            findNavController().navigate(R.id.action_architectureModuleFragment_to_observatiionFragment)
        }

    }
}