package mitiempo.android.curso.linkedin.moviesapp.domain

import mitiempo.android.curso.linkedin.moviesapp.data.model.MoviesRepository
import mitiempo.android.curso.linkedin.moviesapp.data.model.dataJson.Filter
import mitiempo.android.curso.linkedin.moviesapp.data.model.dataJson.MoviesJson
import mitiempo.android.curso.linkedin.moviesapp.data.model.dataJson.MoviesPopular
import mitiempo.android.curso.linkedin.moviesapp.data.model.dataJson.MoviesTopRate
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val repository : MoviesRepository){



    suspend operator fun invoke():MoviesJson? = repository.getMovies()


}
class GetMoviesFilterUseCases @Inject constructor(private val repository : MoviesRepository){

    suspend operator fun invoke(filter: String):Filter? = repository.getMoviesFilter(filter)
}
class GetTopRatesMoviesUseCase @Inject constructor(private val repository : MoviesRepository){


    suspend operator fun invoke():MoviesTopRate? = repository.getTopRatesMovies()


}

class GetPopularMoviesUseCase @Inject constructor(private val repository : MoviesRepository){
    suspend operator fun invoke():MoviesPopular? = repository.getPopularMovies()


}