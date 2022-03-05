package com.example.a21go.Ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.a21go.Activity.HomePageActivity
import com.example.a21go.R
import com.example.a21go.databinding.About21goBinding
import com.example.a21go.databinding.FragmentCategoryChooseBinding
import com.example.a21go.databinding.PrivacyPolicyBinding
import com.example.a21go.databinding.StatsFragmentBinding


class About21Go : Fragment() {
    private var _binding: About21goBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = About21goBinding.inflate(inflater, container, false)
        val view = binding.root


        return view
    }


}