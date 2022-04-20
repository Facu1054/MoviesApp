package mitiempo.android.curso.linkedin.moviesapp.data.network

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mitiempo.android.curso.linkedin.moviesapp.data.model.dataJson.*
import javax.inject.Inject

class TvService @Inject constructor(private val api: MoviesApiClient) {



    suspend fun getTvFilter(filter: String): TvFilter {
        return withContext(Dispatchers.IO) {
            val response = filter?.let {
                Log.i("Service", filter)
                api.getTvFilter(
                    query = filter
                )
            }
            response!!.body() ?: TvFilter()
        }
    }
    suspend fun getTvTopRate(): TvTopRates {
        return withContext(Dispatchers.IO) {
            val response = api.getTVTopRates()
            Log.i("Rwe", response.body().toString())
            response.body() ?: TvTopRates()
        }
    }

    suspend fun getTvPopular(): TvPoupular {
        return withContext(Dispatchers.IO) {
            val response = api.getTVPopular()
            Log.i("Rwe", response.body().toString())
            response.body() ?: TvPoupular()
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