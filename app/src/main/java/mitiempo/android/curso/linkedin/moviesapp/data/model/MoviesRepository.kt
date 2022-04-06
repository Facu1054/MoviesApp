package mitiempo.android.curso.linkedin.moviesapp.data.model

import mitiempo.android.curso.linkedin.moviesapp.data.model.dataJson.MoviesJson
import mitiempo.android.curso.linkedin.moviesapp.data.network.MoviesService

class MoviesRepository {

    private val api = MoviesService()


    suspend fun getMarvel():MoviesJson{
        val response = api.getMovies()

        MoviesProvider.results = response.items

        return response
    }
    suspend fun getMoviesFilter(filter: String):MoviesJson{
        val response = api.getMoviesFilter(filter)

        MoviesProvider.results = response.items

        return response
    }

    /*suspend fun getImage(url: String):String{
        val response = api.getMovieImage(url)

        MoviesProvider.resultsImageMovies = response

        return response
    }*/



}