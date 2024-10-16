package com.enterprenuership.sponsorize

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.enterprenuership.sponsorize.databinding.ActivityOnboardingBinding

class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding
    private lateinit var viewPager2: ViewPager2
    private lateinit var dotsLayout: LinearLayout
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable
    private val sliderDelay: Long = 7000 // 7 detik

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewPager2 = findViewById(R.id.imageSlider)
        dotsLayout = findViewById(R.id.dots)

        val images = listOf(
            R.drawable.image_1,
            R.drawable.image_2,
            R.drawable.image_3
        )

        val adapter = ImageSliderAdapter(images)
        viewPager2.adapter = adapter

        setupDotsIndicator(images.size)
        setCurrentDot(0)

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                setCurrentDot(position)
                super.onPageSelected(position)
            }
        })

        // Setup auto-slide
        handler = Handler(Looper.getMainLooper())
        runnable = Runnable {
            val nextPos = (viewPager2.currentItem + 1) % images.size
            viewPager2.currentItem = nextPos
            handler.postDelayed(runnable, sliderDelay)
        }

        // Handle next button
        binding.nextBtn.setOnClickListener {
            val intentToLanding = Intent(this, LandingActivity::class.java)
            startActivity(intentToLanding)
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(runnable, sliderDelay)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }

    private fun setupDotsIndicator(count: Int) {
        for (i in 0 until count) {
            val dot = ImageView(this)
            dot.setImageResource(R.drawable.dot_unselected) // Buat drawable untuk dot_unselected
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(8, 0, 8, 0)
            dot.layoutParams = params
            dotsLayout.addView(dot)
        }
    }

    private fun setCurrentDot(position: Int) {
        for (i in 0 until dotsLayout.childCount) {
            val imageView = dotsLayout.getChildAt(i) as ImageView
            if (i == position) {
                imageView.setImageResource(R.drawable.dot_selected) // Buat drawable untuk dot_selected
            } else {
                imageView.setImageResource(R.drawable.dot_unselected)
            }
        }
    }
}
