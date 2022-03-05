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
import com.example.a21go.Repository.PostCommunityForumRepo
import com.example.a21go.databinding.FragmentCommunityForumPostBinding
import kotlinx.coroutines.launch


class CommunityForumPost : Fragment() ,View.OnClickListener{

    lateinit var binding:FragmentCommunityForumPostBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_community_forum_post, container, false)
        binding.PostButton.setOnClickListener(this)
        return binding.root
    }

    override fun onClick(v: View?) {
        when(v?.id)
        {
            R.id.PostButton->
            {
                lifecycleScope.launch {
                          var  result=PostCommunityForumRepo().postCommunitytForumApi(Splash_Screen.id.toInt(),binding.ContentPostCommunityForum.text.toString(),binding.TitlePostContentForum.text.toString())
                            result.observe(viewLifecycleOwner,
                                {
                                    when(it)
                                    {
                                        is Response.Success->{findNavController().navigate(R.id.communityForum)}
                                        is Response.Error->{Toast.makeText(requireContext(),it.errorMessage.toString(),Toast.LENGTH_LONG).show()}
                                    }
                                })
                }
            }
        }
    }


}