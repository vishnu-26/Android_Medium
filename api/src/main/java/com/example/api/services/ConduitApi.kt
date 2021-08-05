package com.example.api.services

import com.example.api.models.Requests.SignUpRequest
import com.example.api.models.Requests.SigninRequest
import com.example.api.models.Responses.ArticlesResponse
import com.example.api.models.Responses.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface ConduitApi {

    @GET("articles")
    suspend fun getArticles(
        @Query("author")author:String?=null,
        @Query("favourited")favourited:String?=null,
        @Query("tag")tag:String?=null
    ): Response<ArticlesResponse>


    @POST("users")
    suspend fun signUpUser(
        @Body signUpRequest: SignUpRequest
    ):Response<UserResponse>

    @POST("users/login")
    suspend fun signInUser(
        @Body signInRequest: SigninRequest
    ):Response<UserResponse>



}