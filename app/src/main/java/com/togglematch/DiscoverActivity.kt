package com.togglematch

import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Button
import android.widget.FrameLayout
import android.view.ViewGroup
import android.view.Gravity
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class DiscoverActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Create scrollable layout
        val scrollView = ScrollView(this).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
        
        // Create main container
        val mainLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER_HORIZONTAL
            setPadding(32, 32, 32, 32)
        }
        
        // Back button
        val backButton = Button(this).apply {
            text = "← Back to Menu"
            setBackgroundColor(android.graphics.Color.WHITE)
            setTextColor(android.graphics.Color.BLACK)
            setPadding(32, 16, 32, 16)
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, 0, 0, 32)
            }
            setOnClickListener {
                finish() // Close this activity and return to previous
            }
        }
        
        // Title
        val titleText = TextView(this).apply {
            text = "Discover"
            textSize = 28f
            setTextColor(android.graphics.Color.WHITE)
            setPadding(0, 0, 0, 32)
        }
        
        // Subtitle
        val subtitleText = TextView(this).apply {
            text = "Find your perfect match!"
            textSize = 18f
            setTextColor(android.graphics.Color.WHITE)
            setPadding(0, 0, 0, 48)
        }
        
        // Create profile grid (2 columns, 3 rows)
        val profileGrid = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER_HORIZONTAL
        }
        
        // Create 3 rows of 2 profiles each
        for (row in 0 until 3) {
            val rowLayout = LinearLayout(this).apply {
                orientation = LinearLayout.HORIZONTAL
                gravity = Gravity.CENTER_HORIZONTAL
                setPadding(0, 0, 0, 32)
            }
            
            // Add 2 profiles per row
            for (col in 0 until 2) {
                val profileIndex = row * 2 + col + 1
                
                val profileContainer = LinearLayout(this).apply {
                    orientation = LinearLayout.VERTICAL
                    gravity = Gravity.CENTER_HORIZONTAL
                    setPadding(16, 16, 16, 16)
                }
                
                val profileImage = ImageView(this).apply {
                    layoutParams = ViewGroup.LayoutParams(200, 200)
                    scaleType = ImageView.ScaleType.CENTER_CROP
                    setBackgroundColor(android.graphics.Color.LTGRAY)
                    // Set the actual profile image based on index
                    val imageResource = when (profileIndex) {
                        1 -> R.drawable.profile_1
                        2 -> R.drawable.profile_2
                        3 -> R.drawable.profile_3
                        4 -> R.drawable.profile_4
                        5 -> R.drawable.profile_5
                        6 -> R.drawable.profile_6
                        else -> R.drawable.ic_profile
                    }
                    setImageResource(imageResource)
                    
                    // Add click listener to show full-screen image
                    setOnClickListener {
                        showFullScreenImage(imageResource, "Profile $profileIndex")
                    }
                }
                
                val profileName = TextView(this).apply {
                    text = "Profile $profileIndex"
                    textSize = 16f
                    setTextColor(android.graphics.Color.WHITE)
                    setPadding(0, 16, 0, 0)
                    gravity = Gravity.CENTER
                }
                
                val profileAge = TextView(this).apply {
                    text = "${20 + profileIndex} years old"
                    textSize = 14f
                    setTextColor(android.graphics.Color.LTGRAY)
                    setPadding(0, 4, 0, 0)
                    gravity = Gravity.CENTER
                }
                
                profileContainer.addView(profileImage)
                profileContainer.addView(profileName)
                profileContainer.addView(profileAge)
                
                rowLayout.addView(profileContainer)
            }
            
            profileGrid.addView(rowLayout)
        }
        
        // Add all components
        mainLayout.addView(backButton)
        mainLayout.addView(titleText)
        mainLayout.addView(subtitleText)
        mainLayout.addView(profileGrid)
        
        scrollView.addView(mainLayout)
        setContentView(scrollView)
    }
    
    private fun showFullScreenImage(imageResource: Int, profileName: String) {
        // Create overlay container
        val overlay = FrameLayout(this).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            setBackgroundColor(android.graphics.Color.parseColor("#80000000")) // Semi-transparent black
        }
        
        // Create the enlarged image
        val fullScreenImage = ImageView(this).apply {
            layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
            ).apply {
                gravity = Gravity.CENTER
                setMargins(200, 300, 200, 300) // 20% of screen with larger margins
            }
            scaleType = ImageView.ScaleType.CENTER_CROP
            setImageResource(imageResource)
            setBackgroundColor(android.graphics.Color.WHITE)
        }
        
        // Create profile name text
        val profileNameText = TextView(this).apply {
            text = profileName
            textSize = 24f
            setTextColor(android.graphics.Color.WHITE)
            gravity = Gravity.CENTER
            layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                gravity = Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
                setMargins(0, 0, 0, 100)
            }
        }
        
        // Add click listener to dismiss
        overlay.setOnClickListener {
            // Remove the overlay
            val rootView = findViewById<ViewGroup>(android.R.id.content)
            rootView.removeView(overlay)
        }
        
        // Add components to overlay
        overlay.addView(fullScreenImage)
        overlay.addView(profileNameText)
        
        // Add overlay to the root view
        val rootView = findViewById<ViewGroup>(android.R.id.content)
        rootView.addView(overlay)
    }
}
