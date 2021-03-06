package com.example.conduit.ui.Feed

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.api.models.Entities.Article
import com.example.conduit.R
import com.example.conduit.databinding.GlobalFeedFragmentBinding

class GlobalFeedFragment : Fragment(){

    private var binding: GlobalFeedFragmentBinding?=null
    private lateinit var viewModel:FeedViewModel
    private lateinit var feedAdapter: FeedAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this).get(FeedViewModel::class.java)
        feedAdapter= FeedAdapter{viewArticle(it)}

        binding = GlobalFeedFragmentBinding.inflate(inflater,container,false)
        binding?.globalFeedRecyclerView?.layoutManager = LinearLayoutManager(context)
        binding?.globalFeedRecyclerView?.adapter = feedAdapter

        viewModel.fetchGlobalFeed()
        viewModel.globalFeed.observe({lifecycle}){
           feedAdapter.submitList(it)
//           Log.d("Success","${it}")
        }

        return binding?.root
    }

    private fun viewArticle(articleId:String){
        findNavController().navigate(
            R.id.feedToArticleFragment,
            bundleOf("article_id" to articleId)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}