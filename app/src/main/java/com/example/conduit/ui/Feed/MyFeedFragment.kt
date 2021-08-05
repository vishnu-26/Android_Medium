package com.example.conduit.ui.Feed

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.api.models.Entities.Article
import com.example.conduit.databinding.GlobalFeedFragmentBinding
import com.example.conduit.databinding.MyFeedFragmentBinding

class MyFeedFragment : Fragment(){

    private var binding: MyFeedFragmentBinding?=null
    private lateinit var viewModel:FeedViewModel
    private lateinit var feedAdapter: FeedAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(FeedViewModel::class.java)
        feedAdapter= FeedAdapter()

        binding = MyFeedFragmentBinding.inflate(inflater,container,false)
        binding?.myFeedRecyclerView?.layoutManager = LinearLayoutManager(context)
        binding?.myFeedRecyclerView?.adapter = feedAdapter

        viewModel.fetchMyFeed()
        viewModel.myFeed.observe({lifecycle}){
            feedAdapter.submitList(it)
            Log.d("Success","${it}")
        }

        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}