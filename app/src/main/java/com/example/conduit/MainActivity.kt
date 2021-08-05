package com.example.conduit

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.WindowManager
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.api.ConduitClient
import com.example.conduit.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        authViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

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
           it?.let{
               updateNavMenu()
               navController.navigateUp()

           }
        }

//        ConduitClient.authToken?.let{
//            Log.d("Token","${it}")
//            updateNavMenu()
//            navController.navigateUp()
//      }
    }

    private fun updateNavMenu() {
        binding.navView.menu.clear()
        binding.navView.inflateMenu(R.menu.nav_user_menu)
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