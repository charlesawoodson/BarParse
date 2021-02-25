package com.charlesawoodson.barparse

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.charlesawoodson.barparse.contents.adapters.TopTracksPagingAdapter
import com.charlesawoodson.barparse.contents.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    private val tracksAdapter = TopTracksPagingAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()
        fetchTopTracks()
    }

    private fun fetchTopTracks() {
        lifecycleScope.launch {
            viewModel.fetchPaginatedTopTracks().collectLatest { pagingData ->
                tracksAdapter.submitData(pagingData)
            }
        }
    }

    private fun setupViews() {
        tracksRecyclerView.adapter = tracksAdapter
        // tracksRecyclerView.adapter = tracksAdapter todo: loader and error
    }
}