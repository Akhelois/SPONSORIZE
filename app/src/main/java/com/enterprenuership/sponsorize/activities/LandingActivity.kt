package com.enterprenuership.sponsorize.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.enterprenuership.sponsorize.databinding.ActivityLandingBinding

class LandingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLandingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLandingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Handle Sign Up Button
        binding.signUpBtn.setOnClickListener {
            val intentToSignUp = Intent(this, SignUpActivity::class.java)
            startActivity(intentToSignUp)
        }

        // Handle Sign In Button
        binding.signInBtn.setOnClickListener {
            val intentToSignIn = Intent(this, SignInActivity::class.java)
            startActivity(intentToSignIn)
        }
    }
}