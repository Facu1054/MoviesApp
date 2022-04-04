package mitiempo.android.curso.linkedin.moviesapp.domain

import mitiempo.android.curso.linkedin.moviesapp.data.model.MoviesRepository
import mitiempo.android.curso.linkedin.moviesapp.data.model.dataJson.MoviesJson

class GetMoviesUseCase {

    private val repository = MoviesRepository()

    suspend operator fun invoke():MoviesJson? = repository.getMarvel()


}