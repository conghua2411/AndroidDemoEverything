package com.example.leclevietnam.demoeverything

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import com.example.leclevietnam.demoeverything.SnapRecyclerDemo.SnapRecyclerActivity
import com.example.leclevietnam.demoeverything.alarmRepeat.AlarmActivity
import com.example.leclevietnam.demoeverything.annotationProcessing.AnnoProcessActivity
import com.example.leclevietnam.demoeverything.automateViewPager.AutoPagerActivity
import com.example.leclevietnam.demoeverything.camera2.Camera2Activity
import com.example.leclevietnam.demoeverything.cameraDemo.CameraActivity
import com.example.leclevietnam.demoeverything.customEditTextNoteUnderLine.CustomNoteUnderLineActivity
import com.example.leclevietnam.demoeverything.javaDemo.JavaDemoActivity
import com.example.leclevietnam.demoeverything.kotlinDemo.KotlinDemoActivity
import com.example.leclevietnam.demoeverything.mediaProjectionDemo.MediaProjectionACtivity
import com.example.leclevietnam.demoeverything.recordSurfaceView.RecordSufaceActivity
import com.example.leclevietnam.demoeverything.socketDemo.SocketDemo

class MainActivity : AppCompatActivity(), RecyclerDemoListAdapter.DemoListListener  {

    companion object {
        //Demo name
        private const val CUSTOM_EDIT_TEXT = "CUSTOM_EDIT_TEXT"
        private const val CUSTOM_OPTIONS_DIALOG = "CUSTOM_OPTIONS_DIALOG"
        private const val ALARM_REPEAT = "ALARM_REPEAT"
        private const val CAMERA_DEMO = "CAMERA_DEMO"
        private const val CAMERA2_DEMO = "CAMERA2_DEMO"
        private const val SNAP_RECYCLER_DEMO = "SNAP_RECYCLER_DEMO"
        private const val RECORD_SURFACE = "RECORD_SURFACE"
        private const val MEDIA_PROJECTION_DEMO = "MEDIA_PROJECTION_DEMO"
        private const val SOCKET_DEMO = "SOCKET_DEMO"
        private const val JAVA_DEMO = "JAVA_DEMO"
        private const val AUTO_PAGER = "AUTO_PAGER"
        private const val ANNO_PROCESS = "ANNO_PROCESS"
        private const val KOTLIN_DEMO = "KOTLIN_DEMO"
    }

    private lateinit var recyclerView: androidx.recyclerview.widget.RecyclerView
    private lateinit var viewAdapter: androidx.recyclerview.widget.RecyclerView.Adapter<*>
    private lateinit var viewManager: androidx.recyclerview.widget.RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewManager = androidx.recyclerview.widget.LinearLayoutManager(this)

        val listDemo = arrayOf(
                CUSTOM_EDIT_TEXT,
                CUSTOM_OPTIONS_DIALOG,
                ALARM_REPEAT,
                CAMERA_DEMO,
                CAMERA2_DEMO,
                SNAP_RECYCLER_DEMO,
                RECORD_SURFACE,
                MEDIA_PROJECTION_DEMO,
                SOCKET_DEMO,
                JAVA_DEMO,
                AUTO_PAGER,
                ANNO_PROCESS,
                KOTLIN_DEMO)

        viewAdapter = RecyclerDemoListAdapter(listDemo, this)

        recyclerView = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recycler_list_demo).apply {
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
            SNAP_RECYCLER_DEMO -> {
                startActivity(Intent(this, SnapRecyclerActivity::class.java))
            }
            RECORD_SURFACE -> {
                startActivity(Intent(this, RecordSufaceActivity::class.java))
            }
            MEDIA_PROJECTION_DEMO -> {
                startActivity(Intent(this, MediaProjectionACtivity::class.java))
            }
            SOCKET_DEMO -> {
                startActivity(Intent(this, SocketDemo::class.java))
            }
            JAVA_DEMO -> {
                startActivity(Intent(this, JavaDemoActivity::class.java))
            }
            AUTO_PAGER -> {
                startActivity(Intent(this, AutoPagerActivity::class.java))
            }
            ANNO_PROCESS -> {
                startActivity(Intent(this, AnnoProcessActivity::class.java))
            }
            KOTLIN_DEMO -> {
                startActivity(Intent(this, KotlinDemoActivity::class.java))
            }
            else -> {
                Log.d("onClick_main", "demo name : $demoName")
            }
        }
    }
}
