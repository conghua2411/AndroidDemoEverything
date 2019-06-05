package com.example.leclevietnam.demoeverything.wrapper

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

object ImageLoader {
    fun load(context: Context, view: ImageView, url: String?, placeholder: Int) {
        Glide.with(context)
                .load(url)
                .placeholder(placeholder)
                .into(view)
    }
}