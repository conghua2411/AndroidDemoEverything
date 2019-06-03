package com.example.leclevietnam.demoeverything.motionLayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.leclevietnam.demoeverything.R

class MotionLayoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_motion_layout)

        val motionLayout = findViewById<MotionLayout>(R.id.motionLayout).apply {
            savedInstanceState
        }
        findViewById<RecyclerView>(R.id.recyclerview_front).apply {
            adapter = FrontPhotosAdapter()
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(this@MotionLayoutActivity)
        }

//        val debugMode = if (intent.getBooleanExtra("showPaths", false)) {
//            MotionLayout.DEBUG_SHOW_PATH
//        } else {
//            MotionLayout.DEBUG_SHOW_NONE
//        }
        motionLayout.setDebugMode(MotionLayout.DEBUG_SHOW_PATH)
    }
}
