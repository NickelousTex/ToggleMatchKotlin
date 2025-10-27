package com.togglematch.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.togglematch.R
import com.togglematch.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var viewModel: ProfileViewModel
    
    // Mock user profile data
    private val userProfile = com.togglematch.data.model.Profile(
        id = "user",
        name = "John Doe",
        age = 26,
        bio = "Adventure seeker and coffee enthusiast. Love exploring new places and trying different cuisines. Looking for someone to share life's adventures with!",
        photos = listOf(
            "https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=400",
            "https://images.unsplash.com/photo-1500648767791-00dcc994a43e?w=400",
            "https://images.unsplash.com/photo-1472099645785-5658abf4ff4e?w=400"
        ),
        interests = listOf("Travel", "Photography", "Coffee", "Hiking", "Cooking", "Music"),
        location = "San Francisco, CA",
        distance = 0,
        verified = true,
        online = true,
        lastActive = "Online now"
    )
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
        
        setupClickListeners()
        displayProfile()
    }
    
    private fun setupClickListeners() {
        binding.editProfileButton.setOnClickListener {
            // Navigate to edit profile screen
        }
        
        binding.privacyButton.setOnClickListener {
            // Navigate to privacy settings
        }
    }
    
    private fun displayProfile() {
        // Load profile image
        Glide.with(this)
            .load(userProfile.photos.firstOrNull())
            .placeholder(R.drawable.ic_profile)
            .into(binding.profileAvatar)
        
        // Set profile information
        binding.profileName.text = "${userProfile.name}, ${userProfile.age}"
        binding.profileLocation.text = userProfile.location
        binding.profileBio.text = userProfile.bio
        
        // Show verification badge
        binding.verifiedBadge.visibility = if (userProfile.verified) View.VISIBLE else View.GONE
        
        // Show online status
        binding.onlineIndicator.visibility = if (userProfile.online) View.VISIBLE else View.GONE
        binding.statusText.visibility = if (userProfile.online) View.VISIBLE else View.GONE
        
        // Update interests (simplified for now)
        // In a real app, you'd dynamically create TextView elements for each interest
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
