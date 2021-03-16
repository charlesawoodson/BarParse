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
import com.charlesawoodson.barparse.databinding.FragmentExploreBinding
import com.charlesawoodson.barparse.databinding.FragmentLyricsBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ExploreFragment : Fragment() {

    private lateinit var binding: FragmentExploreBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExploreBinding.inflate(inflater, container, false)
        return binding.root
    }
}