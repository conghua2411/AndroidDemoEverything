package com.example.leclevietnam.demoeverything.alarmRepeat

import android.annotation.TargetApi
import android.app.AlarmManager
import android.app.IntentService
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.app.NotificationCompat
import android.util.Log
import com.example.leclevietnam.demoeverything.R
import java.util.*

class AlarmService : IntentService("alarm") {
    override fun onHandleIntent(intent: Intent?) {

        Log.d("AlarmRepeat", "AlarmService receive alarm ---- ")
        // service

        setNotification(intent!!.getStringExtra("content"))
    }

    private fun setNotification(stringExtra: String?) {

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val notification = NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("alarm repeat")
                .setContentText(stringExtra + Date().toString())
                .setPriority(NotificationCompat.PRIORITY_MAX)

        notificationManager.notify(500, notification.build())

        createRepeatAlarm(stringExtra)
    }

    private fun createRepeatAlarm(stringExtra: String?) {

        Log.d("AlarmRepeat", "set repeat alarm lalalalala")
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val calendar = Calendar.getInstance()

        calendar.set(Calendar.SECOND, 0)
        calendar.add(Calendar.MINUTE, 1)

        val alarmIntent = Intent(this, AlarmReceiver::class.java)
                .putExtra("content", stringExtra)
                .let {
                    PendingIntent.getBroadcast(this, 100, it, PendingIntent.FLAG_UPDATE_CURRENT)
                }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            alarmManager.setExact(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    alarmIntent
            )
        }
    }
}