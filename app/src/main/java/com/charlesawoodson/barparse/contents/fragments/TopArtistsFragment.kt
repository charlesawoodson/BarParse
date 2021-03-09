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
import com.charlesawoodson.barparse.contents.adapters.paging.ArtistsPagingAdapter
import com.charlesawoodson.barparse.contents.extensions.Mvi
import com.charlesawoodson.barparse.contents.responses.Artist
import com.charlesawoodson.barparse.contents.viewmodels.TopArtistsViewModel
import com.pandora.bottomnavigator.BottomNavigator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_top_artists.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TopArtistsFragment : Fragment(), ArtistsPagingAdapter.OnArtistItemClickListener {

    private lateinit var navigator: BottomNavigator
    private val viewModel: TopArtistsViewModel by viewModels()

    private val artistsAdapter by lazy(LazyThreadSafetyMode.NONE) {
        ArtistsPagingAdapter(this, requireContext())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fetchTopArtists()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_top_artists, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigator = BottomNavigator.provide(requireActivity())

        artistsRecyclerView.adapter = artistsAdapter.withLoadStateHeaderAndFooter(
            header = ListItemsLoadingAdapter { artistsAdapter.retry() },
            footer = ListItemsLoadingAdapter { artistsAdapter.retry() }
        )
    }

    private fun fetchTopArtists() {
        lifecycleScope.launch {
            viewModel.fetchPaginatedTopArtists().collectLatest { pagingData ->
                artistsAdapter.submitData(pagingData)
            }
        }
    }

    override fun onArtistItemClick(artist: Artist) {
        navigator.addFragment(
            ArtistAlbumsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(Mvi.KEY_ARG, artist)
                }
            },
            enterAnim = R.anim.open_enter_slide,
            exitAnim = R.anim.open_exit_slide,
            popEnterAnim = R.anim.close_enter_slide,
            popExitAnim = R.anim.close_exit_slide,
        )
    }
}