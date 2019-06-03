package com.example.leclevietnam.demoeverything.extendBottomSheet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import com.example.leclevietnam.demoeverything.R
import com.google.android.material.bottomsheet.BottomSheetBehavior

class BottomSheetActivity : AppCompatActivity() {

    private lateinit var llExtendBottomSheet: LinearLayout

    private lateinit var llExtendView: LinearLayout

    private lateinit var llContent: LinearLayout

    private lateinit var mBottomSheetBehavior: BottomSheetBehavior<LinearLayout>

    private lateinit var mBottomCallbask: BottomSheetBehavior.BottomSheetCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_sheet)

        llExtendBottomSheet = findViewById(R.id.llExtendBottomSheet)

        llExtendView = findViewById(R.id.llExtendView)

        llContent = findViewById(R.id.llContent)

        mBottomSheetBehavior = BottomSheetBehavior.from(llExtendBottomSheet)

        mBottomCallbask = object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(view: View, slideOffset: Float) {

            }

            override fun onStateChanged(view: View, state: Int) {

            }
        }

    }
}
