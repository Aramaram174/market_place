package com.example.marketplace.ui.view.payments_method.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.marketplace.data.db.model.Payment
import com.example.marketplace.databinding.PaymentsMethodItemViewBinding

class PaymentsMethodAdapter : ListAdapter<Payment, PaymentsMethodAdapter.ViewHolder>(PaymentsMethodsDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(private val binding: PaymentsMethodItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Payment) {
            binding.payment = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = PaymentsMethodItemViewBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class PaymentsMethodsDiffCallback : DiffUtil.ItemCallback<Payment>() {

    override fun areItemsTheSame(oldItem: Payment, newItem: Payment) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Payment, newItem: Payment) = oldItem == newItem
}