package com.charlesawoodson.barparse.contents.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.charlesawoodson.barparse.contents.viewmodels.LyricsViewModel
import com.charlesawoodson.barparse.databinding.FragmentLyricsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LyricsFragment : Fragment() {

    private lateinit var binding: FragmentLyricsBinding
    private val viewModel: LyricsViewModel by viewModels()
    private val arguments: LyricsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchTrackLyrics(arguments.Track.id)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLyricsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.lyricsLiveData.observe(viewLifecycleOwner, {
            binding.lyrics = it.message.body.lyrics.lyricsBody
        })
    }
}