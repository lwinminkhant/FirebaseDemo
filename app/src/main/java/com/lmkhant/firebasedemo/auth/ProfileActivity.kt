package com.lmkhant.firebasedemo.auth

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.lmkhant.firebasedemo.MainActivity
import com.lmkhant.firebasedemo.R
import com.lmkhant.firebasedemo.auth.GoogleSignInActivity.Companion.RC_SIGN_IN
import com.lmkhant.firebasedemo.databinding.ActivityProfileBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.lang.Exception

class ProfileActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityProfileBinding
    private lateinit var signInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser

        binding.signOut.setOnClickListener {
            signOut()
        }
        if (auth.currentUser == null) {
            // Not signed in, launch the Sign In activity
            startActivity(Intent(this, GoogleSignInActivity::class.java))
            finish()
            return
        }

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        signInClient = GoogleSignIn.getClient(this, gso)

        updateUI(currentUser)

    }

    private fun signOut() {
        auth.signOut()
        signInClient.signOut()
        startActivity(Intent(this, GoogleSignInActivity::class.java))
        finish()
    }

    private fun updateUI(user: FirebaseUser?) {
        binding.profileImage.let { Glide.with(this).load(getUserPhotoUrl(user)).into(it) }
        binding.profileName.text = getUserName(user)
    }

    private fun getUserPhotoUrl(user: FirebaseUser?): String? {
        return if (user != null && user.photoUrl != null) {
            user.photoUrl.toString()
        } else null
    }

    private fun getUserName(user: FirebaseUser?): String? {
        return if (user != null) {
            user.displayName
        } else getString(R.string.anonymous)
    }
}