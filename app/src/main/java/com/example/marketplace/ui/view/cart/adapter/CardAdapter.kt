package com.example.marketplace.ui.view.cart.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.marketplace.data.db.model.Product
import com.example.marketplace.databinding.CartProductItemViewBinding

class CartAdapter(val onItemClick: (Product) -> Unit = {}) :
    ListAdapter<Product, CartAdapter.ViewHolder>(ProductDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.setOnClickListener {
            onItemClick(item)
        }
//        holder.itemView.findViewById<ImageView>(R.id.favorite).setOnClickListener {
//            onClickListener.onFavoriteClick(item.id)
//        }
//        holder.itemView.findViewById<ImageView>(R.id.cart).setOnClickListener {
//            onClickListener.onCartClick(item.id)
//        }
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder.from(parent)

    class ViewHolder private constructor(private val binding: CartProductItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Product) {
//            binding.product = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CartProductItemViewBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class ProductDiffCallback : DiffUtil.ItemCallback<Product>() {

    override fun areItemsTheSame(oldItem: Product, newItem: Product) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Product, newItem: Product) = oldItem == newItem
}