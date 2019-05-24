package com.example.leclevietnam.demoeverything.automateViewPager

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.leclevietnam.demoeverything.R
import com.example.leclevietnam.demoeverything.wrapper.ImageLoader

class AutoPagerAdapter(private val context: Context, private val listimageUrl : MutableList<String>) : PagerAdapter() {

    private var inflater: LayoutInflater? = null

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val view = inflater!!.inflate(R.layout.item_viewpager_image, container, false)

        val imageView = view.findViewById<ImageView>(R.id.img_item)

        ImageLoader.load(context, imageView, listimageUrl[position], R.drawable.ic_launcher_background)

        val txtCount = view.findViewById<TextView>(R.id.txtCount)

        txtCount.text = position.toString()

        container.addView(view)

        return view
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int = listimageUrl.size

    override fun getItemPosition(`object`: Any): Int = POSITION_NONE

    fun addAll(list: List<String>) {
        listimageUrl.clear()
        listimageUrl.addAll(list)
    }

    fun clear() {
        listimageUrl.clear()
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as RelativeLayout)
    }
}