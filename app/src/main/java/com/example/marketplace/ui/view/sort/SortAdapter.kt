package com.example.marketplace.ui.view.sort

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marketplace.databinding.SortItemViewBinding
import com.example.marketplace.ui.view.sort.SortAdapter.ViewHolder

class SortAdapter(private val sortTypeList: List<SortType>, val onItemClick: (SortType) -> Unit = {}) : RecyclerView.Adapter<ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = sortTypeList[position]

        holder.itemView.setOnClickListener {
            onItemClick(item)
        }

        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun getItemCount(): Int = sortTypeList.size

    class ViewHolder(private val binding: SortItemViewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SortType) {
            binding.sortEnum = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = SortItemViewBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}