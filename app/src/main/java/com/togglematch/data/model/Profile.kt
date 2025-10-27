package com.togglematch.data.model

data class Profile(
    val id: String,
    val name: String,
    val age: Int,
    val bio: String,
    val photos: List<String>,
    val interests: List<String>,
    val location: String,
    val distance: Int,
    val verified: Boolean,
    val online: Boolean,
    val lastActive: String
)

data class SwipeAction(
    val type: SwipeType,
    val profileId: String,
    val timestamp: Long
)

enum class SwipeType {
    LIKE, PASS, SUPER_LIKE
}

data class Match(
    val id: String,
    val profiles: List<Profile>,
    val timestamp: Long,
    val messages: List<Message>
)

data class Message(
    val id: String,
    val senderId: String,
    val content: String,
    val timestamp: Long,
    val read: Boolean
)
