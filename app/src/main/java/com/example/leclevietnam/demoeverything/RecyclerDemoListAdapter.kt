package com.example.leclevietnam.demoeverything

import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class RecyclerDemoListAdapter(private val listDemo: Array<String>, listener: DemoListListener) : androidx.recyclerview.widget.RecyclerView.Adapter<RecyclerDemoListAdapter.ViewHolder>(){

    private val mListener: DemoListListener = listener

    class ViewHolder(val view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
        var textView: TextView = view.findViewById(R.id.tv_recycler_list_demo)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerDemoListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_demo_list_item, parent, false)

        return RecyclerDemoListAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listDemo.size
    }

    override fun onBindViewHolder(holder: RecyclerDemoListAdapter.ViewHolder, position: Int) {
        holder.textView.text = listDemo[position]

        holder.textView.setOnClickListener {
            mListener.onClick(listDemo[position])

        }
    }

    interface DemoListListener {
        fun onClick(demoName: String)
    }

}