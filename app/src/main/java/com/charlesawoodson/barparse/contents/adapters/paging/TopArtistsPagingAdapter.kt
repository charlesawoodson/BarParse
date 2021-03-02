package com.charlesawoodson.barparse.contents.adapters.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.charlesawoodson.barparse.R
import com.charlesawoodson.barparse.contents.diffutil.ArtistDiffUtilCallBack
import com.charlesawoodson.barparse.contents.model.Artist

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
//        private val artistPositionTextView: TextView = itemView.artistPositionTextView
//        private val artistNameTextView: TextView = itemView.artistNameTextView
//        private val artistNameTextView: TextView = itemView.artistNameTextView
//        private val explicitImageView: ImageView = itemView.explicitImageView

        fun bindArtist(artist: Artist) {
            with(artist) {
//                artistPositionTextView.text = (bindingAdapterPosition + 1).toString()
//                artistNameTextView.text = name
//                artistNameTextView.text = artistName
//                explicitImageView.isVisible = explicit == 1
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