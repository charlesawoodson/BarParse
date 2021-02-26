package com.charlesawoodson.barparse.contents.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.charlesawoodson.barparse.R
import com.charlesawoodson.barparse.contents.diffutil.TrackDiffUtilCallBack
import com.charlesawoodson.barparse.contents.model.Track
import kotlinx.android.synthetic.main.list_item_track.view.*

class TopTracksPagingAdapter :
    PagingDataAdapter<Track, TopTracksPagingAdapter.TrackViewHolder>(TrackDiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_track, parent, false)
        return TrackViewHolder(view)
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        getItem(position)?.let { holder.bindTrack(it) }
    }

    class TrackViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val trackPositionTextView: TextView = itemView.trackPositionTextView
        private val trackNameTextView: TextView = itemView.trackNameTextView
        private val artistNameTextView: TextView = itemView.artistNameTextView
        private val explicitImageView: ImageView = itemView.explicitImageView

        fun bindTrack(track: Track) {
            with(track) {
                trackPositionTextView.text = (bindingAdapterPosition + 1).toString()
                trackNameTextView.text = name
                artistNameTextView.text = artistName
                explicitImageView.isVisible = explicit == 1
            }
        }

        init {
            itemView.setOnClickListener {
                // todo: add click listener
            }
        }
    }

}