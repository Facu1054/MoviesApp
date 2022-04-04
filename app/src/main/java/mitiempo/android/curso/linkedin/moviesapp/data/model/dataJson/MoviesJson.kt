package mitiempo.android.curso.linkedin.moviesapp.data.model.dataJson

import mitiempo.android.curso.linkedin.moviesapp.data.model.dataJson.Item

data class MoviesJson(
    val created_by: String = "",
    val description: String = "",
    val favorite_count: Int = 0,
    val id: String = "",
    val iso_639_1: String = "",
    val item_count: Int = 0,
    val items: List<Item> = mutableListOf(),
    val name: String = "",
    val poster_path: String = ""
)