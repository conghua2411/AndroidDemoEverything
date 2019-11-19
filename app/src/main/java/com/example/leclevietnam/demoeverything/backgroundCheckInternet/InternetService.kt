package com.example.leclevietnam.demoeverything.backgroundCheckInternet

import android.app.*
import android.app.NotificationManager.*
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.wifi.SupplicantState
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.JobIntentService
import androidx.core.app.NotificationCompat
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.google.api.client.util.DateTime
import kotlinx.android.synthetic.main.activity_background_internet.*
import org.json.JSONArray
import java.util.*

class InternetService : JobIntentService() {

    private val FOREGROUND_ID = 1338

    val isServiceRunning = false

    var delay: Int = 0

    lateinit var mgr: NotificationManager

    override fun onCreate() {
        super.onCreate()
        AndroidNetworking.initialize(applicationContext)
    }

    override fun onHandleWork(intent: Intent) {

        Log.d("InternetService", "isNetworkConnected : alo")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        performSync()
        startSyncThread()

        mgr = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val builder = buildForeground()

        startForeground(1, builder.build())

        return Service.START_STICKY
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun buildForeground(): NotificationCompat.Builder {
        val intent = Intent(this, BackgroundInternet::class.java)

        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        val notiBuilder = NotificationCompat.Builder(this, "alo1234")

        val mChannel = NotificationChannel("alo1234", "hello", IMPORTANCE_NONE)

        mgr.createNotificationChannel(mChannel)

        notiBuilder.setContentTitle("Prime Share is running")
                .setSmallIcon(android.R.drawable.stat_notify_sync_noanim)
                .setOngoing(true)
                .setAutoCancel(false)
                .setContentIntent(pendingIntent)

        return notiBuilder
    }

    private fun performSync() {
        if (isWifiConnected()) {
            Log.d("InternetService", "bssid: ${getWifiBssid()} --- now ${Date()}")
            delay = 60000

            AndroidNetworking.get("https://jsonplaceholder.typicode.com/posts")
                    .setPriority(Priority.LOW)
                    .build()
                    .getAsJSONArray(object : JSONArrayRequestListener {
                        override fun onResponse(response: JSONArray?) {
                            Log.d("InternetService", "network: success - ${response?.length()}")
                        }

                        override fun onError(anError: ANError?) {
                            Log.d("InternetService", "network: error - ${anError?.localizedMessage}")
                        }
                    })

        } else {
            Log.i("InternetService:", "Wifi IS NOT connected")
            delay = 1000
        }
    }

    private fun startSyncThread() {
        val handler: Handler = Handler()
        val delay: Long = 60000

        val runnable = object : Runnable {
            override fun run() {
                performSync()
                handler.postDelayed(this, delay)
            }
        }

        handler.postDelayed(runnable, delay)
    }

    private fun isWifiConnected(): Boolean {
        val connManager: ConnectivityManager =
                getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val mWifi = connManager.activeNetworkInfo

        return mWifi != null && mWifi.isConnected
    }

    private fun getWifiBssid(): String {
        // start broadcast
        Log.d("BackgroundInternet", "Start Broadcast")

        val wifiManager: WifiManager =
                applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

        val wifiInfo = wifiManager.connectionInfo

        if (wifiInfo.supplicantState == SupplicantState.COMPLETED) {
            return wifiInfo.bssid
        }

        return wifiInfo.supplicantState.name
    }
}