package mitiempo.android.curso.linkedin.moviesapp.data.model.dataJson

data class Filter(
    val page: Int = 0,
    val results: List<Result> = mutableListOf(),
    val total_pages: Int = 0,
    val total_results: Int = 0
)