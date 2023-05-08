package com.example.salarymanagementsystem

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.util.Log

class MyBroadcastDemo : BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        TODO("Not yet implemented")
        val mp= MediaPlayer.create(p0,R.raw.alarm)
        mp.start()
        Log.d("hello", "AlarmRinging")
    }
}
