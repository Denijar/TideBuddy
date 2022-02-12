package com.example.tidebuddy.utils

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import androidx.core.app.AlarmManagerCompat

class BroadcastUtil(base: Context?) : ContextWrapper(base) {

    /** Schedules a one-off broadcast at the given time in milliseconds **/
    fun scheduleBroadcast(time: Long, broadcastReceiver: Class<out BroadcastReceiver>){
        val intent = Intent(this, broadcastReceiver)
        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        AlarmManagerCompat.setExact(alarmManager, AlarmManager.RTC_WAKEUP, time, pendingIntent)
    }
}