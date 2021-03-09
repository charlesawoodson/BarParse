package com.charlesawoodson.barparse.contents.adapters.holders

import androidx.recyclerview.widget.RecyclerView
import com.charlesawoodson.barparse.contents.responses.Album
import com.charlesawoodson.barparse.databinding.ListItemAlbumBinding

class AlbumViewHolder(private val binding: ListItemAlbumBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Album) {
        binding.apply {
            albumName = item.albumName
            artistName = item.artistName
            albumReleaseYear = item.albumReleaseDate
            isExplicit = item.explicit == 1
        }
    }
}