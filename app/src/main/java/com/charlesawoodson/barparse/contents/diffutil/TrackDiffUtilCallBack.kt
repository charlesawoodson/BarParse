package com.charlesawoodson.barparse.contents.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.charlesawoodson.barparse.contents.model.Track

class TrackDiffUtilCallBack : DiffUtil.ItemCallback<Track>() {

    override fun areItemsTheSame(oldItem: Track, newItem: Track): Boolean {
        return oldItem.trackId == newItem.trackId
    }

    override fun areContentsTheSame(oldItem: Track, newItem: Track): Boolean {
        return oldItem == newItem // todo: review
    }
}