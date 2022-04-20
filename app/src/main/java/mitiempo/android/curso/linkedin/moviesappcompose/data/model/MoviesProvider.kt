package mitiempo.android.curso.linkedin.moviesapp.data.model

import mitiempo.android.curso.linkedin.moviesapp.data.model.dataJson.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesProvider @Inject constructor(){
    var results: List<Item> = emptyList()
    var resultsFilter: List<Result> = emptyList()
    var resultsFilterTv: List<ResultTv> = emptyList()
    var resultsTopRates: List<ResultTvTopRates> = emptyList()
    var resultsPopular: List<ResultTvPopular> = emptyList()







}