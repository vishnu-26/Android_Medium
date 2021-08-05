package com.example.api.models.Entities


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Comment(
    @Json(name = "profile")
    val author: Profile,
    @Json(name = "body")
    val body: String,
    @Json(name = "createdAt")
    val createdAt: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "updatedAt")
    val updatedAt: String
)