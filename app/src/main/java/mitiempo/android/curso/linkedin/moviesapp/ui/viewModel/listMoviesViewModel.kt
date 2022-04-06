package mitiempo.android.curso.linkedin.moviesapp.ui.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mitiempo.android.curso.linkedin.moviesapp.data.model.dataJson.Item
import mitiempo.android.curso.linkedin.moviesapp.data.model.dataJson.MoviesJson
import mitiempo.android.curso.linkedin.moviesapp.domain.GetMoviesFilterUseCases
import mitiempo.android.curso.linkedin.moviesapp.domain.GetMoviesUseCase
import retrofit2.http.Url

class listMoviesViewModel: ViewModel() {
    val movies = MutableLiveData<MoviesJson>()
    var moviesList:List<Item> = mutableListOf()
    var moviesListFilt:List<Item> = mutableListOf()

    var moviesListFilter= MutableLiveData<MoviesJson>()
    var imageStr: String = ""


    private var recyclerListData: MutableLiveData<List<MoviesJson>>
    var getQuotesUseCase = GetMoviesUseCase()
    var getQuotesUseCaseFilter = GetMoviesFilterUseCases()

    init {
        recyclerListData =  MutableLiveData()
    }

    fun onCreate() {
        viewModelScope.launch {
            val result = getQuotesUseCase()
            Log.i("Test222","tem")

            if (result != null) {
                if(result.item_count != 0){
                    //Se le asigna el valor obtenido de retrofit
                    movies.postValue(result!!)

                }
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
        viewModelScope.launch {
            val result = getQuotesUseCaseFilter(query)

            if (result != null) {
                if(result.item_count != 0){
                    Log.i("Test222",query)
                    //Se le asigna el valor obtenido de retrofit
                    moviesListFilter.postValue(result!!)

                }
            }
        }
    }






}