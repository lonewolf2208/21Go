package com.example.a21go.Ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a21go.Adapters.RecyclerAdapterHabits
import com.example.a21go.R
import com.example.a21go.Ui.Splash_Screen.Companion.readInfo
import com.example.a21go.databinding.FragmentHomePageBinding
import com.example.a21go.databinding.LoginFragmentBinding
import kotlinx.coroutines.launch


class HomePageFragment : Fragment() {
    lateinit var _binding:FragmentHomePageBinding
    private val binding get() = _binding!!
    private var layoutManager: RecyclerView.LayoutManager?=null
    lateinit var adapter: RecyclerAdapterHabits
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomePageBinding.inflate(inflater, container, false)
        val view =binding.root

        layoutManager = LinearLayoutManager(
            container?.context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        binding.RecyclerViewHabits.layoutManager = layoutManager
        adapter= RecyclerAdapterHabits()
        binding.RecyclerViewHabits.adapter = adapter

//        lifecycleScope.launch {
//            Toast.makeText(requireContext(), readInfo("USERID"), Toast.LENGTH_SHORT).show()
//        }
            return view
    }

}