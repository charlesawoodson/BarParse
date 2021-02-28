package com.charlesawoodson.barparse

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.charlesawoodson.barparse.contents.adapters.loading.TopTracksLoadingAdapter
import com.charlesawoodson.barparse.contents.adapters.paging.TopTracksPagingAdapter
import com.charlesawoodson.barparse.contents.model.Track
import com.charlesawoodson.barparse.contents.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), TopTracksPagingAdapter.OnTrackItemClickListener {

    private val viewModel: MainViewModel by viewModels()

    private val tracksAdapter = TopTracksPagingAdapter(this)

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
        // tracksRecyclerView.adapter = tracksAdapter
        tracksRecyclerView.adapter = tracksAdapter.withLoadStateHeaderAndFooter(
            header = TopTracksLoadingAdapter { tracksAdapter.retry() },
            footer = TopTracksLoadingAdapter { tracksAdapter.retry() }
        )
    }

    override fun onTrackItemClick(track: Track) {
        Toast.makeText(applicationContext, track.name, Toast.LENGTH_LONG).show()
    }
}