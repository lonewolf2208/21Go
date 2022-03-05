package com.example.a21go.Ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.a21go.Activity.HomePageActivity
import com.example.a21go.R
import com.example.a21go.databinding.FragmentCategoryChooseBinding
import kotlinx.coroutines.launch


class CategoryChoose : Fragment() {
    lateinit var bindin:FragmentCategoryChooseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bindin=DataBindingUtil.inflate(inflater,R.layout.fragment_category_choose, container, false)

        bindin.AlcoholCategory.setOnClickListener {
            var intent= Intent(activity, HomePageActivity::class.java)
            lifecycleScope.launch {
                Splash_Screen.saveInfo("category", "Alcohol")
            }
            startActivity(intent)
        }
        bindin.SmokingCategory.setOnClickListener {
            var intent=Intent(activity,HomePageActivity::class.java)
            lifecycleScope.launch {
                Splash_Screen.saveInfo("category", "Smoking")
            }
            startActivity(intent)
        }
        bindin.OthersCategory.setOnClickListener {
            var intent=Intent(activity,HomePageActivity::class.java)
            lifecycleScope.launch {
                Splash_Screen.saveInfo("category", "Others")
            }
            startActivity(intent)
        }
        bindin.SocialMEdiaCategory.setOnClickListener {
            var intent=Intent(activity,HomePageActivity::class.java)
            lifecycleScope.launch {
                Splash_Screen.saveInfo("category", " Social Media")
            }
            startActivity(intent)
        }
        bindin.PornCategory.setOnClickListener {
            var intent=Intent(activity,HomePageActivity::class.java)
            lifecycleScope.launch {
                Splash_Screen.saveInfo("category", "Porn")
            }
            startActivity(intent)
        }
        return bindin.root
    }


}