package com.example.conduit

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.ConduitClient
import com.example.api.models.Entities.User
import com.example.conduit.data.AuthRepo
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    private var _user = MutableLiveData<User?>()
    var user: LiveData<User?> = _user

    fun signin(email:String,password:String) = viewModelScope.launch {
        AuthRepo.signin(email, password).let{
            _user.postValue(it?.user)
            ConduitClient.authToken = it?.user?.token
            Log.d("Success","${ConduitClient.authToken }")
        }
    }

    fun signup(email:String,password:String,username:String) = viewModelScope.launch {
        AuthRepo.signup(email,password,username).let {
            _user.postValue(it?.user)
        }
    }

    fun updateProfile(
        image:String,
        username: String,
        email: String,
        bio:String,
        password: String) = viewModelScope.launch{

            AuthRepo.updateProfile(image,username,email,bio,password)?.let {
                Log.d("Edit","call to viewModel working")
                _user.postValue(it?.user)

            }
    }
}