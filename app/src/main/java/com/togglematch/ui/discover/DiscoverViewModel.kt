package com.togglematch.ui.discover

import androidx.lifecycle.ViewModel
import com.togglematch.data.model.Profile
import com.togglematch.data.model.SwipeAction
import com.togglematch.data.model.SwipeType

class DiscoverViewModel : ViewModel() {
    
    private val _profiles = mutableListOf<Profile>()
    val profiles: List<Profile> get() = _profiles
    
    private val _swipeActions = mutableListOf<SwipeAction>()
    val swipeActions: List<SwipeAction> get() = _swipeActions
    
    fun addProfile(profile: Profile) {
        _profiles.add(profile)
    }
    
    fun removeProfile(profileId: String) {
        _profiles.removeAll { it.id == profileId }
    }
    
    fun addSwipeAction(swipeAction: SwipeAction) {
        _swipeActions.add(swipeAction)
    }
    
    fun getNextProfile(): Profile? {
        return _profiles.firstOrNull()
    }
    
    fun swipeProfile(profileId: String, swipeType: SwipeType) {
        val swipeAction = SwipeAction(
            type = swipeType,
            profileId = profileId,
            timestamp = System.currentTimeMillis()
        )
        addSwipeAction(swipeAction)
        removeProfile(profileId)
    }
}
