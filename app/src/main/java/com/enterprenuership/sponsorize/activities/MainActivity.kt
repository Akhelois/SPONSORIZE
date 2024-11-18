package com.enterprenuership.sponsorize.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.enterprenuership.sponsorize.R
import com.enterprenuership.sponsorize.databinding.ActivityMainBinding
import com.enterprenuership.sponsorize.fragments.HomeFragment
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var db: FirebaseFirestore
    private  lateinit var homeFragment: HomeFragment
    private var activeFragment: Fragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = FirebaseFirestore.getInstance()

        // Ambil data dari login activity
        val uid = intent.getStringExtra("uid")

        // init fragment
        homeFragment = HomeFragment().apply{
            arguments = Bundle().apply {
                putString("uid", uid)
            }
        }

        // Taro fragment ke container
        supportFragmentManager.beginTransaction().apply {
            add(R.id.fragmentContainer, homeFragment, "HomeFragment").hide(homeFragment)
            show(homeFragment)
        }.commit()

        activeFragment = homeFragment

        binding.botNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menuHome -> {
                    switchFragment(homeFragment)
                }
            }
            true
        }
    }

    private fun switchFragment(fragment: Fragment) {
        if (fragment != activeFragment) {
            supportFragmentManager.beginTransaction().apply {
                activeFragment?.let { hide(it) }
                show(fragment)
            }.commit()
            activeFragment = fragment
        }
    }
}