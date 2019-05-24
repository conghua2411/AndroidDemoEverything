package com.example.leclevietnam.demoeverything.customOptionsDialog.dialog

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.leclevietnam.demoeverything.R

class OptionsAdapter(listOpt: List<String>) : androidx.recyclerview.widget.RecyclerView.Adapter<OptionsAdapter.OptionViewHolder>() {

    private val listOptions = listOpt
    private var mListener:OptionsDialogListener? = null

    constructor(listOpt: List<String>, listener: OptionsDialogListener) : this(listOpt) {
        mListener = listener
    }

    fun setListener(listener: OptionsDialogListener) {
        mListener = listener
    }

    inner class OptionViewHolder(val view: View): androidx.recyclerview.widget.RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): OptionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_option_dialog, parent, false)
        return OptionViewHolder(view)
    }

    override fun getItemCount(): Int = listOptions.size

    override fun onBindViewHolder(holder: OptionViewHolder, position: Int) {
        holder.view.findViewById<TextView>(R.id.txt_item_option).setOnClickListener {
            mListener?.onItemClick(listOptions[position])
        }
    }

    interface OptionsDialogListener {
        fun onItemClick(name: String)
    }
}