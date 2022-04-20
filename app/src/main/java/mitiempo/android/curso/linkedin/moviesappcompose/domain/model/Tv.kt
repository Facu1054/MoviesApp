package mitiempo.android.curso.linkedin.moviesapp.domain.model

import mitiempo.android.curso.linkedin.moviesapp.data.database.entities.TvEntityPopular
import mitiempo.android.curso.linkedin.moviesapp.data.database.entities.TvEntityTop
import mitiempo.android.curso.linkedin.moviesapp.data.model.dataJson.Item
import mitiempo.android.curso.linkedin.moviesapp.data.model.dataJson.Result
import mitiempo.android.curso.linkedin.moviesapp.data.model.dataJson.ResultTvPopular
import mitiempo.android.curso.linkedin.moviesapp.data.model.dataJson.ResultTvTopRates

data class Tv (val name: String?, val image : String?, val description: String?)

fun ResultTvTopRates.toDomain() = Tv(name,poster_path,overview)
fun ResultTvPopular.toDomain() = Tv(name,poster_path,overview)

//fun TvEntity.toDomain() = Tv(name,image,description)
fun TvEntityTop.toDomainTop() = Tv(name,image,description)
fun TvEntityPopular.toDomainPopular() = Tv(name,image,description)