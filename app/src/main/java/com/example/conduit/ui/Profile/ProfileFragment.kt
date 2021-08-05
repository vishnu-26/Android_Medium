package com.example.conduit.ui.Profile

import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.conduit.AuthViewModel
import com.example.conduit.R
import com.example.conduit.databinding.ProfileFragmentBinding
import kotlinx.android.synthetic.main.profile_fragment.*
import kotlinx.android.synthetic.main.profile_fragment.view.*

class ProfileFragment : Fragment() {

    private var binding:ProfileFragmentBinding?=null
    val authViewModel: AuthViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= ProfileFragmentBinding.inflate(inflater, container, false)
        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        authViewModel.user.observe(viewLifecycleOwner){
            binding.apply {
                evProfilePictureUrl.setText(it?.image)
                evProfileUsername.setText(it?.username)
                evProfileBio.setText(it?.bio)
                evProfileEmail.setText(it?.email)
//                evProfileNewPassword.setText(it?.)
            }

        }

        binding.apply {
            editProfileBtn.setOnClickListener {
                Log.d("Edit","btn working")
                val password=evProfileNewPassword.text.toString()
                val username=evProfileUsername.text.toString().takeIf { it.isNotBlank() }
                val email  =evProfileEmail.text.toString().takeIf { it.isNotBlank() }
                val bio =  evProfileBio.text.toString()
                val imageurl = evProfilePictureUrl.text.toString()

                if (username != null) {
                    if (email != null) {
                        if (password != null) {
                            Log.d("checking editetexts","${imageurl}-${username}-${email}-${bio}-${imageurl}")
                            authViewModel.updateProfile(
                                image=imageurl,
                                username = username,
                                email= email,
                                bio=bio,
                                password = password
                            )
                        }
                    }
                }

            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        binding=null
    }


}


