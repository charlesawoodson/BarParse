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
import com.charlesawoodson.barparse.contents.adapters.paging.ArtistAlbumsPagingAdapter
import com.charlesawoodson.barparse.contents.extensions.Mvi
import com.charlesawoodson.barparse.contents.extensions.args
import com.charlesawoodson.barparse.contents.responses.Album
import com.charlesawoodson.barparse.contents.responses.Artist
import com.charlesawoodson.barparse.contents.viewmodels.ArtistAlbumsViewModel
import com.pandora.bottomnavigator.BottomNavigator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_artist_albums.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ArtistAlbumsFragment : Fragment(), ArtistAlbumsPagingAdapter.OnAlbumItemClickListener {

    private lateinit var navigator: BottomNavigator

    private val viewModel: ArtistAlbumsViewModel by viewModels()

    private val arguments: Artist by args()

    private val albumsAdapter = ArtistAlbumsPagingAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fetchArtistAlbums(arguments.artistId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_artist_albums, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigator = BottomNavigator.provide(requireActivity())
        setupViews()
    }

    private fun fetchArtistAlbums(artistId: String) {
        lifecycleScope.launch {
            viewModel.fetchPaginatedArtistAlbums(artistId).collectLatest { pagingData ->
                albumsAdapter.submitData(pagingData)
            }
        }
    }

    private fun setupViews() {
        albumsRecyclerView.adapter = albumsAdapter.withLoadStateHeaderAndFooter(
            header = ListItemsLoadingAdapter { albumsAdapter.retry() },
            footer = ListItemsLoadingAdapter { albumsAdapter.retry() }
        )
    }

    override fun onAlbumItemClick(album: Album) {
        navigator.addFragment(
            AlbumTracksFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(Mvi.KEY_ARG, album)
                }
            },
            enterAnim = R.anim.open_enter_slide,
            exitAnim = R.anim.open_exit_slide,
            popEnterAnim = R.anim.close_enter_slide,
            popExitAnim = R.anim.close_exit_slide,
        )
    }
}