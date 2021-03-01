package com.charlesawoodson.barparse.contents.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.charlesawoodson.barparse.R
import com.charlesawoodson.barparse.contents.adapters.loading.TopTracksLoadingAdapter
import com.charlesawoodson.barparse.contents.adapters.paging.TopTracksPagingAdapter
import com.charlesawoodson.barparse.contents.model.Track
import com.charlesawoodson.barparse.contents.viewmodels.MainViewModel
import com.pandora.bottomnavigator.BottomNavigator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_top_tracks.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TopTracksFragment : Fragment(), TopTracksPagingAdapter.OnTrackItemClickListener {

    private lateinit var navigator: BottomNavigator

    private val viewModel: MainViewModel by viewModels()

    private val tracksAdapter = TopTracksPagingAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fetchTopTracks()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_top_tracks, container, false)

        navigator = BottomNavigator.provide(requireActivity())

        val tab = when (navigator.currentTab()) {
            R.id.tab1 -> 1
            R.id.tab2 -> 2
            R.id.tab3 -> 3
            else -> -1
        }
        val depth = navigator.currentStackSize()

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun fetchTopTracks() {
        lifecycleScope.launch {
            viewModel.fetchPaginatedTopTracks().collectLatest { pagingData ->
                tracksAdapter.submitData(pagingData)
            }
        }
    }

    private fun setupViews() {
        tracksRecyclerView.adapter = tracksAdapter.withLoadStateHeaderAndFooter(
            header = TopTracksLoadingAdapter { tracksAdapter.retry() },
            footer = TopTracksLoadingAdapter { tracksAdapter.retry() }
        )
    }

    override fun onTrackItemClick(track: Track) {

    }
}