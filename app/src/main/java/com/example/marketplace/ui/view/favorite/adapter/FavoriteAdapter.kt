package com.example.marketplace.ui.view.favorite.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.marketplace.data.db.model.Product
import com.example.marketplace.databinding.FavoriteProductItemViewBinding

class FavoriteAdapter(val onItemClick: (Product) -> Unit = {}) :
    ListAdapter<Product, FavoriteAdapter.ViewHolder>(ProductDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.setOnClickListener {
            onItemClick(item)
        }
//        holder.itemView.findViewById<ImageView>(R.id.favorite).setOnClickListener {
//            onClickListener.onFavoriteClick(item.id)
//        }
//        holder.itemView.findViewById<Button>(R.id.moveToCartBtn).setOnClickListener {
//            onClickListener.onCardClick(item.id)
//        }
        holder.bind(item)
    }

    fun getIdByPosition(position: Int): Int{
        return getItem(position).id
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(private val binding: FavoriteProductItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Product) {
//            binding.product = item
            binding.priceTv.paintFlags = binding.priceTv.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FavoriteProductItemViewBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class ProductDiffCallback : DiffUtil.ItemCallback<Product>() {

    override fun areItemsTheSame(oldItem: Product, newItem: Product) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Product, newItem: Product) = oldItem == newItem
}