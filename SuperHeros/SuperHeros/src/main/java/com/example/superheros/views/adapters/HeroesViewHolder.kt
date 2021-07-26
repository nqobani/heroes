package com.example.superheros.views.adapters

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.networklayer.localDb.HeroEntity
import com.example.superheros.R


class HeroesViewHolder(itemView: View, var context: Context) : RecyclerView.ViewHolder(itemView) {
    fun display(hero: HeroEntity){
        Glide.with(context).load(hero.image)
            .thumbnail(Glide.with(context).load(R.drawable.image_loading_placeholder))
            .into(itemView.findViewById<ImageView>(R.id.hero_image_view))
        itemView.findViewById<TextView>(R.id.name_text_view).text = hero.name
        itemView.findViewById<TextView>(R.id.full_name_text_view).text = hero.fullName
        itemView.findViewById<TextView>(R.id.occupation_text_view).text = hero.occupation
    }
}