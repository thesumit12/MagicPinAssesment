package com.example.sumitlakra.magicpinassesment.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.sumitlakra.magicpinassesment.R
import com.example.sumitlakra.magicpinassesment.view.mainActivity.MainActivity



class SplashScreen : AppCompatActivity() {

    private val SPLASH_TIME_OUT: Long = 3000;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        Handler().postDelayed({

            val intent = Intent(this@SplashScreen, MainActivity::class.java)
            startActivity(intent)

            finish()
        }, SPLASH_TIME_OUT)
    }
}
