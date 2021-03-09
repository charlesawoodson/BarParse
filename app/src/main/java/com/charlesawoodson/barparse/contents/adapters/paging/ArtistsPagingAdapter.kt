package com.charlesawoodson.barparse.contents.adapters.paging

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.charlesawoodson.barparse.contents.adapters.diffutil.ArtistDiffUtilCallBack
import com.charlesawoodson.barparse.contents.adapters.holders.ArtistViewHolder
import com.charlesawoodson.barparse.contents.responses.Artist
import com.charlesawoodson.barparse.databinding.ListItemArtistBinding

class ArtistsPagingAdapter(
    private val listener: OnArtistItemClickListener,
    private val context: Context
) : PagingDataAdapter<Artist, ArtistViewHolder>(ArtistDiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ListItemArtistBinding.inflate(inflater, parent, false)
        return ArtistViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        getItem(position)?.let { item ->
            holder.bind(item)
            holder.itemView.setOnClickListener {
                listener.onArtistItemClick(item)
            }
        }
    }

    interface OnArtistItemClickListener {
        fun onArtistItemClick(artist: Artist)
    }
}