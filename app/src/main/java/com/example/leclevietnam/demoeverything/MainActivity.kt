package com.example.leclevietnam.demoeverything

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.example.leclevietnam.demoeverything.alarmRepeat.AlarmActivity
import com.example.leclevietnam.demoeverything.camera2.Camera2Activity
import com.example.leclevietnam.demoeverything.cameraDemo.CameraActivity
import com.example.leclevietnam.demoeverything.customEditTextNoteUnderLine.CustomNoteUnderLineActivity

class MainActivity : AppCompatActivity(), RecyclerDemoListAdapter.DemoListListener  {

    companion object {
        //Demo name
        private const val CUSTOM_EDIT_TEXT = "CUSTOM_EDIT_TEXT"
        private const val CUSTOM_OPTIONS_DIALOG = "CUSTOM_OPTIONS_DIALOG"
        private const val ALARM_REPEAT = "ALARM_REPEAT"
        private const val CAMERA_DEMO = "CAMERA_DEMO"
        private const val CAMERA2_DEMO = "CAMERA2_DEMO"
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewManager = LinearLayoutManager(this)

        val listDemo = arrayOf(CUSTOM_EDIT_TEXT, CUSTOM_OPTIONS_DIALOG, ALARM_REPEAT, CAMERA_DEMO, CAMERA2_DEMO)

        viewAdapter = RecyclerDemoListAdapter(listDemo, this)

        recyclerView = findViewById<RecyclerView>(R.id.recycler_list_demo).apply {
            setHasFixedSize(true)

            layoutManager = viewManager

            adapter = viewAdapter
        }

    }

    override fun onClick(demoName: String) {
        when (demoName) {
            CUSTOM_EDIT_TEXT -> {
                startActivity(Intent(this, CustomNoteUnderLineActivity::class.java))
            }
            CUSTOM_OPTIONS_DIALOG -> {

            }
            ALARM_REPEAT -> {
                startActivity( Intent(this, AlarmActivity::class.java))
            }
            CAMERA_DEMO -> {
                startActivity(Intent(this, CameraActivity::class.java))
            }
            CAMERA2_DEMO -> {
                startActivity(Intent(this, Camera2Activity::class.java))
            }
            else -> {
                Log.d("onClick_main", "demo name : $demoName")
            }
        }
    }
}
