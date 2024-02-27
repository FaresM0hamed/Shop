package com.devfares.shopappwithcleanarch.presentation.screens.productsScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.devfares.shopappwithcleanarch.databinding.ItemProductApiBinding
import com.devfares.shopappwithcleanarch.domain.models.ProductModel


class ProductsAdapter(
    private var products: List<ProductModel>,
) : RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>() {

    class ProductsViewHolder(
        private val binding: ItemProductApiBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: ProductModel) {
            binding.apply {
                productName.text = product.title
                productPrice.text = product.price.toString()
                productCategory.text = product.category
                Glide.with(itemView)
                    .load(product.image)
                    .into(productImage)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ProductsViewHolder {
        val binding =
            ItemProductApiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val item = products[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    fun updateList(newItems: List<ProductModel>) {
        val diffResult = DiffUtil.calculateDiff(ProductsDiffCallback(products, newItems))
        products = newItems
        diffResult.dispatchUpdatesTo(this)
    }

    inner class ProductsDiffCallback(
        private val oldList: List<ProductModel>,
        private val newList: List<ProductModel>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }
}