package com.example.tidebuddy.receivers

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.TaskStackBuilder
import com.example.tidebuddy.activities.HighTideNotificationActivity
import com.example.tidebuddy.R
import com.example.tidebuddy.utils.NotificationUtil

class HighTideNotificationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // Create the complete back-stack for the HighTideNotificationActivity
        val highTideIntent = Intent(context, HighTideNotificationActivity::class.java)
        val highTidePendingIntent = TaskStackBuilder.create(context).run {
            addNextIntentWithParentStack(highTideIntent)
            getPendingIntent(0, PendingIntent.FLAG_IMMUTABLE)
        }

        NotificationUtil(context).run {
            triggerNotification(getString(R.string.high_tide_channel_id), R.drawable.high_tide_notification_icon, "It's high tide in 10 seconds!", "Go for a swim", highTidePendingIntent)
        }
    }
}