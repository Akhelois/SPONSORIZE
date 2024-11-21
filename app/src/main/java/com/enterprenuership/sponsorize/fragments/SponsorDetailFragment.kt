package com.enterprenuership.sponsorize.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.enterprenuership.sponsorize.R
import com.enterprenuership.sponsorize.databinding.FragmentSponsorDetailBinding

class SponsorDetailFragment : Fragment() {

    private lateinit var binding: FragmentSponsorDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSponsorDetailBinding.inflate(inflater, container, false)

        // Ambil data dari arguments
        val sponsorId = arguments?.getString("sponsorId")
        val sponsorName = arguments?.getString("sponsorName")
        val sponsorCompany = arguments?.getString("sponsorCompany")
//        val sponsorCategory = arguments?.getString("sponsorCategory")
        val sponsorDescription = arguments?.getString("sponsorDescription")
        val imageHeader = arguments?.getString("imageHeader")
        val sponsorCriteria = arguments?.getString("sponsorCriteria")
        val sponsorLogo = arguments?.getString("sponsorLogo")

        // Tampilkan data pada UI
        binding.sponsorName.text = sponsorName
        binding.sponsorCompany.text = sponsorCompany
        binding.sponsorDescription.text = sponsorDescription
        binding.sponsorCriteria.text = sponsorCriteria

        Glide.with(this).load(imageHeader).into(binding.sponsorHeaderImage)
        Glide.with(this).load(sponsorLogo).into(binding.sponsorLogo)

        // Handle Back Button
        binding.backButton.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        return binding.root
    }
}