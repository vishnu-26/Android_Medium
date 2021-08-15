package com.example.conduit.ui.Feed

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.api.ConduitClient
import com.example.api.models.Entities.Article
import com.example.conduit.R
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

        binding = MyFeedFragmentBinding.inflate(inflater,container,false)
        return binding?.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(FeedViewModel::class.java)
        feedAdapter= FeedAdapter{viewArticle()}

        binding?.myFeedRecyclerView?.layoutManager = LinearLayoutManager(context)
        binding?.myFeedRecyclerView?.adapter = feedAdapter

        viewModel.fetchMyFeed()
//        Log.d("Dummy","view")
        viewModel.myFeed.observe(viewLifecycleOwner){

            feedAdapter.submitList(it)

        }


    }

    fun viewArticle(){
        findNavController().navigate(R.id.feedToArticleFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}