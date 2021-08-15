package com.example.conduit

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.lifecycle.ViewModelProvider
import com.example.api.ConduitClient
import com.example.api.models.Entities.User
import com.example.conduit.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object{
        const val PREFS_FILE_AUTH = "prefs_auth"
        const val PREFS_KEY_TOKEN = "token"
    }

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var authViewModel: AuthViewModel
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        authViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        sharedPreferences=getSharedPreferences(PREFS_FILE_AUTH, MODE_PRIVATE)
        sharedPreferences.getString(PREFS_KEY_TOKEN,null)?.let {
            authViewModel.getCurrentUser(it)
        }

        setSupportActionBar(binding.appBarMain.toolbar)


        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_feed,
                R.id.nav_auth
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        authViewModel.user.observe({lifecycle}){
//            Toast.makeText(this,"${it?.username} is logged in", Toast.LENGTH_LONG).show()
            updateNavMenu(it)
            ConduitClient.authToken = it?.token
//            Log.d("Token","${ConduitClient.authToken}")
            it?.let{
               sharedPreferences.edit{
                   putString(PREFS_KEY_TOKEN,it.token)
               }

            }?: run {

               sharedPreferences.edit{
                   remove(PREFS_KEY_TOKEN)
               }
            }
            navController.navigateUp()
        }


    }



    private fun updateNavMenu(user:User?) {
        binding.navView.menu.clear()
        user?.let{

            binding.navView.inflateMenu(R.menu.nav_user_menu)
        }?:run{

            binding.navView.inflateMenu(R.menu.nav_guest_menu)
        }

    }





    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}