package com.example.planpacifico.ui.zones.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.planpacifico.data.models.entities.EthnicGroupEntity
import com.example.planpacifico.databinding.ItemEthnicGroupsBinding
import com.fabricaswsenactpi.com.construyendopacifico.data.models.web.EthnicGroupResponse

class EthnicGroupAdapter(private val ethnicGroups:List<EthnicGroupEntity>):RecyclerView.Adapter<EthnicGroupAdapter.EthnicGroupViewHolder>() {
    private val ethnicArray = mutableListOf<EthnicGroupEntity>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EthnicGroupViewHolder {
        val binding = ItemEthnicGroupsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return EthnicGroupViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EthnicGroupViewHolder, position: Int) {
        holder.bind(ethnicGroups[position])
    }

    override fun getItemCount(): Int = ethnicGroups.size

    inner class EthnicGroupViewHolder(private val binding:ItemEthnicGroupsBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(ethnicGroups: EthnicGroupEntity){
            ethnicArray.add(ethnicGroups)
            binding.txtName.text = ethnicGroups.ethnic_group_name
            binding.imgClose.setOnClickListener {
                updateData(ethnicGroups)
            }
        }
    }
    fun updateData(item:EthnicGroupEntity){
        ethnicArray.remove(item)
    }
    fun updateInfo():MutableList<EthnicGroupEntity>{
        return ethnicArray
    }

}
