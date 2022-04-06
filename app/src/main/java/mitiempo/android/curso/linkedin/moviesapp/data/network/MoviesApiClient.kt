package mitiempo.android.curso.linkedin.moviesapp.data.network

import mitiempo.android.curso.linkedin.moviesapp.data.model.dataJson.Filter
import mitiempo.android.curso.linkedin.moviesapp.data.model.dataJson.MoviesJson
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApiClient {

    @GET("/3/list/1")
    suspend fun getAllMovies(@Query("api_key") api_key: String = "9b8b57f966770862708e6f03dfd8f71e"
    ): Response<MoviesJson>
    //https://api.themoviedb.org/3/search/movie?api_key={api_key}&query=Jack+Reacher

    //https://api.themoviedb.org/3/movie/popular?api_key=9b8b57f966770862708e6f03dfd8f71e&language=en-US&page=1

    @GET("/3/movie/popular")
    suspend fun getPopularMovies(@Query("api_key") api_key: String = "9b8b57f966770862708e6f03dfd8f71e",
                                 @Query("language") language: String = "en-US",
                                 @Query("page") page: String = "en-1"
    ): Response<MoviesJson>

    //https://api.themoviedb.org/3/movie/top_rated?api_key=9b8b57f966770862708e6f03dfd8f71e&language=en-US&page=1

    @GET("/3/movie/top_rated")
    suspend fun getTopRatesMovies(@Query("api_key") api_key: String = "9b8b57f966770862708e6f03dfd8f71e",
                                  @Query("language") language: String = "en-US",
                                  @Query("page") page: String = "en-1"
    ): Response<MoviesJson>

    //https://api.themoviedb.org/3/search/movie?api_key=9b8b57f966770862708e6f03dfd8f71e&query=Spider
    @GET("/3/search/movie")
    suspend fun getMoviesFilter(@Query("api_key") api_key: String = "9b8b57f966770862708e6f03dfd8f71e"
                                ,@Query("query") query: String?): Response<Filter>
    /*@GET("/3/search/movie?api_key=9b8b57f966770862708e6f03dfd8f71e&query=spider")
    suspend fun getMoviesFilter(): Response<Filter>*/


    //getRetrofitImage
    /*@GET
    suspend fun getImageMovie(@Url url: String): String
*/
}