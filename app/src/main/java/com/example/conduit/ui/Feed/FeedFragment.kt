package com.example.conduit.ui.Feed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.api.ConduitClient
import com.example.conduit.R
import com.example.conduit.databinding.FeedFragmentBinding
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.feed_fragment.*
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.google.android.material.tabs.TabLayoutMediator


class FeedFragment : Fragment() {

    private var binding:FeedFragmentBinding?=null
//    private lateinit var viewpager: ViewPager
//    var pagerAdapter: FeedViewPagerAdapter?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FeedFragmentBinding.inflate(layoutInflater,container,false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.pager?.adapter = FeedSlideAdapter(this)

        TabLayoutMediator(feedTabLayout,pager,
            {tab, position ->
                when(position){
                    0->{
                        tab.text = "Global Feed"
                    }
                    1->{
                        tab.text = "My Feed"
                    }
                }
            }
        ).attach()

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object :OnBackPressedCallback(true){
                override fun handleOnBackPressed() {

                    // if you want onBackPressed() to be called as normal afterwards
                    if(pager.currentItem==0){
                        isEnabled=false
                        requireActivity().onBackPressed()
                    }
                    else{
                        pager.currentItem = pager.currentItem - 1
                    }
                }

            })




    }




    override fun onDestroy() {
        super.onDestroy()
        binding=null
    }



}


