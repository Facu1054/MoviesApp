package mitiempo.android.curso.linkedin.moviesapp.data.network

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mitiempo.android.curso.linkedin.moviesapp.core.RetrofitHelper
import mitiempo.android.curso.linkedin.moviesapp.data.model.dataJson.*

class TvService {
    private val retrofit = RetrofitHelper.getRetrofit()


    suspend fun getTvFilter(filter: String): TvFilter {
        return withContext(Dispatchers.IO) {
            val response = filter?.let {
                Log.i("Service", filter)
                retrofit.create(MoviesApiClient::class.java).getTvFilter(
                    query = filter
                )
            }
            response!!.body() ?: TvFilter()
        }
    }
    suspend fun getTvTopRate(): MoviesTopRate {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(MoviesApiClient::class.java).getTVTopRates()
            Log.i("Rwe", response.body().toString())
            response.body() ?: MoviesTopRate()
        }
    }

    suspend fun getTvPopular(): MoviesPopular {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(MoviesApiClient::class.java).getTVPopular()
            Log.i("Rwe", response.body().toString())
            response.body() ?: MoviesPopular()
        }
    }

    /*private val retrofitImage = RetrofitHelper.getRetrofitImage()
    suspend fun getMovieImage(url: String): String {
        return withContext(Dispatchers.IO) {
            val response = retrofitImage.create(MoviesApiClient::class.java).getImageMovie(url)
            response ?: ""
        }
    }*/

}