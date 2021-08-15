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
import kotlin.properties.Delegates

class AuthViewModel : ViewModel() {

    private var _user = MutableLiveData<User?>()
    var user: LiveData<User?> = _user

    private var _signinError = MutableLiveData<String?>()
    var signinError :LiveData<String?> =_signinError

    fun signin(email:String?,password:String?)= viewModelScope.launch {
        AuthRepo.signin(email, password)?.let{
            _signinError.postValue(null)
            _user.postValue(it?.user)
//            return@launch it?.user
//            ConduitClient.authToken = it?.user?.token
            Log.d("Success","${ConduitClient.authToken }")

        }?:run{
            Log.d("signin"," Has been Called")
            _signinError.postValue("Inavlid Username and Password")
        }

    }

    fun logout()=viewModelScope.launch {
        Log.d("Success","Logout success")
        _user.postValue(null)

    }

    fun signup(email:String,password:String,username:String) = viewModelScope.launch {
        AuthRepo.signup(email,password,username).let {
            _user.postValue(it?.user)
        }
    }

    fun updateProfile(
        image:String,
        username: String?,
        email: String?,
        bio:String,
        password: String?) = viewModelScope.launch{

            AuthRepo.updateProfile(image,username,email,bio,password)?.let {
                Log.d("Edit","call to viewModel working")
                _user.postValue(it?.user)

            }
    }

    fun getCurrentUser(token:String) = viewModelScope.launch {
        AuthRepo.getCurrentUser(token).let{
            _user.postValue(it?.user)
        }
    }
}