package com.example.leclevietnam.demoeverything.backgroundCheckInternet

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo


class ConnectivityChangeReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.putExtra("isNetworkConnected", isConnected(context))
        context?.startService(Intent(context, InternetService::class.java))
    }

    private fun isConnected(context: Context?): Boolean {
        val connectivityManager: ConnectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo

        return networkInfo != null && networkInfo.isConnected
    }
}