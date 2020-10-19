package com.example.service

import android.app.Service
import android.content.Intent
import android.hardware.Sensor
import android.media.MediaPlayer
import android.media.audiofx.BassBoost
import android.os.IBinder
import android.provider.ContactsContract
import android.provider.Settings
import android.widget.Toast
import java.net.URI.create

class BackgroundService : Service() {
    private lateinit var m:MediaPlayer



    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        m.start()
        Toast.makeText(this, "Service started..", Toast.LENGTH_SHORT).show()
        return START_STICKY
    }


    override fun onBind(intent: Intent?): IBinder? {
       return null
    }



    override fun onCreate() {
        super.onCreate()
        m = MediaPlayer.create(this, Settings.System.DEFAULT_ALARM_ALERT_URI)
        m.isLooping = true


    }

    override fun onDestroy() {
        super.onDestroy()
        m.stop()
        Toast.makeText(this, "Service destroyed..", Toast.LENGTH_SHORT).show()
    }










}