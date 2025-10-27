package com.togglematch

import com.togglematch.R

object PersonaData {
    val personas = listOf(
        Persona(
            name = "Alex",
            age = 28,
            occupation = "Software Engineer",
            bio = "Love hiking, coffee, and late-night coding sessions. Looking for someone to explore the city with!",
            imageResource = R.drawable.persona_alex,
            interests = listOf("Technology", "Hiking", "Coffee", "Travel"),
            tier = "Standard"
        ),
        Persona(
            name = "Jordan",
            age = 25,
            occupation = "Graphic Designer",
            bio = "Creative soul who loves art galleries, indie music, and trying new restaurants. Let's create memories together!",
            imageResource = R.drawable.persona_jordan,
            interests = listOf("Art", "Music", "Food", "Photography"),
            tier = "Platinum"
        ),
        Persona(
            name = "Sam",
            age = 30,
            occupation = "Marketing Manager",
            bio = "Fitness enthusiast and travel lover. Always up for an adventure or a good book discussion.",
            imageResource = R.drawable.persona_sam,
            interests = listOf("Fitness", "Travel", "Reading", "Cooking"),
            tier = "Standard"
        ),
        Persona(
            name = "Taylor",
            age = 27,
            occupation = "Teacher",
            bio = "Passionate about education and making a difference. Love dogs, board games, and weekend getaways.",
            imageResource = R.drawable.persona_taylor,
            interests = listOf("Education", "Animals", "Games", "Nature"),
            tier = "Beta"
        ),
        Persona(
            name = "Casey",
            age = 29,
            occupation = "Data Scientist",
            bio = "Numbers person by day, foodie by night. Love trying new cuisines and solving puzzles together.",
            imageResource = R.drawable.persona_casey,
            interests = listOf("Data", "Food", "Puzzles", "Movies"),
            tier = "Developer"
        )
    )
}
