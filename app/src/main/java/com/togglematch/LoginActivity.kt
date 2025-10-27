package com.togglematch

import android.os.Bundle
import android.widget.*
import android.view.ViewGroup
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.graphics.Color
import android.view.View

class LoginActivity : AppCompatActivity() {
    
    private var selectedPersona: Persona? = null
    private lateinit var loginButton: Button
    
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
        
        // Title
        val titleText = TextView(this).apply {
            text = "Choose Your Profile"
            textSize = 28f
            setTextColor(android.graphics.Color.WHITE)
            gravity = Gravity.CENTER
            setPadding(0, 0, 0, 16)
        }
        
        // Subtitle
        val subtitleText = TextView(this).apply {
            text = "Select a persona to start your toggling journey"
            textSize = 16f
            setTextColor(android.graphics.Color.WHITE)
            gravity = Gravity.CENTER
            setPadding(0, 0, 0, 32)
        }
        
        // Create persona selection layout
        val personaLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER_HORIZONTAL
        }
        
        // Add persona cards
        PersonaData.personas.forEach { persona ->
            val personaCard = createPersonaCard(persona)
            personaLayout.addView(personaCard)
        }
        
        // Login button
        loginButton = Button(this).apply {
            text = "Login with Selected Profile"
            setBackgroundColor(resources.getColor(R.color.launchdarkly_purple, null))
            setTextColor(android.graphics.Color.WHITE)
            setPadding(32, 16, 32, 16)
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, 32, 0, 0)
            }
            isEnabled = false
            setOnClickListener {
                selectedPersona?.let { persona ->
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    intent.putExtra("selected_persona", persona.name)
                    startActivity(intent)
                    finish()
                }
            }
        }
        
        // Add all components to main layout
        mainLayout.addView(titleText)
        mainLayout.addView(subtitleText)
        mainLayout.addView(personaLayout)
        mainLayout.addView(loginButton)
        
        scrollView.addView(mainLayout)
        setContentView(scrollView)
    }
    
    private fun createPersonaCard(persona: Persona): LinearLayout {
        val cardLayout = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
            setPadding(16, 16, 16, 16)
            setBackgroundColor(Color.parseColor("#1A1A1A"))
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, 0, 0, 16)
            }
        }
        
        // Persona image
        val imageView = ImageView(this).apply {
            setImageResource(persona.imageResource)
            layoutParams = LinearLayout.LayoutParams(80, 80).apply {
                setMargins(0, 0, 16, 0)
            }
        }
        
        // Persona info layout
        val infoLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        }
        
        // Name and age
        val nameText = TextView(this).apply {
            text = "${persona.name}, ${persona.age}"
            textSize = 18f
            setTextColor(android.graphics.Color.WHITE)
            setTypeface(null, android.graphics.Typeface.BOLD)
        }
        
        // Occupation
        val occupationText = TextView(this).apply {
            text = persona.occupation
            textSize = 14f
            setTextColor(android.graphics.Color.parseColor("#B0B0B0"))
            setPadding(0, 4, 0, 4)
        }
        
        // Bio
        val bioText = TextView(this).apply {
            text = persona.bio
            textSize = 12f
            setTextColor(android.graphics.Color.parseColor("#808080"))
            maxLines = 2
        }
        
        // Tier badge
        val tierText = TextView(this).apply {
            text = persona.tier
            textSize = 10f
            setTextColor(android.graphics.Color.WHITE)
            setBackgroundColor(when (persona.tier) {
                "Platinum" -> Color.parseColor("#FFD700")
                "Beta" -> Color.parseColor("#4CAF50")
                "Developer" -> Color.parseColor("#2196F3")
                else -> Color.parseColor("#666666")
            })
            setPadding(8, 4, 8, 4)
            gravity = Gravity.CENTER
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, 4, 0, 0)
            }
        }
        
        infoLayout.addView(nameText)
        infoLayout.addView(occupationText)
        infoLayout.addView(bioText)
        infoLayout.addView(tierText)
        
        cardLayout.addView(imageView)
        cardLayout.addView(infoLayout)
        
        // Add click listener
        cardLayout.setOnClickListener {
            selectPersona(persona, cardLayout)
        }
        
        return cardLayout
    }
    
    private fun selectPersona(persona: Persona, cardLayout: LinearLayout) {
        // Reset all cards
        val parent = cardLayout.parent as ViewGroup
        for (i in 0 until parent.childCount) {
            val child = parent.getChildAt(i)
            if (child is LinearLayout) {
                child.setBackgroundColor(Color.parseColor("#1A1A1A"))
            }
        }
        
        // Highlight selected card
        cardLayout.setBackgroundColor(Color.parseColor("#333333"))
        selectedPersona = persona
        
        // Enable login button
        loginButton.isEnabled = true
    }
}
