package com.charlesawoodson.barparse.contents.adapters.loading

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.charlesawoodson.barparse.contents.adapters.holders.LoadingStateViewHolder
import com.charlesawoodson.barparse.databinding.ItemLoadingStateBinding

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
        holder.bind(loadState, retry)
    }
}