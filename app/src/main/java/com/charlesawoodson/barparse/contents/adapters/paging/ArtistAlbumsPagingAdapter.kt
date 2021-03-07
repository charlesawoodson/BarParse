package com.charlesawoodson.barparse.contents.adapters.paging

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.charlesawoodson.barparse.R
import com.charlesawoodson.barparse.contents.adapters.diffutil.AlbumDiffUtilCallBack
import com.charlesawoodson.barparse.contents.responses.Album
import kotlinx.android.synthetic.main.list_item_album.view.*

class ArtistAlbumsPagingAdapter(private val listener: OnAlbumItemClickListener) :
    PagingDataAdapter<Album, ArtistAlbumsPagingAdapter.AlbumViewHolder>(AlbumDiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_album, parent, false)
        return AlbumViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        getItem(position)?.let { holder.bindAlbum(it) }
    }

    class AlbumViewHolder(itemView: View, private val listener: OnAlbumItemClickListener) :
        RecyclerView.ViewHolder(itemView) {
        private val context: Context = itemView.context
        private val albumArtImageView: ImageView = itemView.albumArtImageView
        private val albumNameTextView: TextView = itemView.albumNameTextView
        private val artistNameTextView: TextView = itemView.artistNameTextView
        private val releaseYearTextView: TextView = itemView.releaseYearTextView
        private val explicitImageView: ImageView = itemView.explicitImageView

        fun bindAlbum(album: Album) {
            with(album) {
                val circularProgressDrawable = CircularProgressDrawable(context)
                circularProgressDrawable.start()

                Glide.with(context)
                    .load(albumCoverArt)
                    .placeholder(circularProgressDrawable)
                    .circleCrop()
                    .into(albumArtImageView)

                albumNameTextView.text = albumName
                artistNameTextView.text = artistName
                releaseYearTextView.text = albumReleaseDate
                explicitImageView.isVisible = explicit == 1
            }

            itemView.setOnClickListener {
                listener.onAlbumItemClick(album)
            }
        }
    }

    interface OnAlbumItemClickListener {
        fun onAlbumItemClick(album: Album)
    }
}