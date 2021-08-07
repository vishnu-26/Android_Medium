package com.example.api.models.Entities


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SigninCredentials(
    @Json(name = "email")
    val email: String?,
    @Json(name = "password")
    val password: String?
)