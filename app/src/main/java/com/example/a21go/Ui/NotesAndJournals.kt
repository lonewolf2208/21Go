package com.example.a21go.Ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a21go.Adapters.RecyclerAdapterCommunityForum
import com.example.a21go.Adapters.RecyclerAdapterJournals
import com.example.a21go.Network.Response
import com.example.a21go.R
import com.example.a21go.Repository.CommunityForumRepo
import com.example.a21go.Repository.GetJournalsRepo
import com.example.a21go.databinding.FragmentNotesAndJournalsBinding
import kotlinx.coroutines.launch


class NotesAndJournals : Fragment() {
    lateinit var binding:FragmentNotesAndJournalsBinding
    private var layoutManager: RecyclerView.LayoutManager?=null
    lateinit var adapter: RecyclerAdapterJournals
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_notes_and_journals, container, false)
        binding.AddNotes.setOnClickListener {
            findNavController().navigate(R.id.action_notesAndJournals_to_journalsPost)
        }

        lifecycleScope.launch {
            var result= GetJournalsRepo().getJournalsForumApi(Splash_Screen.id.toInt())
            result.observe(viewLifecycleOwner,
                {
                    when(it)
                    {
                        is Response.Success->{
                            if(it.data?.size==0)
                            {
                                binding.NoJournals.visibility=View.VISIBLE
                            }
                            layoutManager = LinearLayoutManager(
                                container?.context)
                            binding.RecyclerViewNotesAndJournals.layoutManager = layoutManager
                            adapter= RecyclerAdapterJournals(it.data!!)
                            binding.RecyclerViewNotesAndJournals.adapter = adapter

                        }
                        is Response.Error->{}
                    }
                })
        }
        return binding.root
    }


}