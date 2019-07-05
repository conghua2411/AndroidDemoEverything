package com.example.leclevietnam.demoeverything

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.leclevietnam.demoeverything.SnapRecyclerDemo.SnapRecyclerActivity
import com.example.leclevietnam.demoeverything.alarmRepeat.AlarmActivity
import com.example.leclevietnam.demoeverything.annotationProcessing.AnnoProcessActivity
import com.example.leclevietnam.demoeverything.automateViewPager.AutoPagerActivity
import com.example.leclevietnam.demoeverything.camera2.Camera2Activity
import com.example.leclevietnam.demoeverything.cameraDemo.CameraActivity
import com.example.leclevietnam.demoeverything.cognito.CognitoActivity
import com.example.leclevietnam.demoeverything.constraintDemo.ConstraintActivity
import com.example.leclevietnam.demoeverything.customEditTextNoteUnderLine.CustomNoteUnderLineActivity
import com.example.leclevietnam.demoeverything.extendBottomSheet.BottomSheetActivity
import com.example.leclevietnam.demoeverything.javaDemo.JavaDemoActivity
import com.example.leclevietnam.demoeverything.koinDemo.KoinActivity
import com.example.leclevietnam.demoeverything.kotlinDemo.KotlinDemoActivity
import com.example.leclevietnam.demoeverything.mediaProjectionDemo.MediaProjectionACtivity
import com.example.leclevietnam.demoeverything.motionLayout.MotionLayoutActivity
import com.example.leclevietnam.demoeverything.paging.PagingActivity
import com.example.leclevietnam.demoeverything.recordSurfaceView.RecordSufaceActivity
import com.example.leclevietnam.demoeverything.retrofit.RetrofitActivity
import com.example.leclevietnam.demoeverything.rxJavaDemo.RxJavaActivity
import com.example.leclevietnam.demoeverything.socketDemo.SocketDemo
import com.example.leclevietnam.demoeverything.testDemo.TestActivity
import com.example.leclevietnam.demoeverything.zxcvbn4j.zxcvbn4jActivity

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
        private const val EXTEND_BOTTOM_SHEET = "EXTEND_BOTTOM_SHEET"
        private const val CONSTRAINT_DEMO = "CONSTRAINT_DEMO"
        private const val MOTION_LAYOUT = "MOTION_LAYOUT"
        private const val KOIN_DEMO = "KOIN_DEMO"
        private const val RX_JAVA_DEMO = "RX_JAVA_DEMO"
        private const val RETROFIT_DEMO = "RETROFIT_DEMO"
        private const val PAGING_DEMO = "PAGING_DEMO"
        private const val TEST_DEMO = "TEST_DEMO"
        private const val COGNITO_DEMO = "COGNITO_DEMO"
        private const val zxcvbn4j_DEMO = "zxcvbn4j_DEMO"
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
                KOTLIN_DEMO,
                EXTEND_BOTTOM_SHEET,
                CONSTRAINT_DEMO,
                MOTION_LAYOUT,
                KOIN_DEMO,
                RX_JAVA_DEMO,
                RETROFIT_DEMO,
                PAGING_DEMO,
                TEST_DEMO,
                COGNITO_DEMO,
                zxcvbn4j_DEMO)

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
            EXTEND_BOTTOM_SHEET -> {
                startActivity(Intent(this, BottomSheetActivity::class.java))
            }
            CONSTRAINT_DEMO -> {
                startActivity(Intent(this, ConstraintActivity::class.java))
            }
            MOTION_LAYOUT -> {
                startActivity(Intent(this, MotionLayoutActivity::class.java))
            }
            KOIN_DEMO -> {
                startActivity(Intent(this, KoinActivity::class.java))
            }
            RX_JAVA_DEMO -> {
                startActivity(Intent(this, RxJavaActivity::class.java))
            }
            RETROFIT_DEMO -> {
                startActivity(Intent(this, RetrofitActivity::class.java))
            }
            PAGING_DEMO -> {
                startActivity(Intent(this, PagingActivity::class.java))
            }
            TEST_DEMO -> {
                startActivity(Intent(this, TestActivity::class.java))
            }
            COGNITO_DEMO -> {
                startActivity(Intent(this, CognitoActivity::class.java))
            }
            zxcvbn4j_DEMO -> {
                startActivity(Intent(this, zxcvbn4jActivity::class.java))
            }
            else -> {
                Log.d("onClick_main", "demo name : $demoName")
            }
        }
    }
}
