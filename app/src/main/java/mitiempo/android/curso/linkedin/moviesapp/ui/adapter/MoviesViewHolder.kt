package com.superheroapp.ui.view.adapter

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import mitiempo.android.curso.linkedin.moviesapp.data.model.dataJson.*
import mitiempo.android.curso.linkedin.moviesapp.databinding.ItemListBinding
import mitiempo.android.curso.linkedin.moviesapp.domain.model.Movies
import mitiempo.android.curso.linkedin.moviesapp.domain.model.Tv

//Conecta la vista con los datos
class MoviesViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val binding = ItemListBinding.bind(view)

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
    fun renderMovies(movieModel: Movies){
        with(binding) {

            tvNameMovie.text = movieModel.name
            tvDescription.text = movieModel.description
            if(movieModel.image != null) {
                Picasso.get()
                    .load("https://image.tmdb.org/t/p/w500/" + movieModel.image)
                    .into(imgMovie)
                Log.i("test",movieModel.image)
            }

        }

    }

    fun renderTv(tvModel: Tv){
        with(binding) {

            tvNameMovie.text = tvModel.name
            tvDescription.text = tvModel.description
            if(tvModel.image != null) {
                Picasso.get()
                    .load("https://image.tmdb.org/t/p/w500/" + tvModel.image)
                    .into(imgMovie)
                Log.i("test",tvModel.image)
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