package com.example.leclevietnam.demoeverything.alarmRepeat

import android.content.Context
import android.content.Intent
import androidx.core.app.JobIntentService
import android.util.Log

class AlarmJobService : JobIntentService() {

    companion object {
        val JOB_ID = 1

        fun enqueueWork(context: Context, intent: Intent) {
            enqueueWork(context, AlarmJobService::class.java, JOB_ID, intent)
        }
    }

    override fun onHandleWork(intent: Intent) {
        Log.d("AlarmRepeat", "AlarmJobService receive alarm ---- ")

    }
}