package com.example.contacts.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.contacts.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()
        startContactsActivity()
    }

    private fun startContactsActivity() {
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, ContactsActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)
    }
}