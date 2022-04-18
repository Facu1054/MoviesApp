package mitiempo.android.curso.linkedin.moviesapp.data.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import mitiempo.android.curso.linkedin.moviesapp.data.database.dao.MoviesDao
import mitiempo.android.curso.linkedin.moviesapp.data.database.entities.*

@Database(entities = [MoviesEntity::class,MoviesEntityPopular::class,MoviesEntityTop::class,
                     TvEntityPopular::class,TvEntityTop::class], version = 1,
)
abstract class MoviesDatabase: RoomDatabase() {

    abstract fun getMoviesDao():MoviesDao
}