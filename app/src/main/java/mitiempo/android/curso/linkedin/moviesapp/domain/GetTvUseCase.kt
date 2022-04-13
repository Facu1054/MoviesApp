package mitiempo.android.curso.linkedin.moviesapp.domain

import mitiempo.android.curso.linkedin.moviesapp.data.model.MoviesRepository
import mitiempo.android.curso.linkedin.moviesapp.data.model.dataJson.*

class GetTvFilterUseCases(){
    private val repository = MoviesRepository()

    suspend operator fun invoke(filter: String): TvFilter? = repository.geTvFilter(filter)
}
class GetTopRatesTvUseCase {

    private val repository = MoviesRepository()

    suspend operator fun invoke():MoviesTopRate? = repository.getTopRatesMovies()


}

class GetPopularTvUseCase {

    private val repository = MoviesRepository()

    suspend operator fun invoke():MoviesPopular? = repository.getPopularMovies()


}