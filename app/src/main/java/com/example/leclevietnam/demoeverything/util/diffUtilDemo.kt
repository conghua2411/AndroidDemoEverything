package com.example.leclevietnam.demoeverything.util

import androidx.recyclerview.widget.DiffUtil
import com.example.leclevietnam.demoeverything.room.Product

object DiffUtilDemo {
    fun PRODUCT_DIFF_CALLBACK(): DiffUtil.ItemCallback<Product> {
        return object : DiffUtil.ItemCallback<Product>() {
            override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem.name == newItem.name &&
                        oldItem.numbers == newItem.numbers &&
                        oldItem.price == newItem.price
            }
        }
    }
}