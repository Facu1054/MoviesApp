package mitiempo.android.curso.linkedin.moviesapp.ui.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import mitiempo.android.curso.linkedin.moviesapp.data.model.dataJson.*
import mitiempo.android.curso.linkedin.moviesapp.domain.*
import mitiempo.android.curso.linkedin.moviesapp.domain.model.Movies
import mitiempo.android.curso.linkedin.moviesapp.domain.model.Tv
import javax.inject.Inject

@HiltViewModel
class listTvViewModel @Inject constructor(
    private val getTvUseCaseFilter: GetTvFilterUseCases,
    private val getTvTopRatesUseCase : GetTopRatesTvUseCase,
    private val getTvPopularUseCase : GetPopularTvUseCase
): ViewModel() {
    val popularTv = MutableLiveData<List<Tv>>()
    val topRatesTv = MutableLiveData<List<Tv>>()
    //val popularMovies = MutableLiveData<TvPoupular>()


    var tvList:MutableList<List<Tv>> = mutableListOf()
    var tvListFilt:List<ResultTv> = mutableListOf()
    var tempSearch = mutableSetOf<String>()

    var tvListFilter= MutableLiveData<TvFilter>()

    var imageStr: String = ""


    private var recyclerListData: MutableLiveData<List<MoviesJson>>





    init {
        recyclerListData =  MutableLiveData()
    }

    fun onCreate() {
        viewModelScope.launch {
            val result = getTvPopularUseCase()
            Log.i("Test222","tem")

            if (result != null) {
                    //Se le asigna el valor obtenido de retrofit
                    popularTv.postValue(result!!)


            }
        }
    }

    fun invokeFilter (query : String){
        Log.i("Test2w",query)
        viewModelScope.launch {

            val result = getTvUseCaseFilter(query)

            if (result != null) {
                if(result.total_results != 0){

                    //Se le asigna el valor obtenido de retrofit
                    tvListFilter.postValue(result!!)
                    Log.i("Test2w",result.total_results.toString())

                }
            }
        }
    }


    fun getTopRates() {
        viewModelScope.launch {
            val result = getTvTopRatesUseCase()
            Log.i("Test222","tem")

            if (result != null) {
                    //Se le asigna el valor obtenido de retrofit
                    topRatesTv.postValue(result!!)


            }
        }
    }

    fun getPopular() {
        viewModelScope.launch {
            val result = getTvPopularUseCase()
            Log.i("Test222","tem")

            if (result != null) {
                    //Se le asigna el valor obtenido de retrofit
                    popularTv.postValue(result!!)


            }
        }
    }





}