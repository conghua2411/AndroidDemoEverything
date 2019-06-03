package com.example.leclevietnam.demoeverything.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.leclevietnam.demoeverything.databinding.PagingItemBinding
import com.example.leclevietnam.demoeverything.room.Product
import com.example.leclevietnam.demoeverything.util.DiffUtilDemo

class PagingAdapter(private val itemListener: ItemListener) : PagedListAdapter<Product, RecyclerView.ViewHolder>(DiffUtilDemo.PRODUCT_DIFF_CALLBACK()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val pagingItemBinding = PagingItemBinding.inflate(layoutInflater, parent, false)

        return PagingViewHolder(pagingItemBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PagingViewHolder).bind(getItem(position))
    }

    inner class PagingViewHolder(private var binding: PagingItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product?) {

            binding.txtPagingItemText.text = product.toString()

            binding.root.setOnLongClickListener {
                itemListener.updateProduct(product as Product)
//                itemListener.deleteItem(product as Product)
                true
            }

        }
    }

    interface ItemListener {
        fun deleteItem(product: Product)
        fun updateProduct(product: Product)
    }
}