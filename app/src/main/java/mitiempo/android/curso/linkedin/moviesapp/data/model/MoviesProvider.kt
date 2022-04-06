package mitiempo.android.curso.linkedin.moviesapp.data.model

import mitiempo.android.curso.linkedin.moviesapp.data.model.dataJson.Item
import mitiempo.android.curso.linkedin.moviesapp.data.model.dataJson.Result

class MoviesProvider {
    //todo  va a proveer el listado de heroes
    companion object {
        var results: List<Item> = emptyList()
        var resultsFilter: List<Result> = emptyList()




    }
}