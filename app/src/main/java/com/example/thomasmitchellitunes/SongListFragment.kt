package com.example.thomasmitchellitunes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.thomasmitchellitunes.api.ApiService
import com.example.thomasmitchellitunes.model.ITunesResponse
import com.example.thomasmitchellitunes.view.SongsAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SongListFragment : Fragment() {

    lateinit var songList: RecyclerView
    lateinit var songsAdapter: SongsAdapter
    lateinit var swipeRefresher: SwipeRefreshLayout

    private var genre : Int = CLASSIC

    companion object {
        val CLASSIC = 0
        val ROCK = 1
        val POP = 2

        val GENRE_KEY = "GENRE_KEY"

        fun newInstance(genre: Int): SongListFragment {

            val fragment = SongListFragment()
            val bundle = Bundle()

            bundle.putInt(GENRE_KEY, genre)
            fragment.arguments = bundle

            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.song_list_layout, container, false)

        songList = view.findViewById(R.id.rv_song_list)
        swipeRefresher = view.findViewById(R.id.swipe_refresher)

        getSongList(inflater, view)

        swipeRefresher.setOnRefreshListener {
            swipeRefresher.isRefreshing = true

            getSongList(inflater, view)
        }

        return view
    }

    fun getSongList(inflater: LayoutInflater, view: View) {
        genre = requireArguments().getInt(GENRE_KEY)

        when (genre) {
            CLASSIC -> startRetrofit(inflater, ApiService.createRetrofit().create(ApiService::class.java).getClassicSongs())
            ROCK -> startRetrofit(inflater, ApiService.createRetrofit().create(ApiService::class.java).getRockSongs())
            POP -> startRetrofit(inflater, ApiService.createRetrofit().create(ApiService::class.java).getPopSongs())
        }
    }

    private fun startRetrofit(inflater: LayoutInflater, call: Call<ITunesResponse>) {
        call.enqueue(object: Callback<ITunesResponse>{
            override fun onResponse(call :Call<ITunesResponse>,
                    response: Response<ITunesResponse>) {
                if (response.isSuccessful) {
                    songsAdapter = SongsAdapter(response.body()!!.results)  // tells compiler it won't be null
                    songList.adapter = songsAdapter
                    swipeRefresher.isRefreshing = false
                }
            }

            override fun onFailure(call: Call<ITunesResponse>, t: Throwable) {
                Toast.makeText(inflater.context, t.message, Toast.LENGTH_LONG).show()
                swipeRefresher.isRefreshing = false
            }
        })
    }
}