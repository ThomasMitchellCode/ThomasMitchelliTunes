package com.example.thomasmitchellitunes.api

import com.example.thomasmitchellitunes.model.ITunesResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

//Classic Tab should load:
//https://itunes.apple.com/search?term=classick&amp;media=music&amp;entity=song&amp;limit=50
//Pop Tab should load:
//https://itunes.apple.com/search?term=pop&amp;media=music&amp;entity=song&amp;limit=50
//Rock Tab should load:
//https://itunes.apple.com/search?term=rock&amp;media=music&amp;entity=song&amp;limit=50

interface ApiService {

    @GET("search?term=classic&amp;media=music&amp;entity=song&amp;limit=50")
    fun getClassicSongs(): Call<ITunesResponse>

    @GET("search?term=rock&amp;media=music&amp;entity=song&amp;limit=50")
    fun getRockSongs(): Call<ITunesResponse>

    @GET("search?term=pop&amp;media=music&amp;entity=song&amp;limit=50")
    fun getPopSongs(): Call<ITunesResponse>

    companion object {
        private var instance: Retrofit? = null

        fun createRetrofit(): Retrofit {
            if (instance == null) {
                instance = Retrofit.Builder()
                    .baseUrl("https://itunes.apple.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return instance!! // !! -> this will NOT be null at compile time
        }
    }
}