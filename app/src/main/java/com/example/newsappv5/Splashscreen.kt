package com.example.newsappv5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.*
import android.content.Intent

class Splashscreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        supportActionBar?.hide()

        Handler().postDelayed({
            val intent = Intent (this@Splashscreen, Login::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}