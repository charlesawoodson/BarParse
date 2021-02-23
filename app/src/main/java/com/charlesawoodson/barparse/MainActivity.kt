package com.charlesawoodson.barparse

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.charlesawoodson.barparse.temp.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.topArtists.observe(this) {
            // update ui
            val topArtists = it.message.body.artistList.joinToString(separator = ", ") { item ->
                "${item.artist?.artistName}"
            }
            topArtistTextView.text = topArtists
        }

        viewModel.topTracks.observe(this) {
            // update ui
            val topTracks = it.message.body.trackList.joinToString(separator = ", ") { item ->
                "${item.track?.trackName}"
            }
            topTrackTextView.text = topTracks
        }
    }
}