package mitiempo.android.curso.linkedin.moviesapp.data.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mitiempo.android.curso.linkedin.moviesapp.core.RetrofitHelper
import mitiempo.android.curso.linkedin.moviesapp.data.model.dataJson.MoviesJson

class MoviesService {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getSuperHeroes(): MoviesJson {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(MoviesApiClient::class.java).getAllMovies()
            response.body() ?: MoviesJson()
        }
    }


}