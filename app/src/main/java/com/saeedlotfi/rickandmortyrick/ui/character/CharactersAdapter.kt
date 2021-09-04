package com.saeedlotfi.rickandmortyrick.ui.character

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.saeedlotfi.rickandmortyrick.R
import com.saeedlotfi.rickandmortyrick.data.remote.model.CharacterResponseModel
import javax.inject.Inject


class CharactersAdapter @Inject constructor() :
    ListAdapter<CharacterResponseModel, CharactersAdapter.CharacterViewHolder>(diffUtil) {


    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<CharacterResponseModel>() {
            override fun areItemsTheSame(
                oldItem: CharacterResponseModel,
                newItem: CharacterResponseModel
            ): Boolean =
                oldItem.id == newItem.id


            override fun areContentsTheSame(
                oldItem: CharacterResponseModel,
                newItem: CharacterResponseModel
            ): Boolean =
                oldItem == newItem

        }
    }

    class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CharacterViewHolder(inflater.inflate(R.layout.row_character, parent, false))
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val name = holder.itemView.findViewById<TextView>(R.id.tvName)
        val imageView = holder.itemView.findViewById<ImageView>(R.id.profileImg)

        val item =  getItem(position)

        name.text = item.name

        Glide.with(imageView).load(item.image).into(imageView)

    }


}