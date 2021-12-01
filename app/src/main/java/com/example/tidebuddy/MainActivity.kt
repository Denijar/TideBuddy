package com.example.tidebuddy

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        createNotificationChannel();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Sends a notification when the high tide button is clicked **/
    fun onHighTideClick(view: View) {
        var builder = NotificationCompat.Builder(this, getString(R.string.high_tide_channel_id))
            .setSmallIcon(R.drawable.high_tide_notification_icon)
            .setContentTitle("It's high tide!")
            .setContentText("Go for a swim")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        // send the notification
        val notificationId = Random.nextInt(1000);

        with(NotificationManagerCompat.from(this)) {
            notify(notificationId, builder.build())
        }
    }

    private fun createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name = getString(R.string.high_tide_channel_name);
            val importance = NotificationManager.IMPORTANCE_DEFAULT;
            val channel = NotificationChannel(getString(R.string.high_tide_channel_id), name, importance);

            // register the channel with the system
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager;
            notificationManager.createNotificationChannel(channel);
        }
    }
}