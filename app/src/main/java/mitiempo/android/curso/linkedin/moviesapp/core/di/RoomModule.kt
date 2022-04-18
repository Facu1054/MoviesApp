package mitiempo.android.curso.linkedin.moviesapp.core.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import mitiempo.android.curso.linkedin.moviesapp.data.database.MoviesDatabase
import mitiempo.android.curso.linkedin.moviesapp.data.database.dao.MoviesDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    private const val moviesDatabase = "movies_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) = Room.databaseBuilder(context,
        MoviesDatabase::class.java,moviesDatabase).build()

    @Singleton
    @Provides
    fun provideMoviesDao(db: MoviesDatabase) = db.getMoviesDao()
}