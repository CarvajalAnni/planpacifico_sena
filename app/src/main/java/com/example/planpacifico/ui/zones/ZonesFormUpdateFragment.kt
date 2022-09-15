package com.example.planpacifico.ui.zones

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.planpacifico.R
import com.example.planpacifico.databinding.FragmentZonesFormUpdateBinding


class ZonesFormUpdateFragment : Fragment(R.layout.fragment_zones_form_update) {
    private lateinit var binding : FragmentZonesFormUpdateBinding
    private val args by navArgs<ZonesFormUpdateFragmentArgs>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentZonesFormUpdateBinding.bind(view)

        referenceData()
    }

    private fun referenceData() {
    }


}