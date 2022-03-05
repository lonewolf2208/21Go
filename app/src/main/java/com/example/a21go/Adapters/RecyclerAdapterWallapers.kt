package com.example.a21go.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.a21go.R
import com.example.a21go.databinding.CardViewHabitsBinding
import com.example.a21go.databinding.CardViewWallpapersBinding
import com.example.a21go.model.WallpapersModelItem

class RecyclerAdapterWallapers(var wallpapersModelItem: List<WallpapersModelItem>?): RecyclerView.Adapter<RecyclerAdapterWallapers.ViewHolder>() {
    inner class ViewHolder(val binding: CardViewWallpapersBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        val cardViewLecturesBinding:CardViewWallpapersBinding=
            DataBindingUtil.inflate(layoutInflater,
                R.layout.card_view_wallpapers,parent,false)

        return ViewHolder(cardViewLecturesBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.Wallpapers.load(wallpapersModelItem!![position].image)
        holder.binding.Wallpapers.setOnClickListener {

        }
    }

    override fun getItemCount(): Int {
        return wallpapersModelItem!!.size
    }
}