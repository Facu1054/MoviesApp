package mitiempo.android.curso.linkedin.moviesapp.data.model

import mitiempo.android.curso.linkedin.moviesapp.data.model.dataJson.MoviesJson
import mitiempo.android.curso.linkedin.moviesapp.data.network.MoviesService

class MoviesRepository {

    private val api = MoviesService()


    suspend fun getMarvel():MoviesJson{
        val response = api.getSuperHeroes()

        MoviesProvider.results = response.items

        return response
    }



}