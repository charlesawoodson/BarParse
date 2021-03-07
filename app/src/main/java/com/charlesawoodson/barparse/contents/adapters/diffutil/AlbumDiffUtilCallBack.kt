package com.charlesawoodson.barparse.contents.adapters.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.charlesawoodson.barparse.contents.responses.Album
import com.charlesawoodson.barparse.contents.responses.Artist

class AlbumDiffUtilCallBack : DiffUtil.ItemCallback<Album>() {

    override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean {
        return oldItem.albumId == newItem.albumId
    }

    override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean {
        return oldItem == newItem
    }
}