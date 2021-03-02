package com.charlesawoodson.barparse.contents.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.charlesawoodson.barparse.R
import com.charlesawoodson.barparse.contents.adapters.loading.ListItemsLoadingAdapter
import com.charlesawoodson.barparse.contents.adapters.paging.TopTracksPagingAdapter
import com.charlesawoodson.barparse.contents.extensions.Mvi
import com.charlesawoodson.barparse.contents.model.Track
import com.charlesawoodson.barparse.contents.viewmodels.TopTracksViewModel
import com.pandora.bottomnavigator.BottomNavigator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_top_tracks.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TopTracksFragment : Fragment(), TopTracksPagingAdapter.OnTrackItemClickListener {

    private lateinit var navigator: BottomNavigator

    private val viewModel: TopTracksViewModel by viewModels()

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
        return inflater.inflate(R.layout.fragment_top_tracks, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigator = BottomNavigator.provide(requireActivity())
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
            header = ListItemsLoadingAdapter { tracksAdapter.retry() },
            footer = ListItemsLoadingAdapter { tracksAdapter.retry() }
        )
    }

    override fun onTrackItemClick(track: Track) {
        navigator.addFragment(
            LyricsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(Mvi.KEY_ARG, track)
                }
            },
            enterAnim = R.anim.open_enter_slide,
            exitAnim = R.anim.open_exit_slide,
            popEnterAnim = R.anim.close_enter_slide,
            popExitAnim = R.anim.close_exit_slide,
        )
    }
}