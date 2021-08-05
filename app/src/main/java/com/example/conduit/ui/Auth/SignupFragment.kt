package com.example.conduit.ui.Auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
//import com.example.conduit.databinding.SigninFragmentBinding
import com.example.conduit.databinding.SigninSignupFragmentBinding
import com.example.conduit.AuthViewModel

class SignupFragment:Fragment() {

    private var binding: SigninSignupFragmentBinding? = null
    val authViewModel: AuthViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = SigninSignupFragmentBinding.inflate(layoutInflater, container, false)
//        authViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        binding?.authbtn?.text = "Submit"
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            authbtn.setOnClickListener {

                val username: String = evemail.text.toString()
                val password: String = evpassword.text.toString()

                authViewModel.signin(username, password)


                authViewModel.user.observe({ lifecycle }) {
                    it ?: let {
                        AlertDialog.Builder(requireActivity())
                            .setTitle("Invalid Operation")
                            .setMessage("Please Enter Valid Username and Password")
                            .setNegativeButton("Ok", { dialog, id -> dialog.cancel() })
                            .setCancelable(false)
                            .show()
                    }
                }

            }


        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}