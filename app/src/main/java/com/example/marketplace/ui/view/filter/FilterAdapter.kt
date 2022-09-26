package com.example.marketplace.ui.view.filter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.marketplace.data.db.model.Filter
import com.example.marketplace.databinding.FilterAdapterItemBinding
import com.example.marketplace.ui.view.filter.FilterAdapter.ViewHolder

class FilterAdapter(val onItemClick: (Filter) -> Unit = {}) :
    RecyclerView.Adapter<ViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<Filter>() {
        override fun areItemsTheSame(oldItem: Filter, newItem: Filter): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Filter, newItem: Filter): Boolean {
            return oldItem.value == newItem.value
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = differ.currentList[position]

        holder.itemView.setOnClickListener {
            onItemClick(item)
        }

        holder.bind(item)
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder.from(parent)

    class ViewHolder private constructor(private val binding: FilterAdapterItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Filter) {
            binding.filter = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FilterAdapterItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}