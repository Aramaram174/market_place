package com.example.marketplace.ui.view.order.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.marketplace.R
import com.example.marketplace.data.db.model.Order
import com.example.marketplace.databinding.OrdersItemViewBinding

class OrdersAdapter (private val onClickListener: OrdersOnClickListener) :
    ListAdapter<Order, OrdersAdapter.ViewHolder>(ProductDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onItemClick(item.id)
        }
        holder.itemView.findViewById<Button>(R.id.moveToCartBtn).setOnClickListener {
            onClickListener.onCartClick(item.id)
        }
        holder.bind(item)
    }

    fun getIdByPosition(position: Int): Int{
        return getItem(position).id
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(private val binding: OrdersItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Order) {
//            binding.order = item
            binding.priceTv.paintFlags = binding.priceTv.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = OrdersItemViewBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class ProductDiffCallback : DiffUtil.ItemCallback<Order>() {

    override fun areItemsTheSame(oldItem: Order, newItem: Order) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Order, newItem: Order) = oldItem == newItem
}

interface OrdersOnClickListener {
    fun onItemClick(id: Int)
    fun onFavoriteClick(id: Int)
    fun onCartClick(id: Int)
}