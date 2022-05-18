package com.example.thomasmitchellitunes

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.content.res.AppCompatResources
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        viewPager = findViewById(R.id.viewpager)
        tabLayout = findViewById(R.id.tab_layout)

        viewPager.adapter = ViewPagerAdapter(this)

        TabLayoutMediator(tabLayout, viewPager) {
            tab, index ->
                tab.text = when(index) {
                    0 -> {"Classic"}
                    1 -> {"Rock"}
                    2 -> {"Pop"}
                    else -> {throw Resources.NotFoundException("Tab not found")}
                }

            tab.icon = when(index) {
                0 -> {AppCompatResources.getDrawable(this, R.drawable.ic_treble_clef)}
                1 -> {AppCompatResources.getDrawable(this, R.drawable.ic_drum_kit)}
                2 -> {AppCompatResources.getDrawable(this, R.drawable.ic_guitar)}
                else -> {throw Resources.NotFoundException("Tab not found")}
            }
        }.attach()

    }
}