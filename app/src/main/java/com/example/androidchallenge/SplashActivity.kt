package com.example.androidchallenge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        val intent = Intent(this@SplashActivity, MainActivity::class.java)
        startActivity(intent)
        this.finish()
    }
}