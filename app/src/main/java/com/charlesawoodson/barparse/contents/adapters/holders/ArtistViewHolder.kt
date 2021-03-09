package com.charlesawoodson.barparse.contents.adapters.holders

import androidx.recyclerview.widget.RecyclerView
import com.charlesawoodson.barparse.contents.responses.Artist
import com.charlesawoodson.barparse.databinding.ListItemArtistBinding

class ArtistViewHolder(private val binding: ListItemArtistBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Artist) {
        binding.apply {
            artistName = item.artistName
            artistRating = item.artistRating.toString()
        }
    }
}