package com.example.marketplace.ui.view.filter.multiselect

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.marketplace.data.db.model.MultiselectAdapterItem
import com.example.marketplace.databinding.MultiselectAdapterItemBinding

class MultiselectFilterAdapter : RecyclerView.Adapter<MultiselectFilterAdapter.MultiselectViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<MultiselectAdapterItem>() {
        override fun areItemsTheSame(oldItem: MultiselectAdapterItem, newItem: MultiselectAdapterItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MultiselectAdapterItem, newItem: MultiselectAdapterItem): Boolean {
            return oldItem.isChecked == newItem.isChecked
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MultiselectViewHolder {
        return MultiselectViewHolder(
            MultiselectAdapterItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MultiselectViewHolder, position: Int) = holder.bind(differ.currentList[position])

    override fun getItemCount(): Int = differ.currentList.size

    class MultiselectViewHolder(private val binding: MultiselectAdapterItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(multiselectAdapterItem: MultiselectAdapterItem) {
            binding.ivSelected.visibility = if (multiselectAdapterItem.isChecked) View.VISIBLE else View.GONE
            binding.tvName.text = multiselectAdapterItem.name
            itemView.setOnClickListener {
                multiselectAdapterItem.isChecked = !multiselectAdapterItem.isChecked
                binding.ivSelected.visibility = if (multiselectAdapterItem.isChecked) View.VISIBLE else View.GONE
            }
        }
    }

    fun getSelected(): ArrayList<MultiselectAdapterItem> {
        val selected: ArrayList<MultiselectAdapterItem> = ArrayList()
        for (i in 0 until differ.currentList.size) {
            if (differ.currentList[i].isChecked) {
                selected.add(differ.currentList[i])
            }
        }
        return selected
    }

    fun resetSelected(){
        differ.currentList.filter{it.isChecked}.forEach {it.isChecked = false}
    }
}