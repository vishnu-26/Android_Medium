package com.example.conduit.ui.Feed

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class FeedSlideAdapter(private val fragment: Fragment): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        when(position){
            0->{
                return GlobalFeedFragment()
            }
            1->{
                return MyFeedFragment()
            }

        }

        return GlobalFeedFragment()
    }
}