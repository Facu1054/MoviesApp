package com.superheroapp.ui.view.adapter

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import mitiempo.android.curso.linkedin.moviesapp.data.model.dataJson.*
import mitiempo.android.curso.linkedin.moviesapp.databinding.ItemSuperheroBinding

//Conecta la vista con los datos
class MoviesViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val binding = ItemSuperheroBinding.bind(view)

    //, onclickListener:(Result) -> Unit
    fun render(movieModel: Item){
        with(binding) {

            tvNameMovie.text = movieModel.original_title
            tvDescription.text = movieModel.overview
            Picasso.get()
                .load("https://image.tmdb.org/t/p/w500/"+ movieModel.poster_path)
                .into(imgMovie)
            Log.i("test",movieModel.poster_path)
            itemView.setOnClickListener {
                //onclickListener(superHeroModel)

            }
        }

    }

    fun render2(movieModel: Result){
        with(binding) {

            tvNameMovie.text = movieModel.original_title
            tvDescription.text = movieModel.overview
            if(movieModel.poster_path != null) {
                Picasso.get()
                    .load("https://image.tmdb.org/t/p/w500/" + movieModel.poster_path)
                    .into(imgMovie)
                Log.i("test",movieModel.poster_path)
            }
            itemView.setOnClickListener {
                //onclickListener(superHeroModel)

            }
        }

    }
    fun renderTvPopular(tvModel: ResultTvPopular){
        with(binding) {

            tvNameMovie.text = tvModel.name
            tvDescription.text = tvModel.overview
            if(tvModel.poster_path != null) {
                Picasso.get()
                    .load("https://image.tmdb.org/t/p/w500/" + tvModel.poster_path)
                    .into(imgMovie)
                Log.i("test",tvModel.poster_path)
            }
            itemView.setOnClickListener {
                //onclickListener(superHeroModel)

            }
        }

    }
    fun renderTvTopRates(tvModel: ResultTvTopRates){
        with(binding) {

            tvNameMovie.text = tvModel.name
            tvDescription.text = tvModel.overview
            if(tvModel.poster_path != null) {
                Picasso.get()
                    .load("https://image.tmdb.org/t/p/w500/" + tvModel.poster_path)
                    .into(imgMovie)
                Log.i("test",tvModel.poster_path)
            }
            itemView.setOnClickListener {
                //onclickListener(superHeroModel)

            }
        }

    }
    fun renderFilterTv(tvModel: ResultTv){
        with(binding) {

            tvNameMovie.text = tvModel.name
            tvDescription.text = tvModel.overview
            if(tvModel.poster_path != null) {
                Picasso.get()
                    .load("https://image.tmdb.org/t/p/w500/" + tvModel.poster_path)
                    .into(imgMovie)
                Log.i("test",tvModel.poster_path)
            }
            itemView.setOnClickListener {
                //onclickListener(superHeroModel)

            }
        }

    }


}