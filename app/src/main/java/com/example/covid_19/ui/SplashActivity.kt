package com.example.covid_19.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.covid_19.R

class SplashActivity : AppCompatActivity() {

    private val splashScreenTimeOut: Long = 1500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        @Suppress("DEPRECATION")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        }
        else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

        setContentView(R.layout.activity_splash_screen)

        Looper.myLooper()?.also { looper ->
            Handler(looper).postDelayed({
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }, splashScreenTimeOut)
        }
    }
}