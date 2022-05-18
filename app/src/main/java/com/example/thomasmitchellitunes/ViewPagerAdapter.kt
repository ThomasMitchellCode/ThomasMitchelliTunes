package com.example.thomasmitchellitunes

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {

        return when(position){
            0 -> {SongListFragment.newInstance(SongListFragment.CLASSIC)}
            1 -> {SongListFragment.newInstance(SongListFragment.ROCK)}
            2 -> {SongListFragment.newInstance(SongListFragment.POP)}
            else -> {throw Resources.NotFoundException("Tab not found")}
        }
    }

    override fun getItemCount() = 3
}