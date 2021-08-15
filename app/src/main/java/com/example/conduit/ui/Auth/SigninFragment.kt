package com.example.conduit.ui.Auth

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
//import com.example.conduit.databinding.SigninFragmentBinding
import com.example.conduit.databinding.SigninSignupFragmentBinding
import com.example.conduit.AuthViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class SigninFragment:Fragment() {

    private var binding :SigninSignupFragmentBinding? = null
    val authViewModel: AuthViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = SigninSignupFragmentBinding.inflate(layoutInflater,container,false)
//        authViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        binding?.apply{
            evusername.isVisible = false
            authbtn.text = "Signin"
        }

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {

            authbtn.setOnClickListener(object: View.OnClickListener {

                override fun onClick(v: View?) {

                    val username:String= evemail.text.toString()
                    val password:String= evpassword.text.toString()

                    if(username.isBlank()){
//                        Log.d("Error","Username Blank ${username}")
                        evemail.setError("Username Required")


                    }

                    if(password.isBlank()){
//                        Log.d("Error","Password Blank ${password}")
                        evpassword.setError("Password Required")
                    }
                    else{
                        authViewModel.signin(username,password)
//                        Log.d("signinError","${authViewModel.signinError}")
                    }

                }

            })

            authViewModel.signinError?.observe(viewLifecycleOwner){
                it?.let{
                    AlertDialog.Builder(requireActivity())
                        .setTitle("Invalid Operation")
                        .setMessage("${it}")
                        .setPositiveButton("OK",DialogInterface.OnClickListener { dialog, which ->})
                        .show()
                }
            }

        }



    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}