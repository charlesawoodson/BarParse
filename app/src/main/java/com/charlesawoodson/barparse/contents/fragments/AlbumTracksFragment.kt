package com.charlesawoodson.barparse.contents.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.charlesawoodson.barparse.contents.adapters.TracksAdapter
import com.charlesawoodson.barparse.contents.responses.Track
import com.charlesawoodson.barparse.contents.viewmodels.AlbumTracksViewModel
import com.charlesawoodson.barparse.databinding.FragmentRecyclerViewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumTracksFragment : Fragment() {

    private lateinit var binding: FragmentRecyclerViewBinding
    private val viewModel: AlbumTracksViewModel by viewModels()
    private val arguments: AlbumTracksFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchAlbumTracks(arguments.Album.albumId, arguments.Album.albumTrackCount)
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

        viewModel.albumTracksResponse.observe(viewLifecycleOwner, { response ->
            binding.itemsRecyclerView.adapter =
                TracksAdapter(
                    response.message.body?.trackList?.map { it.track } as ArrayList<Track>,
                    requireContext(),
                    ::navigateToLyrics
                )
        })
    }

    private fun navigateToLyrics(track: Track) {
        val action = AlbumTracksFragmentDirections.actionAlbumTracksFragmentToLyricsFragment(track)
        findNavController().navigate(action)
    }
}