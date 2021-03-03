package pl.training.runkeeper.commons

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class SampleBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val text = intent?.getStringExtra("message")
        Log.d("###", text ?: "Message not found")
    }

}