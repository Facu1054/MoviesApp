package mitiempo.android.curso.linkedin.moviesapp.data.model.dataJson

data class TvPoupular(
    val results: List<ResultTvPopular> = mutableListOf(),
    val page: Int = 0,
    val total_pages: Int = 0,
    val total_results: Int = 0
)