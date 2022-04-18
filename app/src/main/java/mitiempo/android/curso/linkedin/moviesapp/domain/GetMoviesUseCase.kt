package mitiempo.android.curso.linkedin.moviesapp.domain

import android.util.Log
import mitiempo.android.curso.linkedin.moviesapp.data.database.entities.toDatabase
import mitiempo.android.curso.linkedin.moviesapp.data.database.entities.toDatabasePopular
import mitiempo.android.curso.linkedin.moviesapp.data.database.entities.toDatabaseTop

import mitiempo.android.curso.linkedin.moviesapp.data.model.MoviesProvider
import mitiempo.android.curso.linkedin.moviesapp.data.model.MoviesRepository
import mitiempo.android.curso.linkedin.moviesapp.data.model.dataJson.Filter

import mitiempo.android.curso.linkedin.moviesapp.domain.model.Movies
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val repository : MoviesRepository,private val moviesProvider: MoviesProvider){



    //suspend operator fun invoke():MoviesJson? = repository.getMovies()
    suspend operator fun invoke():List<Movies> {
        var movies: List<Movies>

        return try {
            movies = repository.getMoviesFromApi()
            repository.clearMovies()
            repository.insertMovies(movies = movies.map { it.toDatabase() })
            movies

        }catch (e: Exception){
            Log.i("errr",e.toString())
            movies = repository.getMoviesFromDatabase()
            movies
        }
        /*return if(movies.isNotEmpty()){

        }else{

        }*/

    }


}
class GetMoviesFilterUseCases @Inject constructor(private val repository : MoviesRepository){

    suspend operator fun invoke(filter: String):Filter? = repository.getMoviesFilter(filter)




}
class GetTopRatesMoviesUseCase @Inject constructor(private val repository : MoviesRepository){


    //suspend operator fun invoke(): MoviesTopRate? = repository.getMoviesTop()
    //suspend operator fun invoke():MoviesJson? = repository.getMovies()
    suspend operator fun invoke():List<Movies> {
        var movies: List<Movies>

        return try {
            movies = repository.getMoviesTopFromApi()
            repository.clearTopMovies()
            repository.insertTopMovies(movies = movies.map { it.toDatabaseTop() })
            movies

        }catch (e: Exception){
            movies = repository.getMoviesTopFromDatabase()
            movies
        }

    }


}




class GetPopularMoviesUseCase @Inject constructor(private val repository : MoviesRepository){
    //suspend operator fun invoke(): MoviesPopular? = repository.getPopularMovies()

    suspend operator fun invoke():List<Movies> {
        var movies: List<Movies>

        return try {
            movies = repository.getMoviesPopularFromApi()

            repository.clearPopularMovies()
            repository.insertPopularMovies(movies = movies.map { it.toDatabasePopular() })
            movies

        }catch (e: Exception){
            movies = repository.getMoviesPopularFromDatabase()
            movies
        }

    }
}
