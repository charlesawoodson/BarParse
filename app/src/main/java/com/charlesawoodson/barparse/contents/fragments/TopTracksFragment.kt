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
import com.charlesawoodson.barparse.contents.adapters.paging.TracksPagingAdapter
import com.charlesawoodson.barparse.contents.extensions.Mvi
import com.charlesawoodson.barparse.contents.responses.Track
import com.charlesawoodson.barparse.contents.viewmodels.TopTracksViewModel
import com.charlesawoodson.barparse.databinding.FragmentAlbumTracksBinding
import com.charlesawoodson.barparse.databinding.FragmentTopTracksBinding
import com.pandora.bottomnavigator.BottomNavigator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_top_tracks.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TopTracksFragment : Fragment(), TracksPagingAdapter.OnTrackItemClickListener {

    private lateinit var navigator: BottomNavigator
    private lateinit var binding: FragmentTopTracksBinding
    private val viewModel: TopTracksViewModel by viewModels()

    private val tracksAdapter by lazy(LazyThreadSafetyMode.NONE) {
        TracksPagingAdapter(this, requireContext())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fetchTopTracks()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTopTracksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigator = BottomNavigator.provide(requireActivity())

        binding.tracksRecyclerView.adapter = tracksAdapter.withLoadStateHeaderAndFooter(
            header = ListItemsLoadingAdapter { tracksAdapter.retry() },
            footer = ListItemsLoadingAdapter { tracksAdapter.retry() }
        )
    }

    private fun fetchTopTracks() {
        lifecycleScope.launch {
            viewModel.fetchPaginatedTopTracks().collectLatest { pagingData ->
                tracksAdapter.submitData(pagingData)
            }
        }
    }

    override fun onTrackItemClick(track: Track) {
        navigator.addFragment(
            LyricsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(Mvi.KEY_ARG, track)
                }
            }
        )
    }
}