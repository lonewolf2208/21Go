package com.example.a21go.Activity

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
import com.example.a21go.databinding.ActivityHomePageBinding
import kotlinx.coroutines.launch

class HomePageActivity : AppCompatActivity() {
    private lateinit var binding:ActivityHomePageBinding
    lateinit var toggleButton: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val drawerLayout=binding.drawerLayout
        val navView = binding.navView
        toggleButton = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        //add the toggle button to drawerLayout
        drawerLayout.addDrawerListener(toggleButton)
        //it's ready to used
        toggleButton.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        binding.icondrawer.setOnClickListener {
//            drawerLayout.openDrawer(GravityCompat.START)
//        }

        val intent = Intent(this, HomePageActivity::class.java)
        val builder = android.app.AlertDialog.Builder(this)
        builder.setTitle("Sign Out")
            .setMessage("Are you sure you want to Sign Out ?")
            .setPositiveButton("Sign out") { dialog, id ->
//                lifecycleScope.launch {
//                    datastore = Datastore(requireContext())
//                    datastore.changeLoginState(false)
//                    activity?.finish()
//                    startActivity(intent)
//                }
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
                R.id.Logout -> {
                    drawerLayout.closeDrawers()
                    val alertDialog: android.app.AlertDialog = builder.create()
                    alertDialog.show()
                }
            }
            true
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggleButton.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}