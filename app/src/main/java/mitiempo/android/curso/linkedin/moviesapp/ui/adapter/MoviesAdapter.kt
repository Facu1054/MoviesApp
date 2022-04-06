package com.superheroapp.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mitiempo.android.curso.linkedin.moviesapp.R
import mitiempo.android.curso.linkedin.moviesapp.data.model.dataJson.Item

//Convierte un listado en un RecyclerView
//, private val onClickListener:(Result) -> Unit
class MoviesAdapter(private var moviesList:List<Item>,private var filter: String) : RecyclerView.Adapter<MoviesViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MoviesViewHolder(layoutInflater.inflate(R.layout.item_superhero, parent, false))
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val item = moviesList[position]
        holder.render(item)
        //onClickListener
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }
    fun setListHeroes(heroes: List<Item>) {
        this.moviesList = heroes as MutableList<Item>
        notifyDataSetChanged()
    }

}
/*

class SuperHeroAdapter(private var superHeroesList:List<SuperHero>, private val onClickListener:(SuperHero) -> Unit) : RecyclerView.Adapter<SuperHeroViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SuperHeroViewHolder(layoutInflater.inflate(R.layout.item_superhero, parent, false))
    }

    override fun onBindViewHolder(holder: SuperHeroViewHolder, position: Int) {
        val item = superHeroesList[position]
        holder.render(item, onClickListener)
    }

    override fun getItemCount(): Int {
        return superHeroesList.size
    }
    fun setListHeroes(heroes: List<SuperHero>) {
        this.superHeroesList = heroes as MutableList<SuperHero>
        notifyDataSetChanged()
    }

}
 */