package com.example.conduit.data

import com.example.api.ConduitClient
import com.example.api.services.ConduitAuthAPI

object ArticlesRepo {

    suspend fun getGlobalFeed() = ConduitClient.public_api.getArticles()

    suspend fun getMyFeed() = ConduitClient.auth_api.getFeedArticles()
}