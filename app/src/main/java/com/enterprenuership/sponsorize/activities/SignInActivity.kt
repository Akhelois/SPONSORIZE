package com.enterprenuership.sponsorize.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.enterprenuership.sponsorize.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var fAuth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fAuth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        binding.submitBtn.setOnClickListener {
            val email = binding.emailInput.text.toString()
            val password = binding.passwordInput.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                showDialog("Invalid Input", "Please enter a valid email and password.")
            } else if (!isValidEmail(email)) {
                showDialog("Invalid Input", "Please enter a valid email address.")
            } else {
                fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        showDialog("Login Successful", "You have successfully logged in.") {
                            val intentToMain = Intent(this, MainActivity::class.java)
                            intentToMain.putExtra("uid", fAuth.currentUser?.uid.toString())
                            startActivity(intentToMain)
                            finish()
                        }
                    } else {
                        showDialog("Login Failed", "Invalid credentials. Please try again.")
                    }
                }
            }
        }
    }

    private fun showDialog(title: String, message: String, onPositiveAction: (() -> Unit)? = null) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
            onPositiveAction?.invoke()
        }
        builder.create().show()
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}