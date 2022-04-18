package mitiempo.android.curso.linkedin.moviesapp.domain.model

import mitiempo.android.curso.linkedin.moviesapp.data.database.entities.MoviesEntity
import mitiempo.android.curso.linkedin.moviesapp.data.database.entities.MoviesEntityPopular
import mitiempo.android.curso.linkedin.moviesapp.data.database.entities.MoviesEntityTop

import mitiempo.android.curso.linkedin.moviesapp.data.model.dataJson.Item
import mitiempo.android.curso.linkedin.moviesapp.data.model.dataJson.MoviesJson
import mitiempo.android.curso.linkedin.moviesapp.data.model.dataJson.Result

data class Movies (val name: String?, val image : String?, val description: String?)

fun Item.toDomain() = Movies(title,poster_path,overview)
fun MoviesEntity.toDomain() = Movies(name,image,description)
fun MoviesEntityTop.toDomainTop() = Movies(name,image,description)
fun MoviesEntityPopular.toDomainPoupular() = Movies(name,image,description)