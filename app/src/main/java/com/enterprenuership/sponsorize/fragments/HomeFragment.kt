package com.enterprenuership.sponsorize.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.enterprenuership.sponsorize.R
import com.enterprenuership.sponsorize.adapters.SponsorAdapter
import com.enterprenuership.sponsorize.databinding.FragmentHomeBinding
import com.enterprenuership.sponsorize.models.Sponsor
import com.google.firebase.firestore.FirebaseFirestore

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var sponsorAdapter: SponsorAdapter
    private lateinit var db: FirebaseFirestore
    private val sponsorList = mutableListOf<Sponsor>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        db = FirebaseFirestore.getInstance()
        val intentUID = arguments?.getString("uid")
        Log.d("HomeFragment", "Received UID: $intentUID")

        sponsorAdapter = SponsorAdapter(sponsorList)
        binding.sponsorRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.sponsorRecyclerView.adapter = sponsorAdapter

        fetchUsername(intentUID)
        fetchSponsors()

        sponsorAdapter.setOnItemClickCallback(object : SponsorAdapter.IOnItemClickCallback {
            override fun onItemClick(sponsor: Sponsor) {
                // Buat Bundle untuk mengirim data sponsor ke SponsorDetailFragment
                val bundle = Bundle().apply {
                    putString("sponsorId", sponsor.sponsorId)
                    putString("sponsorName", sponsor.sponsorName)
                    putString("sponsorCompany", sponsor.sponsorCompany)
                    putString("sponsorCategory", sponsor.sponsorCategory)
                    putString("sponsorCriteria", sponsor.sponsorCriteria.joinToString("\n"))
                    putString("sponsorDescription", sponsor.sponsorDescription)
                    putString("sponsorLogo", sponsor.sponsorLogo)
                    putString("imageHeader", sponsor.imageHeader)
                }

                // Navigasi ke SponsorDetailFragment
                val sponsorDetailFragment = SponsorDetailFragment().apply {
                    arguments = bundle
                }

                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, sponsorDetailFragment)
                    .addToBackStack(null)
                    .commit()
            }
        })

        return binding.root
    }

    private fun fetchUsername(uid: String?) {
        if (uid.isNullOrEmpty()) {
            Toast.makeText(requireContext(), "UID not provided", Toast.LENGTH_SHORT).show()
            Log.e("HomeFragment", "UID is null or empty")
            return
        }

        val docRef = db.collection("Users").document(uid)
        docRef.addSnapshotListener { snapshot, error ->
            if (error != null) {
                Toast.makeText(requireContext(), "Error: ${error.message}", Toast.LENGTH_SHORT).show()
                Log.e("HomeFragment", "Snapshot listener error: ${error.message}")
                return@addSnapshotListener
            }

            if (snapshot != null && snapshot.exists()) {
                val username = snapshot.getString("username") ?: "Unknown User"
                binding.welcomeUsername.text = "$username!"
                Log.d("HomeFragment", "Username: $username")
            } else {
                Toast.makeText(requireContext(), "User not found", Toast.LENGTH_SHORT).show()
                Log.e("HomeFragment", "Document not found for UID: $uid")
            }
        }
    }

    private fun fetchSponsors() {
        db.collection("Sponsors").addSnapshotListener { snapshots, error ->
            if (error != null) {
                Log.e("HomeFragment", "Error fetching sponsors: ${error.message}")
                Toast.makeText(requireContext(), "Error loading sponsors", Toast.LENGTH_SHORT).show()
                return@addSnapshotListener
            }

            if (snapshots == null || snapshots.isEmpty) {
                Log.d("HomeFragment", "No sponsors found")
                Toast.makeText(requireContext(), "No sponsors available", Toast.LENGTH_SHORT).show()
                return@addSnapshotListener
            }

            sponsorList.clear()
            for (document in snapshots) {
                val sponsor = Sponsor(
                    sponsorId = document.getString("sponsorId") ?: "",
                    sponsorName = document.getString("sponsorName") ?: "Unknown Name",
                    sponsorCompany = document.getString("sponsorCompany") ?: "Unknown Company",
                    sponsorCategory = document.getString("sponsorCategory") ?: "Uncategorized",
                    sponsorDescription = document.getString("sponsorDescription") ?: "No Description",
                    sponsorLogo = document.getString("sponsorLogo") ?: "Logo",
                    imageHeader = document.getString("imageHeader") ?: "Header",
                    sponsorCriteria = document.get("sponsorCriteria") as? List<String> ?: listOf()
                )
                sponsorList.add(sponsor)
            }
            sponsorAdapter.notifyDataSetChanged()
            Log.d("HomeFragment", "Sponsors loaded: ${sponsorList.size}")
        }
    }
}