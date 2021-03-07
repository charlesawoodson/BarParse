package com.charlesawoodson.barparse.contents.adapters.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.charlesawoodson.barparse.R
import com.charlesawoodson.barparse.contents.diffutil.ArtistDiffUtilCallBack
import com.charlesawoodson.barparse.contents.responses.Artist
import kotlinx.android.synthetic.main.list_item_artist.view.*

class TopArtistsPagingAdapter(private val listener: OnArtistItemClickListener) :
    PagingDataAdapter<Artist, TopArtistsPagingAdapter.ArtistViewHolder>(ArtistDiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_artist, parent, false)
        return ArtistViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        getItem(position)?.let { holder.bindArtist(it) }
    }

    class ArtistViewHolder(itemView: View, private val listener: OnArtistItemClickListener) :
        RecyclerView.ViewHolder(itemView) {
        private val artistNameTextView: TextView = itemView.artistNameTextView
        private val artistRatingTextView: TextView = itemView.artistRatingTextView

        fun bindArtist(artist: Artist) {
            with(artist) {
                artistNameTextView.text = artistName
                artistRatingTextView.text = artistRating.toString()
            }

            itemView.setOnClickListener {
                listener.onArtistItemClick(artist)
            } // todo: set on click in init
        }
    }

    interface OnArtistItemClickListener {
        fun onArtistItemClick(artist: Artist)
    }
}