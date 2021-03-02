package com.charlesawoodson.barparse.contents.adapters.loading

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
import kotlinx.android.synthetic.main.item_loading_state.view.*

class ListItemsLoadingAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<ListItemsLoadingAdapter.LoadingStateViewHolder>() {

    class LoadingStateViewHolder(itemView: View, retry: () -> Unit) :
        RecyclerView.ViewHolder(itemView) {

        private val errorMessageTextView: TextView = itemView.errorMessageTextView
        private val progressBar: ProgressBar = itemView.progressBar
        private val retryButton: Button = itemView.retryButton

        init {
            retryButton.setOnClickListener {
                retry.invoke()
            }
        }

        fun bindState(loadState: LoadState) {
            if (loadState is LoadState.Error) {
                errorMessageTextView.text = loadState.error.localizedMessage
            }
            progressBar.isVisible = loadState is LoadState.Loading
            errorMessageTextView.isVisible = loadState !is LoadState.Loading
            retryButton.isVisible = loadState != LoadState.Loading
        }
    }

    override fun onBindViewHolder(holder: LoadingStateViewHolder, loadState: LoadState) {
        holder.bindState(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): LoadingStateViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_loading_state, parent, false)
        return LoadingStateViewHolder(view, retry)
    }
}