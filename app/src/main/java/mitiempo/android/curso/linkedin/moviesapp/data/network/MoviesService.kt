package mitiempo.android.curso.linkedin.moviesapp.data.network

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mitiempo.android.curso.linkedin.moviesapp.core.RetrofitHelper
import mitiempo.android.curso.linkedin.moviesapp.data.model.dataJson.Filter
import mitiempo.android.curso.linkedin.moviesapp.data.model.dataJson.MoviesJson
import mitiempo.android.curso.linkedin.moviesapp.data.model.dataJson.MoviesPopular
import mitiempo.android.curso.linkedin.moviesapp.data.model.dataJson.MoviesTopRate

class MoviesService {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getMovies(): MoviesJson {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(MoviesApiClient::class.java).getAllMovies()
            response.body() ?: MoviesJson()
        }
    }
    suspend fun getMoviesFilter(filter: String): Filter {
        return withContext(Dispatchers.IO) {
            val response = filter?.let {
                Log.i("Service", filter)
                retrofit.create(MoviesApiClient::class.java).getMoviesFilter(
                    query =filter
                )
            }
            response!!.body() ?: Filter()
        }
    }
    suspend fun getMoviesTopRate(): MoviesTopRate {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(MoviesApiClient::class.java).getTopRatesMovies()
            Log.i("Rwe", response.body().toString())
            response.body() ?: MoviesTopRate()
        }
    }

    suspend fun getMoviesPopular(): MoviesPopular {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(MoviesApiClient::class.java).getPopularMovies()
            Log.i("Rwe", response.body().toString())
            response.body() ?: MoviesPopular()
        }
    }



}