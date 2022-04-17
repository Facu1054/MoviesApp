package mitiempo.android.curso.linkedin.moviesapp.data.network

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mitiempo.android.curso.linkedin.moviesapp.core.RetrofitHelper
import mitiempo.android.curso.linkedin.moviesapp.data.model.dataJson.Filter
import mitiempo.android.curso.linkedin.moviesapp.data.model.dataJson.MoviesJson
import mitiempo.android.curso.linkedin.moviesapp.data.model.dataJson.MoviesPopular
import mitiempo.android.curso.linkedin.moviesapp.data.model.dataJson.MoviesTopRate
import javax.inject.Inject

class MoviesService @Inject constructor(private val api: MoviesApiClient) {


    suspend fun getMovies(): MoviesJson {
        return withContext(Dispatchers.IO) {
            val response = api.getAllMovies()
            response.body() ?: MoviesJson()
        }
    }
    suspend fun getMoviesFilter(filter: String): Filter {
        return withContext(Dispatchers.IO) {
            val response = filter?.let {
                Log.i("Service", filter)
                api.getMoviesFilter(
                    query =filter
                )
            }
            response!!.body() ?: Filter()
        }
    }
    suspend fun getMoviesTopRate(): MoviesTopRate {
        return withContext(Dispatchers.IO) {
            val response = api.getTopRatesMovies()
            Log.i("Rwe", response.body().toString())
            response.body() ?: MoviesTopRate()
        }
    }

    suspend fun getMoviesPopular(): MoviesPopular {
        return withContext(Dispatchers.IO) {
            val response = api.getPopularMovies()
            Log.i("Rwe", response.body().toString())
            response.body() ?: MoviesPopular()
        }
    }



}