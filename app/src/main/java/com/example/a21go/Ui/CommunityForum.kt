package com.example.a21go.Ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a21go.Adapters.RecyclerAdapterCommunityForum
import com.example.a21go.Adapters.RecyclerAdapterHabits
import com.example.a21go.Network.Response
import com.example.a21go.R
import com.example.a21go.Repository.CommunityForumRepo
import com.example.a21go.databinding.FragmentCommunityForumBinding
import kotlinx.coroutines.launch


class CommunityForum : Fragment() {

    lateinit var binding:FragmentCommunityForumBinding
    private var layoutManager: RecyclerView.LayoutManager?=null
    lateinit var adapter: RecyclerAdapterCommunityForum
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_community_forum, container, false)
        lifecycleScope.launch {
            var result=CommunityForumRepo().getCommunitytForumApi()
            result.observe(viewLifecycleOwner,
                {
                    when(it)
                    {
                        is Response.Success->{
                            layoutManager = LinearLayoutManager(
                                container?.context)
                            binding.RecyclerViewCommunityFOrum.layoutManager = layoutManager
                            adapter= RecyclerAdapterCommunityForum(it.data!!)
                            binding.RecyclerViewCommunityFOrum.adapter = adapter

                        }
                        is Response.Error->{}
                    }
                })
        }
        return binding.root
    }


}