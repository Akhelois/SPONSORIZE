package com.enterprenuership.sponsorize.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.enterprenuership.sponsorize.R
import com.enterprenuership.sponsorize.databinding.FragmentEventBinding


class EventFragment : Fragment() {

    private lateinit var binding: FragmentEventBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEventBinding.inflate(inflater, container, false)
        return binding.root
    }
}