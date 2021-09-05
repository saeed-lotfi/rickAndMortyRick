package com.saeedlotfi.rickandmortyrick.ui.deatil

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.saeedlotfi.rickandmortyrick.R
import javax.inject.Inject

class EpisodeAdapter @Inject constructor() :
    ListAdapter<String, EpisodeAdapter.EpisodeViewHolder>(diffUtil) {

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(
                oldItem: String,
                newItem: String
            ): Boolean =
                oldItem == newItem


            override fun areContentsTheSame(
                oldItem: String,
                newItem: String
            ): Boolean =
                oldItem == newItem

        }
    }


    class EpisodeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return EpisodeViewHolder(
            inflater.inflate(
                R.layout.row_episode,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        (holder.itemView.rootView as TextView).text = getItem(position)
    }


}