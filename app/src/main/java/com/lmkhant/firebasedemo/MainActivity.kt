package com.lmkhant.firebasedemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.firebase.auth.FirebaseAuth
import com.lmkhant.firebasedemo.auth.GoogleSignInActivity
import com.lmkhant.firebasedemo.auth.ProfileActivity
import com.lmkhant.firebasedemo.databinding.ActivityMainBinding
import com.lmkhant.firebasedemo.realtimedatabase.RealTimeDatabaseActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.acProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        binding.realtimeDatabase.setOnClickListener {
            startActivity(Intent(this, RealTimeDatabaseActivity::class.java))
        }


    }


}