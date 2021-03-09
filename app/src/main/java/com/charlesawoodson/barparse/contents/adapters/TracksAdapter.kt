package com.charlesawoodson.barparse.contents.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.charlesawoodson.barparse.contents.adapters.holders.TrackViewHolder
import com.charlesawoodson.barparse.contents.responses.Track
import com.charlesawoodson.barparse.databinding.ListItemTrackBinding

class TracksAdapter(
    private val items: ArrayList<Track>,
    private val context: Context,
    private val itemListener: (track: Track) -> Unit
) : RecyclerView.Adapter<TrackViewHolder>() {

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ListItemTrackBinding.inflate(inflater, parent, false)
        return TrackViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener {
            itemListener(items[position])
        }
    }
}