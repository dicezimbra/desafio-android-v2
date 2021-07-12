package com.example.filmes.data.repository

import android.util.Log
import com.example.filmes.data.api.RetrofitTask
import com.example.filmes.domain.model.ResultsMoviesDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class MovieImplementation(val retrofitTask: RetrofitTask) : MovieRepository {

    private val TAG = "MovieImplementation"

    fun verificarResponse(response : Response<ResultsMoviesDto>) : ResultsMoviesDto{
        return if(response.isSuccessful){
            response.body()!!
        }else{
            Log.d(TAG, "Error: ${response.errorBody()} ")
            Log.d(TAG, "Code: ${response.code()} ")
            ResultsMoviesDto(arrayListOf(), Integer(0), Integer(0))
        }
    }

    override suspend fun getAllPopulars(): ResultsMoviesDto {
        return withContext(Dispatchers.Default){
                var response = retrofitTask.getRetrofitTask().getAllPopularMovies(
                    retrofitTask.api_key,
                    retrofitTask.language
                )
            verificarResponse(response)
        }

    }

    override suspend fun getSearchMovies(movieName:String): ResultsMoviesDto {
        return withContext(Dispatchers.Default){
                var response = retrofitTask.getRetrofitTask().getSearchName(
                    retrofitTask.api_key, retrofitTask.language, movieName)
                verificarResponse(response)
        }
    }

}

interface MovieRepository {

    suspend fun getAllPopulars() : ResultsMoviesDto

    suspend fun getSearchMovies(nomeFilme:String) : ResultsMoviesDto
}