package com.charlesawoodson.barparse.contents.adapters.paging

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.charlesawoodson.barparse.contents.adapters.diffutil.AlbumDiffUtilCallBack
import com.charlesawoodson.barparse.contents.adapters.holders.AlbumViewHolder
import com.charlesawoodson.barparse.contents.responses.Album
import com.charlesawoodson.barparse.databinding.ListItemAlbumBinding

class AlbumsPagingAdapter(
    private val listener: OnAlbumItemClickListener,
    private val context: Context
) : PagingDataAdapter<Album, AlbumViewHolder>(AlbumDiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ListItemAlbumBinding.inflate(inflater, parent, false)
        return AlbumViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        getItem(position)?.let { item ->
            holder.bind(item)
            holder.itemView.setOnClickListener {
                listener.onAlbumItemClick(item)
            }
        }
    }

    interface OnAlbumItemClickListener {
        fun onAlbumItemClick(album: Album)
    }
}