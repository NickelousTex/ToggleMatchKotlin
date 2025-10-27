package com.togglematch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.widget.LinearLayout
import android.widget.Button
import android.widget.ImageView
import android.view.ViewGroup
import android.view.Gravity
import android.content.Intent
import android.net.Uri
import android.view.View

class MainActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Get selected persona from intent and update LaunchDarkly context
        val selectedPersonaName = intent.getStringExtra("selected_persona")
        selectedPersonaName?.let { personaName ->
            val persona = PersonaData.personas.find { it.name == personaName }
            persona?.let { ToggleMatchApplication.updateUserContext(it) }
        }
        
        // Create a simple dating app layout programmatically
        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER_HORIZONTAL
            setPadding(32, 32, 32, 32)
        }
        
        // Create horizontal layout for title and logo
        val titleLayout = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
            setPadding(0, 0, 0, 32)
        }
        
        val titleText1 = TextView(this).apply {
            text = "T"
            textSize = 36f // 30% bigger (28 * 1.3)
            setTextColor(android.graphics.Color.WHITE)
        }
        
        // Add the toggle heart logo (replacing the "o")
        val logoImageView = ImageView(this).apply {
            setImageResource(R.drawable.toggle_heart_logo)
            layoutParams = ViewGroup.LayoutParams(156, 78) // 30% bigger (120*1.3, 60*1.3)
            scaleType = ImageView.ScaleType.CENTER_INSIDE
        }
        
        val titleText2 = TextView(this).apply {
            text = "ggleMatch"
            textSize = 36f // 30% bigger (28 * 1.3)
            setTextColor(android.graphics.Color.WHITE)
        }
        
        val subtitleText = TextView(this).apply {
            text = "Find your perfect match!"
            textSize = 23f // 30% bigger (18 * 1.3)
            setPadding(0, 0, 0, 48)
            setTextColor(android.graphics.Color.WHITE)
        }
        
        val discoverButton = Button(this).apply {
            text = "💖 Discover"
            setBackgroundColor(android.graphics.Color.WHITE)
            setTextColor(android.graphics.Color.BLACK)
            setPadding(32, 16, 32, 16)
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                width = 400 // Fixed width
                setMargins(0, 0, 0, 16) // Bottom margin for gap
            }
            setOnClickListener {
                val intent = Intent(this@MainActivity, DiscoverActivity::class.java)
                startActivity(intent)
            }
        }
        
        val matchesButton = Button(this).apply {
            text = "💕 Matches"
            setBackgroundColor(android.graphics.Color.WHITE)
            setTextColor(android.graphics.Color.BLACK)
            setPadding(32, 16, 32, 16)
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                width = 400 // Fixed width
                setMargins(0, 0, 0, 16) // Bottom margin for gap
            }
            setOnClickListener {
                // TODO: Navigate to matches screen
            }
        }
        
        val profileButton = Button(this).apply {
            text = "👤 Profile"
            setBackgroundColor(android.graphics.Color.WHITE)
            setTextColor(android.graphics.Color.BLACK)
            setPadding(32, 16, 32, 16)
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                width = 400 // Fixed width
                setMargins(0, 0, 0, 16) // Bottom margin for gap
            }
            setOnClickListener {
                // TODO: Navigate to profile screen
            }
        }
        
        // LaunchDarkly button - full width
        val launchDarklyButton = Button(this).apply {
            text = "Contact LaunchDarkly"
            setBackgroundColor(resources.getColor(R.color.launchdarkly_purple, null))
            setTextColor(android.graphics.Color.WHITE)
            setPadding(32, 16, 32, 16)
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, 0, 0, 32) // Bottom margin for version text
            }
            setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://launchdarkly.com/demo"))
                startActivity(intent)
            }
        }
        
        // Version text in bottom right
        val versionText = TextView(this).apply {
            text = "Version: 2.4.7"
            textSize = 12f
            setTextColor(android.graphics.Color.RED)
            gravity = Gravity.END // Right align
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, 16, 0, 0) // Top margin to separate from buttons
            }
        }
        
        // Add title parts and logo to horizontal layout
        titleLayout.addView(titleText1)
        titleLayout.addView(logoImageView)
        titleLayout.addView(titleText2)
        
        // Add all components to main layout
        layout.addView(titleLayout)
        layout.addView(subtitleText)
        layout.addView(discoverButton)
        
        // Check the show-matches feature flag
        val showMatches = ToggleMatchApplication.ldClient.boolVariation("show-matches", false)
        if (showMatches) {
            layout.addView(matchesButton)
        }
        
        layout.addView(profileButton)
        layout.addView(launchDarklyButton)
        layout.addView(versionText)
        
        setContentView(layout)
    }
}
