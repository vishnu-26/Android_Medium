package com.example.api.models.Requests


import com.example.api.models.Entities.UserUpdateData
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserUpdateRequest(
    @Json(name = "user")
    val user: UserUpdateData
)