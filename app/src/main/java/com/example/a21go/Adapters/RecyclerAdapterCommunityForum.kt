package com.example.a21go.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.a21go.R
import com.example.a21go.databinding.CardViewCommunityForumBinding
import com.example.a21go.databinding.CardViewHabitsBinding
import com.example.a21go.model.CommunityForumDataClassItem

class RecyclerAdapterCommunityForum(var communityForumDataClassItem: List<CommunityForumDataClassItem>): RecyclerView.Adapter<RecyclerAdapterCommunityForum.ViewHolder>() {
    inner class ViewHolder(val binding:CardViewCommunityForumBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        val cardViewLecturesBinding:CardViewCommunityForumBinding=
            DataBindingUtil.inflate(layoutInflater,
                R.layout.card_view_community_forum,parent,false)

        return ViewHolder(cardViewLecturesBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.ContentForum.text=communityForumDataClassItem[position].message
        holder.binding.TitleForum.text=communityForumDataClassItem[position].title
    }

    override fun getItemCount(): Int {
        return communityForumDataClassItem.size
    }
}