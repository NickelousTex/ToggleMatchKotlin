package com.togglematch

import android.app.Application
import com.launchdarkly.sdk.LDContext
import com.launchdarkly.sdk.android.LDClient
import com.launchdarkly.sdk.android.LDConfig

class ToggleMatchApplication : Application() {
    
    companion object {
        lateinit var ldClient: LDClient
            private set
            
        fun updateUserContext(persona: Persona) {
            val context = LDContext.builder(persona.name.lowercase())
                .name(persona.name)
                .set("age", persona.age)
                .set("occupation", persona.occupation)
                .set("tier", persona.tier)
                .set("interests", persona.interests.joinToString(","))
                .build()
            
            ldClient.identify(context)
        }
    }
    
    override fun onCreate() {
        super.onCreate()
        
        // Initialize LaunchDarkly client
        val config = LDConfig.Builder()
            .mobileKey("<mob-your-mobile-key>")
            .build()
        
        val context = LDContext.builder("user-key")
            .name("Anonymous User")
            .build()
        
        ldClient = LDClient.init(this, config, context, 5)
    }
}
