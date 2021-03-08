package com.charlesawoodson.barparse.contents.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.charlesawoodson.barparse.R
import com.charlesawoodson.barparse.contents.extensions.args
import com.charlesawoodson.barparse.contents.responses.Album
import com.charlesawoodson.barparse.contents.viewmodels.AlbumTracksViewModel
import com.pandora.bottomnavigator.BottomNavigator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumTracksFragment : Fragment() {

    private val viewModel: AlbumTracksViewModel by viewModels()

    private lateinit var navigator: BottomNavigator

    private val arguments: Album by args()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.fetchAlbumTracks(arguments.albumId, arguments.albumTrackCount)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_album_tracks, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigator = BottomNavigator.provide(requireActivity())

        viewModel.albumTracksLiveData.observe(viewLifecycleOwner, {
            Log.d("TrackersPlus", it.message.body.trackList.toString())
        })
    }
}