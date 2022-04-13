package mitiempo.android.curso.linkedin.moviesapp.data.model.dataJson

import mitiempo.android.curso.linkedin.moviesapp.data.model.dataJson.Item

data class MoviesTopRate(
    val total_results: Int = 0,
    val total_pages: Int = 0,
    val page: Int = 0,
    val results: List<Item> = mutableListOf(),
)