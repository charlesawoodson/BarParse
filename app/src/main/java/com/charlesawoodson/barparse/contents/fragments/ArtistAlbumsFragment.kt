package com.charlesawoodson.barparse.contents.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.charlesawoodson.barparse.contents.adapters.loading.ListItemsLoadingAdapter
import com.charlesawoodson.barparse.contents.adapters.paging.AlbumsPagingAdapter
import com.charlesawoodson.barparse.contents.responses.Album
import com.charlesawoodson.barparse.contents.viewmodels.ArtistAlbumsViewModel
import com.charlesawoodson.barparse.databinding.FragmentRecyclerViewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ArtistAlbumsFragment : Fragment(), AlbumsPagingAdapter.OnAlbumItemClickListener {

    private lateinit var binding: FragmentRecyclerViewBinding
    private val viewModel: ArtistAlbumsViewModel by viewModels()
    private val arguments: ArtistAlbumsFragmentArgs by navArgs()

    private val albumsAdapter by lazy(LazyThreadSafetyMode.NONE) {
        AlbumsPagingAdapter(this, requireContext())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fetchArtistAlbums(arguments.Artist.artistId)
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

        binding.itemsRecyclerView.adapter = albumsAdapter.withLoadStateHeaderAndFooter(
            header = ListItemsLoadingAdapter(requireContext()) { albumsAdapter.retry() },
            footer = ListItemsLoadingAdapter(requireContext()) { albumsAdapter.retry() }
        )
    }

    private fun fetchArtistAlbums(artistId: String) {
        lifecycleScope.launch {
            viewModel.fetchPaginatedArtistAlbums(artistId).collectLatest { pagingData ->
                albumsAdapter.submitData(pagingData)
            }
        }
    }

    override fun onAlbumItemClick(album: Album) {
        val action =
            ArtistAlbumsFragmentDirections.actionArtistAlbumsFragmentToAlbumTracksFragment(album)
        findNavController().navigate(action)
    }
}