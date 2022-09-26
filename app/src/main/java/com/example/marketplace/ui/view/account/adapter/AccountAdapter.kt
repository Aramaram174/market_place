package com.example.marketplace.ui.view.account.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.marketplace.data.db.model.AccountItem
import com.example.marketplace.data.db.model.Product
import com.example.marketplace.databinding.AccountAdapterItemViewBinding
import com.example.marketplace.ui.view.account.adapter.AccountAdapter.ViewHolder

class AccountAdapter(private val accountList: ArrayList<AccountItem>, val onItemClick: (Int) -> Unit = {}) : RecyclerView.Adapter<ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = accountList[position]
        holder.itemView.setOnClickListener {
            onItemClick(position)
        }
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder.from(parent)

    override fun getItemCount(): Int = accountList.size

    class ViewHolder(private val binding: AccountAdapterItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: AccountItem) {
            binding.item = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = AccountAdapterItemViewBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}