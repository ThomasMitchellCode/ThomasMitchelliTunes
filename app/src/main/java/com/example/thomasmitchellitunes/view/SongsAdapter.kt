package com.example.thomasmitchellitunes.view

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.thomasmitchellitunes.R
import com.example.thomasmitchellitunes.model.Song
import com.squareup.picasso.Picasso

class SongsAdapter(private val list: List<Song>): RecyclerView.Adapter<SongsAdapter.SongsViewHolder>() {

    inner class SongsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(song: Song) {

            val artistName: TextView = itemView.findViewById(R.id.tv_artist_name)
            val songName: TextView = itemView.findViewById(R.id.tv_song_name)
            val albumName: TextView = itemView.findViewById(R.id.tv_album_name)
            val songPrice: TextView = itemView.findViewById(R.id.tv_price)

            val albumArt: ImageView = itemView.findViewById(R.id.iv_song_artwork)

            artistName.text = song.artistName
            songName.text = song.trackName
            albumName.text = song.collectionName
            songPrice.text = song.trackPrice

            Picasso.get()
                .load(song.artworkUrl60)
                .placeholder(R.drawable.ic_launcher_foreground)
                .fit()
                .into(albumArt)

            itemView.setOnClickListener {
                val intent = Intent()
                intent.action = Intent.ACTION_VIEW
                intent.setDataAndType(Uri.parse(song.previewUrl), "audio/*")
                itemView.context.startActivity(intent)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongsViewHolder {
        val listItem = LayoutInflater.from(parent.context).inflate(R.layout.song_card_item, parent, false)

        return SongsViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: SongsViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

}