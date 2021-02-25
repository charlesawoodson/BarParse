package com.charlesawoodson.barparse.contents.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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
        private val trackId: Button = itemView.trackId
        private val trackName: Button = itemView.trackName

        fun bindTrack(track: Track) {
            with(track) {
                trackId.text = "Track ID: $id"
                trackName.text = "Track Name: $name"
            }
        }
    }

}