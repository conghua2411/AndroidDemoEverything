package com.example.leclevietnam.demoeverything.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.leclevietnam.demoeverything.databinding.PagingItemBinding
import com.example.leclevietnam.demoeverything.room.Product

class PRecyclerAdapter(private val list: MutableList<Product>) : RecyclerView.Adapter<PRecyclerAdapter.Holder2>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder2 {
        val layoutInflater = LayoutInflater.from(parent.context)

        val pagingItemBinding = PagingItemBinding.inflate(layoutInflater, parent, false)

        return Holder2(pagingItemBinding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: Holder2, position: Int) {
        holder.bind(list[position])
    }

    fun addAll(listProduct: List<Product>) {
        list.clear()
        list.addAll(listProduct)
        notifyDataSetChanged()
    }

    inner class Holder2(private var binding: PagingItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product?) {

            binding.txtPagingItemText.text = product.toString()
        }
    }
}