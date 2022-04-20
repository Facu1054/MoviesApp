package mitiempo.android.curso.linkedin.moviesapp.ui.viewModel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import mitiempo.android.curso.linkedin.moviesapp.data.model.dataJson.*
import mitiempo.android.curso.linkedin.moviesapp.domain.GetMoviesFilterUseCases
import mitiempo.android.curso.linkedin.moviesapp.domain.GetMoviesUseCase
import mitiempo.android.curso.linkedin.moviesapp.domain.GetPopularMoviesUseCase
import mitiempo.android.curso.linkedin.moviesapp.domain.GetTopRatesMoviesUseCase
import mitiempo.android.curso.linkedin.moviesapp.domain.model.Movies
import javax.inject.Inject

@HiltViewModel
class listMoviesViewModel @Inject constructor(
    private val getMoviesUseCase : GetMoviesUseCase,
    private val getTopRatesUseCase : GetTopRatesMoviesUseCase,
    private val getPopularUseCase : GetPopularMoviesUseCase,
    private val getFilterMoviesUseCaseFilter : GetMoviesFilterUseCases
): ViewModel() {
    val movies = MutableLiveData<List<Movies>>()
    val topRatesMovies = MutableLiveData<List<Movies>>()
    val popularMovies = MutableLiveData<List<Movies>>()
    val statusFilter = MutableLiveData<Boolean>()



    //var moviesListPop:MutableList<List<Movies>> = mutableListOf()

    var moviesList:MutableList<List<Movies>> = mutableListOf()
    var moviesListFilt:List<Result> = mutableListOf()
    var tempSearch = mutableSetOf<String>()

    var moviesListFilter= MutableLiveData<Filter>()

    var imageStr: String = ""
    /*var getMoviesUseCase = GetMoviesUseCase()
    var getTopRatesUseCase = GetTopRatesMoviesUseCase()
    var getPopularUseCase = GetPopularMoviesUseCase()


    var getFilterMoviesUseCaseFilter = GetMoviesFilterUseCases()*/

    private var recyclerListData: MutableLiveData<List<MoviesJson>>


    init {
        recyclerListData =  MutableLiveData()
    }

    fun onCreate() {
        viewModelScope.launch {
            val result = getMoviesUseCase()
            Log.i("Test222","tem")

            if (result != null) {
                //Se le asigna el valor obtenido de retrofit
                movies.postValue(result!!)


            }
        }
    }
    /*fun image (url: String){
        viewModelScope.launch {
            val image = getQuotesUseCase.invokeImage(url)
            imageStr = image ?: ""
        }
    }*/
    //invokeFilter
    fun invokeFilter (query : String){
        Log.i("Test2w",query)
        viewModelScope.launch {
            try {


                val result = getFilterMoviesUseCaseFilter(query)

                if (result != null) {
                    if (result.total_results != 0) {

                        //Se le asigna el valor obtenido de retrofit
                        moviesListFilter.postValue(result!!)
                        Log.i("Test2w", result.total_results.toString())
                        statusFilter.postValue(false)

                    }
                }
            }catch (e: Exception){
                Log.i("Error","Se necesita Internet para esta operacion")
                statusFilter.postValue(true)

            }
        }
    }


    fun getTopRates() {
        viewModelScope.launch {
            val result = getTopRatesUseCase()
            Log.i("Test222","tem")

            if (result != null) {
                //Se le asigna el valor obtenido de retrofit
                topRatesMovies.postValue(result!!)


            }
        }
    }

    fun getPopular() {
        viewModelScope.launch {
            val result = getPopularUseCase()
            Log.i("Test222","tem")

            if (result != null) {
                //Se le asigna el valor obtenido de retrofit
                popularMovies.postValue(result!!)

            }
        }
    }





}