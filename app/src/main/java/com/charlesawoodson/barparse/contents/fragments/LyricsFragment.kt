package com.charlesawoodson.barparse.contents.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuItemCompat
import androidx.core.view.MenuItemCompat.getActionView
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
            binding.lyrics = it.message.body.lyrics.lyricsBody
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.lyrics_menu, menu)
        val searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.setOnCloseListener { false }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.d("onQueryTextChange", newText ?: "")
                return true
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        (activity as MainActivity).supportActionBar?.title = arguments.Track.name

        super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }
}