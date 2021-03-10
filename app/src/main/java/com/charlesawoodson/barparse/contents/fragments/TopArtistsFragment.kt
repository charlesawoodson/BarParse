package com.charlesawoodson.barparse.contents.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.charlesawoodson.barparse.contents.adapters.loading.ListItemsLoadingAdapter
import com.charlesawoodson.barparse.contents.adapters.paging.ArtistsPagingAdapter
import com.charlesawoodson.barparse.contents.extensions.Mvi
import com.charlesawoodson.barparse.contents.responses.Artist
import com.charlesawoodson.barparse.contents.viewmodels.TopArtistsViewModel
import com.charlesawoodson.barparse.databinding.FragmentRecyclerViewBinding
import com.pandora.bottomnavigator.BottomNavigator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TopArtistsFragment : Fragment(), ArtistsPagingAdapter.OnArtistItemClickListener {

    private lateinit var navigator: BottomNavigator
    private lateinit var binding: FragmentRecyclerViewBinding
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
    ): View {
        binding = FragmentRecyclerViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigator = BottomNavigator.provide(requireActivity())

        binding.itemsRecyclerView.adapter = artistsAdapter.withLoadStateHeaderAndFooter(
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
            }
        )
    }
}