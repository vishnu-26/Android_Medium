package com.example.api.models.Requests


import com.example.api.models.Entities.SigninCredentials
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SigninRequest(
    @Json(name = "user")
    val user: SigninCredentials
)