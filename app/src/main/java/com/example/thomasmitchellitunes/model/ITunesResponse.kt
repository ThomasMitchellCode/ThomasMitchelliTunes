package com.example.thomasmitchellitunes.model

data class ITunesResponse(
    val results: List<Song>
)

// root response, se we will get a RandomSong from our network call
data class Song(
    val artistName: String,
    val trackName: String,
    val collectionName: String,
    val artworkUrl60: String,
    val trackPrice: String,
    // variable names must match JSon names EXACTLY! (casing must match)
    val previewUrl: String
)
