package com.example.a21go.Ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a21go.Adapters.RecyclerAdapterHabits
import com.example.a21go.R
import com.example.a21go.databinding.FragmentHomePageBinding


class HomePageFragment : Fragment() {
    lateinit var binding:FragmentHomePageBinding
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
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_home_page, container, false)
        layoutManager = LinearLayoutManager(
            container?.context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        binding.RecyclerViewHabits.layoutManager = layoutManager
        adapter= RecyclerAdapterHabits()
        binding.RecyclerViewHabits.adapter = adapter
        return binding.root
    }

}