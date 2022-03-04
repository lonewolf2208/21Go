package com.example.a21go.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a21go.R
import com.example.a21go.databinding.ActivityHomePageBinding

class HomePageActivity : AppCompatActivity() {
    private lateinit var binding:ActivityHomePageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityHomePageBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }
}