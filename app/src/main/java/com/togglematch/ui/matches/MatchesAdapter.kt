package com.togglematch.ui.matches

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.togglematch.data.model.Match
import com.togglematch.databinding.ItemMatchBinding
import java.text.SimpleDateFormat
import java.util.*

class MatchesAdapter(
    private val onMatchClick: (Match) -> Unit
) : ListAdapter<Match, MatchesAdapter.MatchViewHolder>(MatchDiffCallback()) {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val binding = ItemMatchBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MatchViewHolder(binding)
    }
    
    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    
    inner class MatchViewHolder(
        private val binding: ItemMatchBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        
        fun bind(match: Match) {
            val profile = match.profiles.firstOrNull() ?: return
            
            // Load profile image
            Glide.with(binding.root.context)
                .load(profile.photos.firstOrNull())
                .placeholder(com.togglematch.R.drawable.ic_profile)
                .into(binding.matchAvatar)
            
            // Set profile information
            binding.matchName.text = "${profile.name}, ${profile.age}"
            
            // Show online indicator
            binding.onlineIndicator.visibility = if (profile.online) {
                android.view.View.VISIBLE
            } else {
                android.view.View.GONE
            }
            
            // Set last message or default text
            val lastMessage = match.messages.lastOrNull()?.content
            binding.lastMessage.text = lastMessage ?: "Start a conversation!"
            
            // Set timestamp
            val dateFormat = SimpleDateFormat("MMM dd", Locale.getDefault())
            binding.timestamp.text = dateFormat.format(Date(match.timestamp))
            
            // Set click listener
            binding.root.setOnClickListener {
                onMatchClick(match)
            }
        }
    }
    
    class MatchDiffCallback : DiffUtil.ItemCallback<Match>() {
        override fun areItemsTheSame(oldItem: Match, newItem: Match): Boolean {
            return oldItem.id == newItem.id
        }
        
        override fun areContentsTheSame(oldItem: Match, newItem: Match): Boolean {
            return oldItem == newItem
        }
    }
}
