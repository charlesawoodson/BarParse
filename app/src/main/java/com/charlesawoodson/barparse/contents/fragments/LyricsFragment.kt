package com.charlesawoodson.barparse.contents.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.charlesawoodson.barparse.contents.extensions.args
import com.charlesawoodson.barparse.contents.responses.Track
import com.charlesawoodson.barparse.contents.viewmodels.LyricsViewModel
import com.charlesawoodson.barparse.databinding.FragmentLyricsBinding
import com.pandora.bottomnavigator.BottomNavigator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LyricsFragment : Fragment() {

    private lateinit var navigator: BottomNavigator
    private lateinit var binding: FragmentLyricsBinding
    private val viewModel: LyricsViewModel by viewModels()
    private val arguments: Track by args()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchTrackLyrics(arguments.id)
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
        navigator = BottomNavigator.provide(requireActivity())

        viewModel.lyricsLiveData.observe(viewLifecycleOwner, {
            binding.lyrics = it.message.body.lyrics.lyricsBody
        })
    }
}