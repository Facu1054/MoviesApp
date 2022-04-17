package mitiempo.android.curso.linkedin.moviesapp.domain

import mitiempo.android.curso.linkedin.moviesapp.data.model.MoviesRepository
import mitiempo.android.curso.linkedin.moviesapp.data.model.dataJson.*
import javax.inject.Inject

class GetTvFilterUseCases @Inject constructor(private val repository : MoviesRepository){


    suspend operator fun invoke(filter: String): TvFilter? = repository.geTvFilter(filter)
}
class GetTopRatesTvUseCase @Inject constructor(private val repository : MoviesRepository){



    suspend operator fun invoke():TvTopRates? = repository.getTopRatesTv()


}

class GetPopularTvUseCase @Inject constructor(private val repository : MoviesRepository){



    suspend operator fun invoke():TvPoupular? = repository.getPopularTv()


}