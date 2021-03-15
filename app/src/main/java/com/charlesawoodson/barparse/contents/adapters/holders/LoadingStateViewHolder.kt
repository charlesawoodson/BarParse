package com.charlesawoodson.barparse.contents.adapters.holders

import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.charlesawoodson.barparse.databinding.ItemLoadingStateBinding

class LoadingStateViewHolder(private val binding: ItemLoadingStateBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: LoadState, retry: () -> Unit) {
        binding.apply {
            isLoading = item is LoadState.Loading
            if (item is LoadState.Error) {
                errorMessage = item.error.localizedMessage
            }
            retryButton.setOnClickListener {
                retry()
            }
        }
    }
}