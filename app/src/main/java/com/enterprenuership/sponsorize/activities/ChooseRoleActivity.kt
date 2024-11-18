package com.enterprenuership.sponsorize.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.enterprenuership.sponsorize.R
import com.enterprenuership.sponsorize.databinding.ActivityChooseRoleBinding

class ChooseRoleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChooseRoleBinding
    private var selectedRole: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChooseRoleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.eventOrganizerCard.setOnClickListener {
            updateSelection(binding.eventOrganizerCard)
        }

        binding.sponsorCard.setOnClickListener {
            updateSelection(binding.sponsorCard)
        }

        binding.confirmButton.setOnClickListener {
            // Handle the confirm button action
        }
    }

    private fun updateSelection(newSelection: View) {
        selectedRole?.background = getDrawable(R.drawable.role_unselected)
        newSelection.background = getDrawable(R.drawable.role_selected)
        selectedRole = newSelection
    }
}
