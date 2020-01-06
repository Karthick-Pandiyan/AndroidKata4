package com.kp.berlinclock

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.text.SimpleDateFormat
import java.util.*
import android.os.Handler




class MainActivity : AppCompatActivity() {

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val berlinClock = BerlinClock()
        val someHandler = Handler(mainLooper)
        someHandler.postDelayed(object : Runnable {
            override fun run() {
                txtBerlinClock.text = berlinClock.transformToBerlinTime(SimpleDateFormat("HH:mm:ss").format(Date(System.currentTimeMillis())))
                someHandler.postDelayed(this, 1000)
            }
        }, 10)

    }
}
