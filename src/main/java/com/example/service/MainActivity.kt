package com.example.service

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn_1 = findViewById(R.id.start_fg_btn) as Button
        val btn_2 = findViewById(R.id.stop_fg_btn) as Button
        val btn_3 = findViewById(R.id.start_btn) as Button
        val btn_4 = findViewById(R.id.stop_btn) as Button

        val backgroundServiceIntent = Intent(this, BackgroundService::class.java)
        val foregroundServiceIntent = Intent(this, ForegroundService::class.java)
        btn_1.setOnClickListener {
            startService(foregroundServiceIntent)
        }
        btn_2.setOnClickListener {
            stopService(foregroundServiceIntent)
        }
        btn_3.setOnClickListener {
            startService(backgroundServiceIntent)
        }
        btn_4.setOnClickListener {
            stopService(backgroundServiceIntent)
        }

    }
}