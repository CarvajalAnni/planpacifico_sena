package com.example.planpacifico.ui.modules.water.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.planpacifico.data.models.entities.relations.SampleParametersAndMeasures
import com.example.planpacifico.databinding.ItemAnalizedParametersWaterBinding
import com.fabricaswsenactpi.com.construyendopacifico.data.models.web.sample.SampleByAnalysisResponse

class WaterSamplesAdapter(private val sampleList: List<SampleParametersAndMeasures>,private val context:Context) :
    RecyclerView.Adapter<WaterSamplesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WaterSamplesViewHolder {
        val itemBinding = ItemAnalizedParametersWaterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return WaterSamplesViewHolder(itemBinding,context)
    }

    override fun onBindViewHolder(holder: WaterSamplesViewHolder, position: Int) {
        holder.bind(sampleList[position])
    }

    override fun getItemCount(): Int = sampleList.size
}

class WaterSamplesViewHolder(private val binding: ItemAnalizedParametersWaterBinding,private val context: Context) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(sample: SampleParametersAndMeasures) {
        var measures  = ""
        sample.measures.forEachIndexed { index, measure ->
            measures = "$measures${measure.value},   "
        }
        binding.txtAverage.text = sample.sampleParameter.average
        binding.txtParameter.text = sample.sampleParameter.parameter_name
        binding.txtLimit.text = "${sample.sampleParameter.operator} ${sample.sampleParameter.expected_value}"
        binding.txtMeasures.text = measures
        if (sample.sampleParameter.id_parameter !=2){
            when(sample.sampleParameter.operator){
                "<=" -> {if (sample.sampleParameter.expected_value.toFloat() <= sample.sampleParameter.average.toFloat()) binding.txtAverage.setTextColor(Color.RED)}
                "="  -> {if (sample.sampleParameter.expected_value.toFloat() == sample.sampleParameter.average.toFloat()) binding.txtAverage.setTextColor(Color.RED)}
                "<"  -> {if (sample.sampleParameter.expected_value.toFloat() <  sample.sampleParameter.average.toFloat())  binding.txtAverage.setTextColor(Color.RED)}
            }
        }else{
            if (sample.sampleParameter.expected_value != sample.sampleParameter.average) binding.txtAverage.setTextColor(Color.RED)
        }
    }
}
