package mitiempo.android.curso.linkedin.moviesapp.domain

import mitiempo.android.curso.linkedin.moviesapp.data.database.entities.toDatabasePopular
import mitiempo.android.curso.linkedin.moviesapp.data.database.entities.toDatabaseTop
import mitiempo.android.curso.linkedin.moviesapp.data.database.entities.toDomainPopular
import mitiempo.android.curso.linkedin.moviesapp.data.model.MoviesRepository
import mitiempo.android.curso.linkedin.moviesapp.data.model.dataJson.*
import mitiempo.android.curso.linkedin.moviesapp.domain.model.Movies
import mitiempo.android.curso.linkedin.moviesapp.domain.model.Tv
import javax.inject.Inject

class GetTvFilterUseCases @Inject constructor(private val repository : MoviesRepository){


    suspend operator fun invoke(filter: String): TvFilter? = repository.geTvFilter(filter)
}

/*
class GetTopRatesTvUseCase @Inject constructor(private val repository : MoviesRepository){



    suspend operator fun invoke():TvTopRates? = repository.getTopRatesTv()


}

class GetPopularTvUseCase @Inject constructor(private val repository : MoviesRepository){



    suspend operator fun invoke():TvPoupular? = repository.getPopularTv()


}*/

class GetPopularTvUseCase @Inject constructor(private val repository : MoviesRepository){


    //suspend operator fun invoke(): MoviesTopRate? = repository.getMoviesTop()
    //suspend operator fun invoke():MoviesJson? = repository.getMovies()
    suspend operator fun invoke():List<Tv> {
        val movies = repository.getPopularTvFromApi()
        return if(movies.isNotEmpty()){
            repository.clearPopularTv()
            repository.insertPopularTv(tv = movies.map { it.toDomainPopular() })
            movies
        }else{
            repository.getPopularTvFromDatabase()
        }

    }


}




class GetTopRatesTvUseCase @Inject constructor(private val repository : MoviesRepository){
    //suspend operator fun invoke(): MoviesPopular? = repository.getPopularMovies()

    suspend operator fun invoke():List<Tv> {
        val movies = repository.getTopRatesTvFromApi()
        return if(movies.isNotEmpty()){
            repository.clearTopTv()
            repository.inserttopTv(tv = movies.map { it.toDatabaseTop() })
            movies
        }else{
            repository.getTopRatesTvFromDatabase()
        }

    }
}
