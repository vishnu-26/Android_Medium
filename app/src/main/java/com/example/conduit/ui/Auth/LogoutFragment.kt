package com.example.conduit.ui.Auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.conduit.AuthViewModel
import com.example.conduit.databinding.LogoutFragmentBinding
import kotlinx.android.synthetic.main.logout_fragment.*

class LogoutFragment : Fragment() {

    private var binding:LogoutFragmentBinding?=null
    val authViewModel: AuthViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= LogoutFragmentBinding.inflate(layoutInflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button.setOnClickListener {
            authViewModel.logout()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}