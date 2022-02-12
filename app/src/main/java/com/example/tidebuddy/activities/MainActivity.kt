package com.example.tidebuddy.activities

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.tidebuddy.R
import kotlin.random.Random
import android.app.AlarmManager

import android.app.PendingIntent
import android.view.View
import androidx.core.app.AlarmManagerCompat
import com.example.tidebuddy.receivers.HighTideNotificationReceiver


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);

        createNotificationChannel();
        scheduleNotification();
        setContentView(R.layout.activity_main);
    }

    /** Sends a notification when the high tide button is clicked **/
    fun onHighTideClick(view: View) {
        val builder = NotificationCompat.Builder(this, getString(R.string.high_tide_channel_id))
            .setSmallIcon(R.drawable.high_tide_notification_icon)
            .setContentTitle("It's high tide!")
            .setContentText("Go for a swim")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat.from(this).notify(Random.nextInt(1000), builder.build());
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

    private fun scheduleNotification(){
        val currentTime = System.currentTimeMillis();
        val tenSeconds = 1000 * 10;
        val triggerReminder = currentTime + tenSeconds; //triggers a reminder after 10 seconds.

        val intent = Intent(this, HighTideNotificationReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        AlarmManagerCompat.setExact(alarmManager, AlarmManager.RTC_WAKEUP, triggerReminder, pendingIntent);
    }


}