package mitiempo.android.curso.linkedin.moviesapp.domain

import mitiempo.android.curso.linkedin.moviesapp.data.model.MoviesRepository
import mitiempo.android.curso.linkedin.moviesapp.data.model.dataJson.Item
import mitiempo.android.curso.linkedin.moviesapp.data.model.dataJson.MoviesJson

class GetMoviesUseCase() {

    private val repository = MoviesRepository()

    suspend operator fun invoke():MoviesJson? = repository.getMarvel()




   // suspend  fun invokeImage(url: String):String? = repository.getImage(url)



}
class GetMoviesFilterUseCases(){
    private val repository = MoviesRepository()

    suspend operator fun invoke(filter: String):MoviesJson? = repository.getMoviesFilter(filter)
}