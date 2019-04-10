package com.example.leclevietnam.demoeverything.alarmRepeat

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        Log.d("AlarmRepeat", "broadcast receive alarm")
        // receiver
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            AlarmJobService.enqueueWork(context as Context, intent as Intent)
        } else {
            context!!.startService(Intent(context, AlarmService::class.java)
                    .putExtra("content", intent!!.getStringExtra("content")))
        }
    }
}