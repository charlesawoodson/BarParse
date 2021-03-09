package com.charlesawoodson.barparse.contents.adapters.paging

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.charlesawoodson.barparse.contents.adapters.diffutil.TrackDiffUtilCallBack
import com.charlesawoodson.barparse.contents.adapters.holders.TrackViewHolder
import com.charlesawoodson.barparse.contents.responses.Track
import com.charlesawoodson.barparse.databinding.ListItemTrackBinding

class TracksPagingAdapter(
    private val listener: OnTrackItemClickListener,
    private val context: Context
) : PagingDataAdapter<Track, TrackViewHolder>(TrackDiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ListItemTrackBinding.inflate(inflater, parent, false)
        return TrackViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        getItem(position)?.let { item ->
            holder.bind(item)
            holder.itemView.setOnClickListener {
                listener.onTrackItemClick(item)
            }
        }
    }

    interface OnTrackItemClickListener {
        fun onTrackItemClick(track: Track)
    }
}