    package com.example.a21go.Activity

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a21go.R


    class MainActivity : AppCompatActivity() {
        companion object
        {
            lateinit var prefs:SharedPreferences
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         prefs=getSharedPreferences("prefs", AppCompatActivity.MODE_PRIVATE);

    }
}