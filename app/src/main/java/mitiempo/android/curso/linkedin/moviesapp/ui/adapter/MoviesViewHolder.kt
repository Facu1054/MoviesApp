package com.superheroapp.ui.view.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import mitiempo.android.curso.linkedin.moviesapp.data.model.dataJson.Item
import mitiempo.android.curso.linkedin.moviesapp.databinding.ItemSuperheroBinding

//Conecta la vista con los datos
class MoviesViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val binding = ItemSuperheroBinding.bind(view)

    //, onclickListener:(Result) -> Unit
    fun render(movieModel: Item){
        with(binding) {

            tvNameSuperHero.text = movieModel.original_title
            tvDescription.text = movieModel.overview
            Picasso.get()
                .load(movieModel.backdrop_path)
                .into(imgSuperHero)
            itemView.setOnClickListener {
                //onclickListener(superHeroModel)

            }
        }

    }


}