package com.mahesh.storyaday



data class Card(val title: String, val imageRes: Int)

val GenresAndIcons = listOf(
    Card("Crime", R.drawable.crime),
    Card("Horror", R.drawable.horror),
    Card("Romance", R.drawable.romance),
    Card("Feelgood", R.drawable.feelgood),
    Card("Thriller", R.drawable.thriller),
    Card("Adventure", R.drawable.adventure),
    Card("Mystery", R.drawable.mystery),
    Card("Sci-fi", R.drawable.sci_fi),
    Card("Friendship", R.drawable.friendship),
    Card("Historical", R.drawable.historical),
    Card("Drama", R.drawable.drama),
    Card("Action", R.drawable.action),
    Card("Motivational", R.drawable.motivational),
    Card("Fantasy", R.drawable.fantasy),
)