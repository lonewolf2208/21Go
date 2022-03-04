package com.example.a21go.Ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.a21go.databinding.LoginFragmentBinding
import com.example.a21go.databinding.StatsFragmentBinding

class StatsFragment :Fragment(){
    private var _binding: StatsFragmentBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = StatsFragmentBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }
}