package com.togglematch

data class Persona(
    val name: String,
    val age: Int,
    val occupation: String,
    val bio: String,
    val imageResource: Int,
    val interests: List<String>,
    val tier: String = "Standard"
)
