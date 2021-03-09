package com.charlesawoodson.barparse.contents.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.charlesawoodson.barparse.contents.responses.Track
import com.charlesawoodson.barparse.databinding.ListItemTrackBinding

class TracksAdapter(
    private val items: ArrayList<Track>,
    private val context: Context
) : RecyclerView.Adapter<ViewHolder>() {

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ListItemTrackBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }
}

class ViewHolder(private val binding: ListItemTrackBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Track) {
        binding.apply {
            trackOrder = (bindingAdapterPosition + 1).toString()
            trackName = item.name
            artistName = item.artistName
            isExplicit = item.explicit == 1
        }
    }
}