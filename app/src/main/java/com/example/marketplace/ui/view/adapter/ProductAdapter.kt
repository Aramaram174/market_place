package com.example.marketplace.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.marketplace.data.db.model.Product
import com.example.marketplace.databinding.ProductItemViewBinding

class ProductAdapter(val onItemClick: (Product) -> Unit = {}) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = differ.currentList[position]

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

    override fun getItemCount(): Int = differ.currentList.size

    fun getIdByPosition(position: Int): Int = differ.currentList[position].id

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder.from(parent)

    class ViewHolder private constructor(val binding: ProductItemViewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Product) {
            binding.product = item
//            binding.priceTv.paintFlags = binding.priceTv.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ProductItemViewBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}