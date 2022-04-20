package mitiempo.android.curso.linkedin.moviesapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import mitiempo.android.curso.linkedin.moviesapp.data.database.entities.*

/*import mitiempo.android.curso.linkedin.moviesapp.data.database.entities.MoviesEntityPopular
import mitiempo.android.curso.linkedin.moviesapp.data.database.entities.MoviesEntityTop*/

@Dao
interface MoviesDao {
    @Query("SELECT * FROM movies_table")
    suspend fun getAllMovies():List<MoviesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies:List<MoviesEntity>)

    @Query("DELETE FROM movies_table")
    suspend fun deleteAllMovies()

    //////////////////////////

    @Query("SELECT * FROM movies_top_table")
    suspend fun getAllMoviesTop():List<MoviesEntityTop>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTopMovies(movies:List<MoviesEntityTop>)

    @Query("DELETE FROM movies_top_table")
    suspend fun deleteAllTopMovies()

    //////////////////////////

    @Query("SELECT * FROM movies_popular_table")
    suspend fun getAllMoviesPopular():List<MoviesEntityPopular>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPopularMovies(movies:List<MoviesEntityPopular>)

    @Query("DELETE FROM movies_popular_table")
    suspend fun deleteAllPopularMovies()

    /////////

    @Query("SELECT * FROM tv_top_table")
    suspend fun getAllTvTop():List<TvEntityPopular>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTvTop(tv:List<TvEntityTop>)

    @Query("DELETE FROM tv_top_table")
    suspend fun deleteAllTvTop()

    ////////////
    @Query("SELECT * FROM tv_popular_table")
    suspend fun getAllTvPopular():List<TvEntityPopular>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTVPopular(tv:List<TvEntityPopular>)

    @Query("DELETE FROM tv_popular_table")
    suspend fun deleteAllTvPopular()

}