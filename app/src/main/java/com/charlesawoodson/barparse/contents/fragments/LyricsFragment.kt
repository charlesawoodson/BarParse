package com.charlesawoodson.barparse.contents.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.charlesawoodson.barparse.R
import com.charlesawoodson.barparse.contents.extensions.args
import com.charlesawoodson.barparse.contents.model.Track
import com.charlesawoodson.barparse.contents.viewmodels.LyricsViewModel
import com.pandora.bottomnavigator.BottomNavigator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_lyrics.*

@AndroidEntryPoint
class LyricsFragment : Fragment() {

    private val viewModel: LyricsViewModel by viewModels()

    private lateinit var navigator: BottomNavigator

    private val arguments: Track by args()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.fetchTrackLyrics(arguments.id)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lyrics, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigator = BottomNavigator.provide(requireActivity())

        viewModel.lyricsLiveData.observe(viewLifecycleOwner, {
            lyricsTextView.text = it.message.body.lyrics.lyricsBody
        })
    }
}