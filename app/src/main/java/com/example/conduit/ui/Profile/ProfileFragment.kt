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


                authViewModel.updateProfile(
                    image=evProfilePictureUrl.text.toString(),
                    username = evProfileUsername.text.toString().takeIf { it.isNotBlank() },
                    email= evProfileEmail.text.toString().takeIf { it.isNotBlank() },
                    bio= evProfileBio.text.toString(),
                    password = evProfileNewPassword.text.toString().takeIf { it.isNotBlank() }
                )

            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        binding=null
    }


}


