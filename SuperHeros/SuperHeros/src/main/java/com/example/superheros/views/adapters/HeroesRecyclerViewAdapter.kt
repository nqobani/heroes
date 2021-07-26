package com.example.superheros.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.networklayer.localDb.HeroEntity
import com.example.superheros.R

class HeroesRecyclerViewAdapter(var context: Context, var heroes: List<HeroEntity>): RecyclerView.Adapter<HeroesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroesViewHolder {
        return HeroesViewHolder(LayoutInflater.from(context).inflate(R.layout.item_hero, parent, false), context)
    }

    override fun onBindViewHolder(holder: HeroesViewHolder, position: Int) {
        holder.display(heroes[position])
    }

    override fun getItemCount(): Int {
        return heroes.size
    }

    fun setData(heroes: List<HeroEntity>) {
        this.heroes = heroes
        notifyDataSetChanged()
    }

}