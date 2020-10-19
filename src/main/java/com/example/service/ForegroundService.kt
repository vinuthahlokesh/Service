package com.example.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import android.provider.Settings
import android.widget.Toast
import androidx.core.app.NotificationCompat

class ForegroundService : Service() {
    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }
    private val CHANNEL_ID: String = "foregroundService"
    private lateinit var m: MediaPlayer
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        createNotificationChannel()
        m = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI)
        val returnIntent = Intent(this, MainActivity::class.java)
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, returnIntent, 0)
        m.isLooping = true
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Foreground service notification")
            .setContentText("Music playing in foreground service")
            .setSmallIcon(R.drawable.ic_baseline_music)
            .setContentIntent(pendingIntent)
            .build()
        m.start()
        startForeground(1, notification)
        Toast.makeText(this, "Service started..", Toast.LENGTH_SHORT).show()
        return START_NOT_STICKY
    }


    override fun onDestroy() {
        super.onDestroy()
        m.stop()
        Toast.makeText(this, "Service destroyed..", Toast.LENGTH_SHORT).show()
        stopSelf()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                "Foreground Service Example",
                NotificationManager.IMPORTANCE_DEFAULT
            )

            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(serviceChannel)
        }
    }

}