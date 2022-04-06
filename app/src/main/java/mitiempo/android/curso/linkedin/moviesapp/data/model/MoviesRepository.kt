package mitiempo.android.curso.linkedin.moviesapp.data.model

import android.util.Log
import mitiempo.android.curso.linkedin.moviesapp.data.model.dataJson.Filter
import mitiempo.android.curso.linkedin.moviesapp.data.model.dataJson.MoviesJson
import mitiempo.android.curso.linkedin.moviesapp.data.network.MoviesService

class MoviesRepository {

    private val api = MoviesService()


    suspend fun getMarvel():MoviesJson{
        val response = api.getMovies()

        MoviesProvider.results = response.items

        return response
    }
    suspend fun getMoviesFilter(filter: String): Filter {
        val response = api.getMoviesFilter(filter)
        Log.i("Repository",filter)

        MoviesProvider.resultsFilter = response.results

        return response
    }



}