package com.charlesawoodson.barparse.contents.fragments

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.BackgroundColorSpan
import android.view.*
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.charlesawoodson.barparse.MainActivity
import com.charlesawoodson.barparse.R
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
        setHasOptionsMenu(true)
        binding = FragmentLyricsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.lyricsLiveData.observe(viewLifecycleOwner, {
            binding.lyrics = it
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.lyrics_menu, menu)
        val searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.queryHint = getString(R.string.search_lyrics)
        searchView.setOnCloseListener { false }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                setHighLightedText(newText ?: "")
                return true
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        (activity as MainActivity).supportActionBar?.title = arguments.Track.name
        super.onPrepareOptionsMenu(menu)
    }

    fun setHighLightedText(textToHighlight: String) {
        binding.lyricsTextView.text = viewModel.lyricsLiveData.value ?: ""
        val tvt = binding.lyricsTextView.text.toString()
        var ofe = tvt.indexOf(textToHighlight, 0)
        val wordToSpan: Spannable = SpannableString(binding.lyricsTextView.text)
        var ofs = 0
        while (ofs < tvt.length && ofe != -1) {
            ofe = tvt.indexOf(textToHighlight, ofs)
            if (ofe == -1) break else {
                // set color here
                wordToSpan.setSpan(
                    BackgroundColorSpan(-0x100),
                    ofe,
                    ofe + textToHighlight.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
            ofs = ofe + 1
        }
        binding.lyricsTextView.setText(wordToSpan, TextView.BufferType.SPANNABLE)
    }
}