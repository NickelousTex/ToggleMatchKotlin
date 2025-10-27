package com.togglematch.ui.discover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.togglematch.R
import com.togglematch.data.model.Profile
import com.togglematch.data.model.SwipeType
import com.togglematch.databinding.FragmentDiscoverBinding

class DiscoverFragment : Fragment() {
    
    private var _binding: FragmentDiscoverBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var viewModel: DiscoverViewModel
    
    private val mockProfiles = listOf(
        Profile(
            id = "1",
            name = "Emma",
            age = 25,
            bio = "Love hiking, photography, and good coffee. Looking for someone to share adventures with!",
            photos = listOf("https://images.unsplash.com/photo-1494790108755-2616b612b786?w=400"),
            interests = listOf("Photography", "Hiking", "Coffee", "Travel"),
            location = "San Francisco, CA",
            distance = 2,
            verified = true,
            online = true,
            lastActive = "2 minutes ago"
        ),
        Profile(
            id = "2",
            name = "Alex",
            age = 28,
            bio = "Software engineer by day, chef by night. Always up for trying new restaurants!",
            photos = listOf("https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=400"),
            interests = listOf("Cooking", "Technology", "Wine", "Movies"),
            location = "San Francisco, CA",
            distance = 5,
            verified = false,
            online = false,
            lastActive = "1 hour ago"
        ),
        Profile(
            id = "3",
            name = "Sofia",
            age = 23,
            bio = "Artist and yoga instructor. Love creating and finding peace in nature.",
            photos = listOf("https://images.unsplash.com/photo-1438761681033-6461ffad8d80?w=400"),
            interests = listOf("Art", "Yoga", "Nature", "Meditation"),
            location = "San Francisco, CA",
            distance = 1,
            verified = true,
            online = true,
            lastActive = "Online now"
        )
    )
    
    private var currentProfileIndex = 0
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDiscoverBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        viewModel = ViewModelProvider(this)[DiscoverViewModel::class.java]
        
        setupClickListeners()
        showCurrentProfile()
    }
    
    private fun setupClickListeners() {
        binding.passButton.setOnClickListener {
            handleSwipe(SwipeType.PASS)
        }
        
        binding.likeButton.setOnClickListener {
            handleSwipe(SwipeType.LIKE)
        }
        
        binding.superLikeButton.setOnClickListener {
            handleSwipe(SwipeType.SUPER_LIKE)
        }
    }
    
    private fun showCurrentProfile() {
        if (currentProfileIndex >= mockProfiles.size) {
            showEmptyState()
            return
        }
        
        val profile = mockProfiles[currentProfileIndex]
        displayProfile(profile)
    }
    
    private fun displayProfile(profile: Profile) {
        // Load profile image
        Glide.with(this)
            .load(profile.photos.firstOrNull())
            .placeholder(R.drawable.ic_profile)
            .into(binding.profileImage)
        
        // Set profile information
        binding.profileName.text = "${profile.name}, ${profile.age}"
        binding.profileDistance.text = getString(R.string.miles_away, profile.distance)
        binding.profileBio.text = profile.bio
        
        // Show verification badge
        binding.verifiedBadge.visibility = if (profile.verified) View.VISIBLE else View.GONE
        
        // Show online indicator
        binding.onlineIndicator.visibility = if (profile.online) View.VISIBLE else View.GONE
        
        // Update interests (simplified for now)
        // In a real app, you'd dynamically create TextView elements for each interest
    }
    
    private fun handleSwipe(swipeType: SwipeType) {
        val currentProfile = mockProfiles[currentProfileIndex]
        
        // Here you would typically send the swipe action to your backend
        // For now, we'll just move to the next profile
        
        currentProfileIndex++
        showCurrentProfile()
        
        // Animate the card swipe (simplified)
        animateCardSwipe(swipeType)
    }
    
    private fun animateCardSwipe(swipeType: SwipeType) {
        val card = binding.profileCard
        val translationX = when (swipeType) {
            SwipeType.LIKE, SwipeType.SUPER_LIKE -> 1000f
            SwipeType.PASS -> -1000f
        }
        
        card.animate()
            .translationX(translationX)
            .alpha(0f)
            .setDuration(300)
            .withEndAction {
                card.translationX = 0f
                card.alpha = 1f
            }
    }
    
    private fun showEmptyState() {
        binding.profileCard.visibility = View.GONE
        binding.swipeButtons.visibility = View.GONE
        binding.emptyText.visibility = View.VISIBLE
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
