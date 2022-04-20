package mitiempo.android.curso.linkedin.moviesapp.core

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitHelper {

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /*fun getRetrofitImage(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://image.tmdb.org/t/p/w500")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }*/
    //
}