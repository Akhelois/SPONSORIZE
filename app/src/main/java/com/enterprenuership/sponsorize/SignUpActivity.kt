package com.enterprenuership.sponsorize// com.enterprenuership.sponsorize.SignUpActivity.kt
import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.UnderlineSpan
import androidx.appcompat.app.AppCompatActivity
import com.enterprenuership.sponsorize.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Handle next button
        binding.nextBtn.setOnClickListener {
            val intentToChooseRole = Intent(this, ChooseRoleActivity::class.java)
            startActivity(intentToChooseRole)
        }
    }
}
