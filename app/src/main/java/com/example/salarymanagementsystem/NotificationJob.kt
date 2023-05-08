package com.example.salarymanagementsystem

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.util.Log
import android.widget.Toast
import java.security.Provider
import java.security.Provider.Service

class NotificationJob: JobService() {
    lateinit var nm:NotificationManager
    lateinit var nc:NotificationChannel
    lateinit var builder:Notification.Builder
    lateinit var pendingIntent:PendingIntent
    val title="Task is Added"
    val channeld="MY CHANNEL"
    override fun onStartJob(params: JobParameters?): Boolean {

//
        Log.d("Job","The job is running")
        val intent = Intent(this, MainActivity::class.java)
        pendingIntent = PendingIntent.getActivity(
            this, 0,
            intent, PendingIntent.FLAG_IMMUTABLE)
        nm=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        createNotificationChannel()
        nm.notify(123,builder.build())

        return true
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            nc= NotificationChannel("MY CHANNEL","Noti",NotificationManager.IMPORTANCE_HIGH)
            nm.createNotificationChannel(nc)
            builder=Notification.Builder(this,channeld)
                .setSmallIcon(com.google.android.material.R.drawable.notification_bg)
                .setContentTitle(title)
                .setContentText("A new task is added to our databases.")
                .setLargeIcon(
                    BitmapFactory.decodeResource(this.resources,
                    R.drawable.about))
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)

        }else
        {
            builder = Notification.Builder(this)
                .setSmallIcon(com.google.android.material.R.drawable.notification_bg)
                .setContentTitle(title)
                .setContentText("this is  teh desc")
                .setLargeIcon(
                    BitmapFactory.decodeResource(this.resources,
                        R.drawable.about))
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
        }
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        TODO("Not yet implemented")
    }
}