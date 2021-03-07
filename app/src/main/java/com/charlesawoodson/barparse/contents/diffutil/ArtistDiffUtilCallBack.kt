package com.charlesawoodson.barparse.contents.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.charlesawoodson.barparse.contents.responses.Artist

class ArtistDiffUtilCallBack : DiffUtil.ItemCallback<Artist>() {

    override fun areItemsTheSame(oldItem: Artist, newItem: Artist): Boolean {
        return oldItem.artistId == newItem.artistId
    }

    override fun areContentsTheSame(oldItem: Artist, newItem: Artist): Boolean {
        return oldItem == newItem // todo: review
    }
}