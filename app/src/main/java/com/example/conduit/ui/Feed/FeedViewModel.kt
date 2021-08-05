package com.example.conduit.ui.Feed

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.models.Entities.Article
import com.example.conduit.data.ArticlesRepo
import kotlinx.coroutines.launch

class FeedViewModel: ViewModel() {

    private val _globalfeed = MutableLiveData<List<Article>>()

    var globalFeed:LiveData<List<Article>> = _globalfeed
    fun fetchGlobalFeed() = viewModelScope.launch{
        Log.d("Success:","Hello")
        ArticlesRepo.getGlobalFeed().body()?.let {
            _globalfeed.postValue(it.articles)
//            Log.d("Global FEED","${it.articlesCount} articles fetched")
//            Log.d("Global Articles","Articles : ${globalFeed.value}")
        }
    }

    private val _myfeed = MutableLiveData<List<Article>>()
    var myFeed:LiveData<List<Article>> = _myfeed
    fun fetchMyFeed() = viewModelScope.launch{
        ArticlesRepo.getMyFeed().body()?.let {
            _myfeed.postValue(it.articles)
            Log.d("My FEED","${it.articlesCount} articles fetched")
            Log.d("Global Articles","Articles : ${myFeed.value}")
        }
    }

}