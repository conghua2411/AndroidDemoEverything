package com.example.leclevietnam.demoeverything.customOptionsDialog.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.leclevietnam.demoeverything.R

class CustomOptionsDialog(context: Context) : Dialog(context) {
    private val mContext: Context = context
    private lateinit var optionsList: androidx.recyclerview.widget.RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_options)

        optionsList = findViewById(R.id.recycler_list_options)
    }



}