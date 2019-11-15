package com.example.leclevietnam.demoeverything.deepLinkDemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.leclevietnam.demoeverything.R
import kotlinx.android.synthetic.main.activity_deep_link_demo.*

class DeepLinkDemo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deep_link_demo)

        tvDeepLinkData.text = intent.getStringExtra("type")
    }
}
