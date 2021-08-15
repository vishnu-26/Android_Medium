package io.realworld.android.ui.article

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.ConduitClient
import com.example.api.models.Entities.Article
import kotlinx.coroutines.launch

class ArticleViewModel : ViewModel() {
    val api = ConduitClient.public_api

    private val _article = MutableLiveData<Article>()
    val article: LiveData<Article> = _article

    fun fetchArticle(slug: String) = viewModelScope.launch {
        val response = api.getArticleBySlug(slug).body()

        response.let{
            _article.postValue(it?.article)
        }

    }
}