package com.example.leclevietnam.demoeverything.alarmRepeat

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.leclevietnam.demoeverything.R
import java.util.*

class AlarmActivity : AppCompatActivity() {

    private lateinit var alarmManager: AlarmManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm)

        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        findViewById<Button>(R.id.btnSetAlarm).setOnClickListener {
            //set alarm
            Toast.makeText(this, "set alarm", Toast.LENGTH_SHORT).show()
            setAlarm(alarmManager)
        }
    }

    private fun setAlarm(alarmManager: AlarmManager) {
        val calendar = Calendar.getInstance()

        calendar.set(Calendar.SECOND, 0)
        calendar.add(Calendar.MINUTE, 1)


        val alarmIntent = Intent(this, AlarmReceiver::class.java)
                .putExtra("content", "hello alarm")
                .let {
                    PendingIntent.getBroadcast(this, 100, it, PendingIntent.FLAG_UPDATE_CURRENT)
                }

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            Log.d("AlarmRepeat", "set alarm 1")
            alarmManager.setExact(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    alarmIntent)
        }

    }
}