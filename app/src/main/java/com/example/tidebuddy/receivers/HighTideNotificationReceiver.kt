package com.example.tidebuddy.receivers

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.TaskStackBuilder
import com.example.tidebuddy.activities.HighTideNotificationActivity
import com.example.tidebuddy.R

class HighTideNotificationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val highTideIntent = Intent(context, HighTideNotificationActivity::class.java)
        val highTidePendingIntent = TaskStackBuilder.create(context).run {
            addNextIntentWithParentStack(highTideIntent)
            getPendingIntent(0, PendingIntent.FLAG_IMMUTABLE)
        }
        val builder = NotificationCompat.Builder(context, context.getString(R.string.high_tide_channel_id))
            .setSmallIcon(R.drawable.high_tide_notification_icon)
            .setContentTitle("It's high tide in 10 Seconds!")
            .setContentText("Go for a swim")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(highTidePendingIntent)

        NotificationManagerCompat.from(context).notify(0, builder.build())
    }
}