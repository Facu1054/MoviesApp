package mitiempo.android.curso.linkedin.moviesapp.data.network

import mitiempo.android.curso.linkedin.moviesapp.data.model.dataJson.MoviesJson
import retrofit2.Response
import retrofit2.http.GET

interface MoviesApiClient {

    @GET("/3/list/1?api_key=9b8b57f966770862708e6f03dfd8f71e")
    suspend fun getAllMovies(): Response<MoviesJson>


}