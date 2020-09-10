package com.douglas.gitlist.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.douglas.gitlist.R

class SplashActivity : AppCompatActivity() {
    private val runnable = Runnable {
        goMainActivity()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler(Looper.myLooper()!!).postDelayed(runnable, 2000)
    }


    private fun goMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
    }
}