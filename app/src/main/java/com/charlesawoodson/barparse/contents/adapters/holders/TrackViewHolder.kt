package com.charlesawoodson.barparse.contents.adapters.holders

import androidx.recyclerview.widget.RecyclerView
import com.charlesawoodson.barparse.contents.responses.Track
import com.charlesawoodson.barparse.databinding.ListItemTrackBinding

class TrackViewHolder(private val binding: ListItemTrackBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Track) {
        binding.apply {
            trackOrder = (bindingAdapterPosition + 1).toString()
            trackName = item.name
            artistName = item.artistName
            isExplicit = item.explicit == 1
        }
    }
}