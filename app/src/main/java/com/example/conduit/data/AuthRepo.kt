package com.example.conduit.data

import com.example.api.ConduitClient
import com.example.api.models.Entities.SigninCredentials
import com.example.api.models.Entities.UserCredentials
import com.example.api.models.Entities.UserUpdateData
import com.example.api.models.Requests.SignUpRequest
import com.example.api.models.Requests.SigninRequest
import com.example.api.models.Requests.UserUpdateRequest
import com.example.api.models.Responses.UserResponse

object AuthRepo {

    suspend fun signin(email:String,password:String): UserResponse? {
        val response = ConduitClient.public_api.signInUser(SigninRequest(SigninCredentials(email, password)))
        return response.body()
    }

    suspend fun signup(email:String,password:String,username:String): UserResponse? {
        val response = ConduitClient.public_api.signUpUser(SignUpRequest(UserCredentials(email,password,username)))
        return response.body()

    }

    suspend fun updateProfile(image:String,username: String,email: String,bio:String,password: String):UserResponse?{
        val response = ConduitClient.auth_api.updateCurrentUser(UserUpdateRequest(UserUpdateData(bio,email,image,password,username)))
        return response.body()
    }

}