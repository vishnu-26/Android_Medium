package com.example.conduit.ui.Auth

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.example.conduit.R
import com.example.conduit.databinding.AuthFragmentBinding
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.auth_fragment.*



class AuthFragment: Fragment() {

    private var binding : AuthFragmentBinding?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = AuthFragmentBinding.inflate(layoutInflater,container,false)
        return binding?.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController  = binding?.let {
            Navigation.findNavController(it.root?.findViewById(R.id.authFragmentNavHost))
        }

        binding?.authTabLayout?.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    0 ->{
                        navController?.navigate(R.id.goToSigninFragment)
                    }
                    1 ->{
                        navController?.navigate(R.id.goToSignupFragment)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {


            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                val pass:Unit
            }


        })


    }

    override fun onDestroy() {
        super.onDestroy()
        binding=null
    }


}


