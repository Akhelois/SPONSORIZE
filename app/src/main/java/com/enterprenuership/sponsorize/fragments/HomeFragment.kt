package com.enterprenuership.sponsorize.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.enterprenuership.sponsorize.adapters.SponsorAdapter
import com.enterprenuership.sponsorize.databinding.FragmentHomeBinding
import com.enterprenuership.sponsorize.models.Sponsor
import com.google.firebase.firestore.FirebaseFirestore

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var sponsorAdapter: SponsorAdapter
    private lateinit var db: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        db = FirebaseFirestore.getInstance()
        val intentUID = arguments?.getString("uid")
        Log.d("HomeFragment", "${arguments?.getString("uid")}")

        sponsorAdapter = SponsorAdapter(emptyList())
        binding.sponsorRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.sponsorRecyclerView.adapter = sponsorAdapter

        val docRef = db.collection("Users").document(intentUID.toString())
        docRef.addSnapshotListener { snapshot, error ->
            if (error != null) {
                Toast.makeText(requireContext(), "Error: ${error.message}", Toast.LENGTH_SHORT).show()
                Log.e("HomeFragment", "Snapshot listener error: ${error.message}")
                return@addSnapshotListener
            }

            if (snapshot != null && snapshot.exists()) {
                val username = snapshot.getString("username")
                binding.welcomeUsername.text = "$username!"
                Log.d("HomeFragment", "Username: $username")
            } else {
                Toast.makeText(requireContext(), "Document not found", Toast.LENGTH_SHORT).show()
                Log.e("HomeFragment", "Document not found for UID: $intentUID")
            }
        }

        // Fetch data from Firestore
//        fetchSponsors()

        return binding.root
    }
}
