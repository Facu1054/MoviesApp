package mitiempo.android.curso.linkedin.moviesapp.data.model

import android.util.Log
import mitiempo.android.curso.linkedin.moviesapp.data.database.dao.MoviesDao
import mitiempo.android.curso.linkedin.moviesapp.data.database.entities.*
import mitiempo.android.curso.linkedin.moviesapp.data.model.dataJson.*
import mitiempo.android.curso.linkedin.moviesapp.data.network.MoviesService
import mitiempo.android.curso.linkedin.moviesapp.data.network.TvService
import mitiempo.android.curso.linkedin.moviesapp.domain.model.*

import javax.inject.Inject

class MoviesRepository @Inject constructor(
            private val api : MoviesService,
            private val apiTv : TvService,
            private val moviesProvider: MoviesProvider,
            private val moviesDao: MoviesDao
) {





    suspend fun getMoviesFromApi():List<Movies>{
        val response: MoviesJson = api.getMovies()


        //moviesProvider.results = response.items

        return response.items.map { it.toDomain() }
    }
    suspend fun getMoviesFromDatabase():List<Movies>{
        val response = moviesDao.getAllMovies()

        return response.map { it.toDomain() }
    }

    suspend fun insertMovies(movies:List<MoviesEntity>){
        moviesDao.insertAll(movies)
    }
    suspend fun clearMovies(){
        moviesDao.deleteAllMovies()
    }
///////////////////////////////////



    suspend fun getMoviesFilter(filter: String): Filter {
        val response = api.getMoviesFilter(filter)
        Log.i("Repository",filter)

        moviesProvider.resultsFilter = response.results

        return response
    }

    /*suspend fun getMoviesTop():MoviesTopRate{
        val response = api.getMoviesTopRate()

        moviesProvider.results = response.results


        return response
    }*/

/////////////////////////
suspend fun getMoviesTopFromApi():List<Movies>{
    val response: MoviesTopRate = api.getMoviesTopRate()


    //moviesProvider.results = response.items

    return response.results.map { it.toDomain() }
}
    suspend fun getMoviesTopFromDatabase(): List<Movies> {
        val response = moviesDao.getAllMoviesTop()

        return response.map { it.toDomainTop() }
    }

    suspend fun insertTopMovies(movies: List<MoviesEntityTop>){
        moviesDao.insertAllTopMovies(movies)
    }
    suspend fun clearTopMovies(){
        moviesDao.deleteAllTopMovies()
    }
    /*suspend fun getTopRatesMovies():MoviesTopRate{
        val response = api.getMoviesTopRate()

        moviesProvider.results = response.results


        return response
    }*/

///////////////////////////


suspend fun getMoviesPopularFromApi():List<Movies>{
    val response: MoviesPopular = api.getMoviesPopular()


    //moviesProvider.results = response.items

    return response.results.map { it.toDomain() }
}
    suspend fun getMoviesPopularFromDatabase():List<Movies>{
        val response = moviesDao.getAllMoviesPopular()

        return response.map { it.toDomainPoupular() }
    }

    suspend fun insertPopularMovies(movies: List<MoviesEntityPopular>){
        moviesDao.insertAllPopularMovies(movies)
    }
    suspend fun clearPopularMovies(){
        moviesDao.deleteAllPopularMovies()
    }

    /*suspend fun getPopularMovies():MoviesPopular{
        val response = api.getMoviesPopular()

        moviesProvider.results = response.results


        return response
    }*/

    suspend fun geTvFilter(filter: String): TvFilter {
        val response = apiTv.getTvFilter(filter)
        Log.i("Repository",filter)

        moviesProvider.resultsFilterTv = response.results

        return response
    }

/*
    suspend fun getTopRatesTv():TvTopRates{
        val response = apiTv.getTvTopRate()

        moviesProvider.resultsTopRates = response.results


        return response
    }*/



    suspend fun getTopRatesTvFromApi():List<Tv>{
        val response: TvTopRates = apiTv.getTvTopRate()


        //moviesProvider.results = response.items

        return response.results.map { it.toDomain() }
    }
    suspend fun getTopRatesTvFromDatabase():List<Tv>{
        val response = moviesDao.getAllTvTop()

        return response.map { it.toDomainPopular() }
    }

    suspend fun inserttopTv(tv: List<TvEntityTop>){
        moviesDao.insertAllTvTop(tv)
    }
    suspend fun clearTopTv(){
        moviesDao.deleteAllPopularMovies()
    }
    //////////
    /*suspend fun getPopularTv():TvPoupular{
        val response = apiTv.getTvPopular()

        moviesProvider.resultsPopular = response.results


        return response
    }*/

    suspend fun getPopularTvFromApi():List<Tv>{
        val response: TvPoupular = apiTv.getTvPopular()


        //moviesProvider.results = response.items

        return response.results.map { it.toDomain() }
    }
    suspend fun getPopularTvFromDatabase():List<Tv>{
        val response = moviesDao.getAllTvPopular()

        return response.map { it.toDomainPopular() }
    }

    suspend fun insertPopularTv(tv: List<TvEntityPopular>){
        moviesDao.insertAllTVPopular(tv)
    }
    suspend fun clearPopularTv(){
        moviesDao.deleteAllPopularMovies()
    }


}