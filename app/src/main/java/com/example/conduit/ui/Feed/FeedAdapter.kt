package com.example.conduit.ui.Feed

import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.api.models.Entities.Article
import com.example.conduit.R
import com.example.conduit.databinding.FeedItemBinding
import kotlinx.android.synthetic.main.feed_item.view.*

class FeedAdapter():ListAdapter<Article,FeedAdapter.FeedViewHolder>(
    object : DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.toString() == newItem.toString()
        }

    }
) {

    private val articles = ArrayList<Article>()

    inner class FeedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.feed_item,parent,false)
        val viewHolder = FeedViewHolder(view)
        return viewHolder
//        val view = parent.context.getSystemService(LayoutInflater::class.java).inflate(
//            R.layout.feed_item,
//            parent,
//            false
//        )
        return viewHolder
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
//        holder.authorName.text = articles[position].author.username
//        holder.publishDate.text = articles[position].createdAt
//        holder.articleName.text = articles[position].title
//        holder.description.text = articles[position].description
        holder.itemView.apply{
            val article = getItem(position)

            Glide.with(context).load(article.author.image).into(imageView)
            tvAuthor.text = article.author.username
            tvDate.text = article.createdAt
            tvArticleName.text = article.title
            tvDescription.text = article.description
        }

    }

//    override fun getItemCount(): Int {
//        return articles.size
//    }
//
//    fun updateFeed(articles: ArrayList<Article>){
//        Log.d("Error","hii")
//        articles.clear()
//        articles.addAll(articles)
//
//        notifyDataSetChanged()
//    }


}