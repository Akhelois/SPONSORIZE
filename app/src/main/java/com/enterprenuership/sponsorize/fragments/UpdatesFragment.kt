package com.enterprenuership.sponsorize.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.enterprenuership.sponsorize.R
import com.enterprenuership.sponsorize.databinding.FragmentUpdatesBinding

class UpdatesFragment : Fragment() {

    private lateinit var binding: FragmentUpdatesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdatesBinding.inflate(inflater, container, false)
        return binding.root
    }
}