package com.charlesawoodson.barparse.contents.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.charlesawoodson.barparse.R
import com.charlesawoodson.barparse.contents.adapters.TracksAdapter
import com.charlesawoodson.barparse.contents.extensions.Mvi
import com.charlesawoodson.barparse.contents.extensions.args
import com.charlesawoodson.barparse.contents.responses.Album
import com.charlesawoodson.barparse.contents.responses.Track
import com.charlesawoodson.barparse.contents.viewmodels.AlbumTracksViewModel
import com.charlesawoodson.barparse.databinding.FragmentAlbumTracksBinding
import com.pandora.bottomnavigator.BottomNavigator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumTracksFragment : Fragment() {

    private lateinit var navigator: BottomNavigator
    private lateinit var binding: FragmentAlbumTracksBinding
    private val viewModel: AlbumTracksViewModel by viewModels()
    private val arguments: Album by args()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchAlbumTracks(arguments.albumId, arguments.albumTrackCount)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAlbumTracksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigator = BottomNavigator.provide(requireActivity())

        viewModel.albumTracksResponse.observe(viewLifecycleOwner, { response ->
            binding.tracksRecyclerView.adapter =
                TracksAdapter(
                    response.message.body?.trackList?.map { it.track } as ArrayList<Track>,
                    requireContext(),
                    ::navigateToLyrics
                )
        })
    }

    private fun navigateToLyrics(track: Track) {
        navigator.addFragment(
            LyricsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(Mvi.KEY_ARG, track)
                }
            }
        )
    }
}