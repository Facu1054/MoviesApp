package mitiempo.android.curso.linkedin.moviesapp.data.network

import mitiempo.android.curso.linkedin.moviesapp.data.model.dataJson.*
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
    ): Response<MoviesPopular>

    //https://api.themoviedb.org/3/movie/top_rated?api_key=9b8b57f966770862708e6f03dfd8f71e&language=en-US&page=1

    @GET("/3/movie/top_rated")
    suspend fun getTopRatesMovies(@Query("api_key") api_key: String = "9b8b57f966770862708e6f03dfd8f71e",
                                  @Query("language") language: String = "en-US",
                                  @Query("page") page: String = "1"
    ): Response<MoviesTopRate>

    //https://api.themoviedb.org/3/search/movie?api_key=9b8b57f966770862708e6f03dfd8f71e&query=Spider
    @GET("/3/search/movie")
    suspend fun getMoviesFilter(@Query("api_key") api_key: String = "9b8b57f966770862708e6f03dfd8f71e"
                                ,@Query("query") query: String?): Response<Filter>


    //https://api.themoviedb.org/3/tv/popular?api_key=9b8b57f966770862708e6f03dfd8f71e&language=en-US&page=1
    @GET("/3/tv/popular")
    suspend fun getTVPopular(@Query("api_key") api_key: String = "9b8b57f966770862708e6f03dfd8f71e"
                                ,@Query("language") language: String = "en-US",
                                @Query("page") page: String = "1"): Response<MoviesPopular>

    @GET("/3/tv/top_rated")
    suspend fun getTVTopRates(@Query("api_key") api_key: String = "9b8b57f966770862708e6f03dfd8f71e"
                                ,@Query("language") language: String = "en-US",
                                @Query("page") page: String = "1"): Response<MoviesTopRate>

    //https://api.themoviedb.org/3/search/tv?api_key=<<api_key>>&language=en-US&page=1&include_adult=false
    @GET("/3/search/tv")
    suspend fun getTvFilter(@Query("api_key") api_key: String = "9b8b57f966770862708e6f03dfd8f71e"
                                ,@Query("language") language: String = "en-US",
                                @Query("page") page: String = "1",
                                @Query("include_adult") include_adult: String = "false"
                                ,@Query("query") query: String?): Response<TvFilter>


    /*@GET("/3/search/movie?api_key=9b8b57f966770862708e6f03dfd8f71e&query=spider")
    suspend fun getMoviesFilter(): Response<Filter>*/


    //getRetrofitImage
    /*@GET
    suspend fun getImageMovie(@Url url: String): String
*/
}