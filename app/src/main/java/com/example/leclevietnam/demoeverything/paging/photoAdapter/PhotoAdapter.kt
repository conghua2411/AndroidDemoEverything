package com.example.leclevietnam.demoeverything.paging.photoAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.leclevietnam.demoeverything.R
import com.example.leclevietnam.demoeverything.databinding.PagingPhotoItemBinding
import com.example.leclevietnam.demoeverything.paging.model.Photo
import com.example.leclevietnam.demoeverything.util.DiffUtilDemo
import com.example.leclevietnam.demoeverything.wrapper.ImageLoader

class PhotoAdapter : PagedListAdapter<Photo, RecyclerView.ViewHolder>(DiffUtilDemo.PHOTO_DIFF_CALLBACK()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val binding = PagingPhotoItemBinding.inflate(layoutInflater, parent, false)

        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PhotoViewHolder).bind(getItem(position))
    }

    inner class PhotoViewHolder(private val binding: PagingPhotoItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(photo: Photo?) {
            val stringBuilder = StringBuffer()

            stringBuilder.append(photo?.title)
            stringBuilder.append("\n album : ")
            stringBuilder.append(photo?.albumId)
            stringBuilder.append("\n id : ")
            stringBuilder.append(photo?.id)

            binding.txtPagingItemText.text = stringBuilder.toString()

            ImageLoader.load(binding.root.context, binding.imgItem, photo?.url, R.drawable.ic_launcher_background)
        }
    }
}