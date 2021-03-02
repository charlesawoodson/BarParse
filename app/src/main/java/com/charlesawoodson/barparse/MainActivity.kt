package com.charlesawoodson.barparse

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.charlesawoodson.barparse.contents.fragments.TopArtistsFragment
import com.charlesawoodson.barparse.contents.fragments.TopTracksFragment
import com.pandora.bottomnavigator.BottomNavigator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navigator: BottomNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigator = BottomNavigator.onCreate(
            fragmentContainer = R.id.fragment_container,
            bottomNavigationView = findViewById(R.id.bottomnav_view),
            rootFragmentsFactory = mapOf(
                R.id.tab1 to { TopTracksFragment() },
                R.id.tab2 to { TopArtistsFragment() },
                R.id.tab3 to { TopTracksFragment() }
            ),
            defaultTab = R.id.tab1,
            activity = this
        )
    }

    override fun onBackPressed() {
        if (!navigator.pop()) {
            super.onBackPressed()
        }
    }
}