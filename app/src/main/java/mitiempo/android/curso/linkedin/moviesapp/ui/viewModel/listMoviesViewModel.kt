package mitiempo.android.curso.linkedin.moviesapp.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mitiempo.android.curso.linkedin.moviesapp.data.model.dataJson.MoviesJson
import mitiempo.android.curso.linkedin.moviesapp.domain.GetMoviesUseCase

class listMoviesViewModel: ViewModel() {
    val movies = MutableLiveData<MoviesJson>()


    private var recyclerListData: MutableLiveData<List<MoviesJson>>
    var getQuotesUseCase = GetMoviesUseCase()
    init {
        recyclerListData =  MutableLiveData()
    }

    fun onCreate() {
        viewModelScope.launch {
            val result = getQuotesUseCase()

            if (result != null) {
                if(result.item_count != 0){
                    //Se le asigna el valor obtenido de retrofit
                    movies.postValue(result!!)
                }
            }
        }
    }







}