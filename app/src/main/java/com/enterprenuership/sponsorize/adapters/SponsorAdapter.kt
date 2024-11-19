package com.enterprenuership.sponsorize.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.enterprenuership.sponsorize.databinding.ItemSponsorBinding
import com.enterprenuership.sponsorize.models.Sponsor

class SponsorAdapter(private var sponsorList: List<Sponsor>) : RecyclerView.Adapter<SponsorAdapter.SponsorViewHolder>() {

    // Interface untuk callback item click
    interface IOnItemClickCallback {
        fun onItemClick(sponsor: Sponsor)
    }

    private lateinit var onItemClickCallback: IOnItemClickCallback

    // Setter untuk callback item click
    fun setOnItemClickCallback(onItemClickCallback: IOnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    // Method untuk memperbarui data sponsor list
    fun updateData(newSponsorList: List<Sponsor>) {
        sponsorList = newSponsorList
        notifyDataSetChanged()
    }

    private lateinit var binding: ItemSponsorBinding

    class SponsorViewHolder(private val binding: ItemSponsorBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(sponsor: Sponsor, onItemClickCallback: SponsorAdapter.IOnItemClickCallback) {
            binding.sponsor = sponsor

            // Tambahkan klik listener
            binding.root.setOnClickListener {
                onItemClickCallback.onItemClick(sponsor)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SponsorViewHolder {
        // Inflate the layout using DataBindingUtil or a direct LayoutInflater
        val binding = ItemSponsorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SponsorViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return sponsorList.size
    }

    override fun onBindViewHolder(holder: SponsorViewHolder, position: Int) {
        val sponsor = sponsorList[position]
        holder.bind(sponsor, onItemClickCallback)

        // Set click listener on the item
        holder.itemView.setOnClickListener {
            if (::onItemClickCallback.isInitialized) {
                onItemClickCallback.onItemClick(sponsor)
            }
        }
    }
}
