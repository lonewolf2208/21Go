package com.example.a21go.Ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.a21go.Network.Response
import com.example.a21go.R
import com.example.a21go.Repository.PostJournalsRepo
import com.example.a21go.databinding.FragmentJournalsPostBinding
import kotlinx.coroutines.launch


class JournalsPost : Fragment() {
   
    lateinit var binding:FragmentJournalsPostBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_journals_post, container, false)
        binding.PostJournalButton.setOnClickListener {
            lifecycleScope.launch {
                var result=PostJournalsRepo().postJournalsApi(Splash_Screen.id.toInt(),binding.ContentPostJournal.text.toString())
                result.observe(viewLifecycleOwner,
                    {
                        when(it)
                        {
                            is Response.Success->{findNavController().navigate(R.id.action_journalsPost_to_notesAndJournals)}
                        }
                    })
            }
        }
        return binding.root
    }


}