package mitiempo.android.curso.linkedin.moviesapp.data.model

import android.util.Log
import mitiempo.android.curso.linkedin.moviesapp.data.model.dataJson.*
import mitiempo.android.curso.linkedin.moviesapp.data.network.MoviesService
import mitiempo.android.curso.linkedin.moviesapp.data.network.TvService

class MoviesRepository {

    private val api = MoviesService()
    private val apiTv = TvService()



    suspend fun getMovies():MoviesJson{
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


    suspend fun getTopRatesMovies():MoviesTopRate{
        val response = api.getMoviesTopRate()

        MoviesProvider.results = response.results


        return response
    }


    suspend fun getPopularMovies():MoviesPopular{
        val response = api.getMoviesPopular()

        MoviesProvider.results = response.results


        return response
    }

    suspend fun geTvFilter(filter: String): TvFilter {
        val response = apiTv.getTvFilter(filter)
        Log.i("Repository",filter)

        MoviesProvider.resultsFilterTv = response.results

        return response
    }


    suspend fun getTopRatesTv():MoviesTopRate{
        val response = apiTv.getTvTopRate()

        MoviesProvider.results = response.results


        return response
    }
    suspend fun getPopularTv():MoviesPopular{
        val response = apiTv.getTvPopular()

        MoviesProvider.results = response.results


        return response
    }


}