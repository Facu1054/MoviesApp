package mitiempo.android.curso.linkedin.moviesapp.data.model.dataJson

data class TvFilter(
    val results: List<ResultTv> = mutableListOf(),
    val page: Int = 0,
    val total_pages: Int = 0,
    val total_results: Int = 0
)