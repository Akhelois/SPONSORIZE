package com.enterprenuership.sponsorize.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.enterprenuership.sponsorize.databinding.ItemSponsorBinding
import com.enterprenuership.sponsorize.models.Sponsor

class SponsorAdapter(private var sponsorList: List<Sponsor>) : RecyclerView.Adapter<SponsorAdapter.SponsorViewHolder>() {

    // Define ViewHolder
    inner class SponsorViewHolder(private val binding: ItemSponsorBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(sponsor: Sponsor) {
            binding.sponsorName.text = sponsor.sponsorName
            binding.sponsorBusiness.text = sponsor.sponsorCategory
            binding.sponsorCompany.text = sponsor.sponsorCompany

            // Handle item click
            binding.moreBtn.setOnClickListener {
                onItemClickCallback?.onItemClick(sponsor)
            }
        }
    }

    // Callback Interface
    interface IOnItemClickCallback {
        fun onItemClick(sponsor: Sponsor)
    }

    private var onItemClickCallback: IOnItemClickCallback? = null

    fun setOnItemClickCallback(callback: IOnItemClickCallback) {
        this.onItemClickCallback = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SponsorViewHolder {
        val binding = ItemSponsorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SponsorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SponsorViewHolder, position: Int) {
        holder.bind(sponsorList[position])
    }

    override fun getItemCount(): Int = sponsorList.size

    fun updateData(newSponsorList: List<Sponsor>) {
        sponsorList = newSponsorList
        notifyDataSetChanged()
    }
}