package com.example.conduit.ui.Article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.conduit.databinding.ArticleFragmentBinding
import io.realworld.android.ui.article.ArticleViewModel

class ArticleFragment:Fragment() {

    private var binding:ArticleFragmentBinding?=null
    lateinit var articleViewModel: ArticleViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        articleViewModel = ViewModelProvider(this).get(ArticleViewModel::class.java)
        binding = ArticleFragmentBinding.inflate(layoutInflater,container,false)

        arguments?.getString("article_id")?.let {
//            Toast.makeText(requireContext(),"Article:${it}",Toast.LENGTH_LONG).show()
            articleViewModel.fetchArticle(it)
        }

        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        articleViewModel.article.observe(viewLifecycleOwner){
            binding?.apply {
                Glide.with(requireContext()).load(it.author.image).into(avatarImageView)
                titleTextView.text = it.title
                bodyTextView.text = it.body
                authorTextView.text = it.author.username
                dateTextView.text = it.createdAt

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding=null
    }
}