package com.togglematch.ui.matches

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.togglematch.R
import com.togglematch.data.model.Match
import com.togglematch.data.model.Profile
import com.togglematch.databinding.FragmentMatchesBinding

class MatchesFragment : Fragment() {
    
    private var _binding: FragmentMatchesBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var viewModel: MatchesViewModel
    private lateinit var matchesAdapter: MatchesAdapter
    
    private val mockMatches = listOf(
        Match(
            id = "1",
            profiles = listOf(
                Profile(
                    id = "1",
                    name = "Emma",
                    age = 25,
                    bio = "Love hiking, photography, and good coffee.",
                    photos = listOf("https://images.unsplash.com/photo-1494790108755-2616b612b786?w=200"),
                    interests = listOf("Photography", "Hiking", "Coffee"),
                    location = "San Francisco, CA",
                    distance = 2,
                    verified = true,
                    online = true,
                    lastActive = "2 minutes ago"
                )
            ),
            timestamp = System.currentTimeMillis() - 3600000,
            messages = emptyList()
        ),
        Match(
            id = "2",
            profiles = listOf(
                Profile(
                    id = "2",
                    name = "Alex",
                    age = 28,
                    bio = "Software engineer by day, chef by night.",
                    photos = listOf("https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=200"),
                    interests = listOf("Cooking", "Technology", "Wine"),
                    location = "San Francisco, CA",
                    distance = 5,
                    verified = false,
                    online = false,
                    lastActive = "1 hour ago"
                )
            ),
            timestamp = System.currentTimeMillis() - 7200000,
            messages = emptyList()
        )
    )
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMatchesBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        viewModel = ViewModelProvider(this)[MatchesViewModel::class.java]
        
        setupRecyclerView()
        updateUI()
    }
    
    private fun setupRecyclerView() {
        matchesAdapter = MatchesAdapter { match ->
            // Handle match click - navigate to chat
        }
        
        binding.matchesRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = matchesAdapter
        }
    }
    
    private fun updateUI() {
        binding.subtitle.text = getString(R.string.new_matches, mockMatches.size, if (mockMatches.size != 1) "es" else "")
        matchesAdapter.submitList(mockMatches)
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
