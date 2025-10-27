package com.togglematch.ui.profile

import androidx.lifecycle.ViewModel
import com.togglematch.data.model.Profile

class ProfileViewModel : ViewModel() {
    
    private var _userProfile: Profile? = null
    val userProfile: Profile? get() = _userProfile
    
    fun setUserProfile(profile: Profile) {
        _userProfile = profile
    }
    
    fun updateProfile(updatedProfile: Profile) {
        _userProfile = updatedProfile
    }
    
    fun updateBio(bio: String) {
        _userProfile = _userProfile?.copy(bio = bio)
    }
    
    fun updateInterests(interests: List<String>) {
        _userProfile = _userProfile?.copy(interests = interests)
    }
    
    fun updatePhotos(photos: List<String>) {
        _userProfile = _userProfile?.copy(photos = photos)
    }
}
