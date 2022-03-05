package com.example.a21go.Activity

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import com.example.a21go.R
import androidx.navigation.fragment.findNavController
import com.example.a21go.Ui.Splash_Screen
import com.example.a21go.Ui.Splash_Screen.Companion.loggedIn
import com.example.a21go.databinding.ActivityHomePageBinding
import kotlinx.coroutines.launch

class HomePageActivity : AppCompatActivity() {
    private lateinit var binding:ActivityHomePageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val drawerLayout=binding.drawerLayout
        val navView = binding.navView
        Splash_Screen.data.observe(this,
            {
                binding.NameHomePage.text="${it.data?.username.toString()}"
            })
        binding.icondrawer.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        val intent = Intent(this, MainActivity::class.java)
        val builder = android.app.AlertDialog.Builder(this)
        builder.setTitle("Sign Out")
            .setMessage("Are you sure you want to Sign Out ?")
            .setPositiveButton("Sign out") { dialog, id ->
                loggedIn=false
                startActivity(intent)

            }
            .setNeutralButton("Cancel") { dialog, id ->
            }
        navView.setNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.Stats -> {
                   findNavController(R.id.fragmentContainerViewHomePage).navigate(R.id.statsFragment)

                    drawerLayout.closeDrawers()
                }
                R.id.Category->
                {
                    findNavController(R.id.fragmentContainerViewHomePage).navigate(R.id.categoryChoose2)
                    drawerLayout.closeDrawers()
                }
                R.id.Menu->
                {
                    findNavController(R.id.fragmentContainerViewHomePage).navigate(R.id.homePageFragment)

                    drawerLayout.closeDrawers()
                }

                R.id.CommunityForum->
                {
                    findNavController(R.id.fragmentContainerViewHomePage).navigate(R.id.communityForum)
                    drawerLayout.closeDrawers()
                }
                R.id.Journals->
                {
                    findNavController(R.id.fragmentContainerViewHomePage).navigate(R.id.notesAndJournals)
                    drawerLayout.closeDrawers()
                }
                R.id.About21Go-> {
                    findNavController(R.id.fragmentContainerViewHomePage).navigate(R.id.about21Go)
                    drawerLayout.closeDrawers()
                }
                R.id.PrivacyPolicies-> {
                    findNavController(R.id.fragmentContainerViewHomePage).navigate(R.id.privacyPolicy)
                    drawerLayout.closeDrawers()
                }
            }
            true
        }
    }
    override fun onBackPressed() {
        when (findNavController(R.id.fragmentContainerViewHomePage).currentDestination?.id) {
            R.id.homePageFragment -> alertBox()
            else -> super.onBackPressed()
        }
    }
    private fun alertBox()
    {
        val builder= AlertDialog.Builder(this)
        builder.setTitle("Quit App")
            .setMessage("Are you sure you want to leave the App?")
            .setPositiveButton("No"){dialog,id->dialog.cancel()}
            .setNegativeButton("Yes"){dialog,id->finishAffinity()}
        builder.show()
    }

}