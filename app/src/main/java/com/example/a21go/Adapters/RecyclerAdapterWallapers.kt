package com.example.a21go.Adapters

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Environment
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.a21go.Activity.HomePageActivity
import com.example.a21go.Activity.MainActivity
import com.example.a21go.R
import com.example.a21go.databinding.CardViewHabitsBinding
import com.example.a21go.databinding.CardViewWallpapersBinding
import com.example.a21go.model.WallpapersModelItem

class RecyclerAdapterWallapers(var wallpapersModelItem: List<WallpapersModelItem>?): RecyclerView.Adapter<RecyclerAdapterWallapers.ViewHolder>() {
    var downloadId:Long=0
    var clickListener: RecyclerAdapterWallapers.ClickListener?=null

    fun onClickListeer( clickListener: RecyclerAdapterWallapers.ClickListener)
    {
        this.clickListener=clickListener
    }
    inner class ViewHolder(val binding: CardViewWallpapersBinding):RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener{
                clickListener?.OnClick(adapterPosition)
            }
        }
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
    }


    override fun getItemCount(): Int {
        return wallpapersModelItem!!.size
    }
    interface ClickListener{
        fun OnClick(position:Int)
    }
}