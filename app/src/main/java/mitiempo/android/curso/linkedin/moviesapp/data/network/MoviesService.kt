package mitiempo.android.curso.linkedin.moviesapp.data.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mitiempo.android.curso.linkedin.moviesapp.core.RetrofitHelper
import mitiempo.android.curso.linkedin.moviesapp.data.model.dataJson.MoviesJson

class MoviesService {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getMovies(): MoviesJson {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(MoviesApiClient::class.java).getAllMovies()
            response.body() ?: MoviesJson()
        }
    }
    suspend fun getMoviesFilter(filter: String): MoviesJson {
        return withContext(Dispatchers.IO) {
            val response = filter?.let {
                retrofit.create(MoviesApiClient::class.java).getMoviesFilter(
                    it
                )
            }
            response!!.body() ?: MoviesJson()
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