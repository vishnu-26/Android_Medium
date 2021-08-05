package com.example.api.models.Requests


import com.example.api.models.Entities.UserCredentials
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SignUpRequest(
    @Json(name = "user")
    val user: UserCredentials
)