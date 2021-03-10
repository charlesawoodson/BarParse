package com.charlesawoodson.barparse.contents.adapters.loading

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.charlesawoodson.barparse.R
import com.charlesawoodson.barparse.contents.adapters.holders.LoadingStateViewHolder
import com.charlesawoodson.barparse.databinding.ItemLoadingStateBinding
import kotlinx.android.synthetic.main.item_loading_state.view.*

class ListItemsLoadingAdapter(
    private val context: Context,
    private val retry: () -> Unit
) : LoadStateAdapter<LoadingStateViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): LoadingStateViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ItemLoadingStateBinding.inflate(inflater, parent, false)
        return LoadingStateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LoadingStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
        holder.itemView.retryButton.setOnClickListener {
            retry()
        }
    }
}