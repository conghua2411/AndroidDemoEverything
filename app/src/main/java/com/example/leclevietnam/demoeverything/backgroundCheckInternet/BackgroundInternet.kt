package com.example.leclevietnam.demoeverything.backgroundCheckInternet

import android.content.Context
import android.net.wifi.SupplicantState
import android.net.wifi.WifiManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.leclevietnam.demoeverything.R
import kotlinx.android.synthetic.main.activity_background_internet.*

class BackgroundInternet : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_background_internet)

        btnStartBroadcast.setOnClickListener {
            // start broadcast
            Log.d("BackgroundInternet", "Start Broadcast")

            val wifiManager: WifiManager =
                    applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

            val wifiInfo = wifiManager.connectionInfo

            if (wifiInfo.supplicantState == SupplicantState.COMPLETED) {
                tvBroadcastStatus.text = wifiInfo.bssid
            }
        }
    }
}
