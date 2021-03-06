package com.tidebuddy.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.view.View
import androidx.lifecycle.lifecycleScope
import com.tidebuddy.BuildConfig
import com.tidebuddy.R
import com.tidebuddy.network.niwa.TideApi
import com.tidebuddy.receivers.HighTideNotificationReceiver
import com.tidebuddy.utils.BroadcastUtil
import com.tidebuddy.utils.NotificationUtil

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create High tide alert notification channel
        NotificationUtil(this).run { createNotificationChannel(getString(R.string.high_tide_channel_id), getString(R.string.high_tide_channel_name)) }
        // Schedule a broadcast 10s after app is opened, to be handled by HighTideNotificationReceiver
        BroadcastUtil(this).run { scheduleBroadcast(System.currentTimeMillis() + 1000 * 10, HighTideNotificationReceiver::class.java) }
        setContentView(R.layout.activity_main);
    }

    /** Sends a notification when the high tide button is clicked **/
    fun onHighTideClick(view: View) {
        NotificationUtil(this).run {
            triggerNotification(getString(R.string.high_tide_channel_id), R.drawable.high_tide_notification_icon, "It's high tide!", "Go for a swim", null)
        }
    }

     /** Gets tide data from NIWA when the button is clicked **/
     fun onWhenTideDataClick(view: View) {
         val tideApi = TideApi.create();

         lifecycleScope.launchWhenCreated {
             val tideResponse = tideApi.getTideData("-36.81782","174.690079", BuildConfig.NIWA_TIDE_BUDDY_API_KEY);
             if (tideResponse.isSuccessful) {
                 val tideData = tideResponse.body();
                 if (tideData != null) {
                     println(tideData.values)
                 };
             }
         }
     }
}