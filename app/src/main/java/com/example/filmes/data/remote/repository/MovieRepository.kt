package com.example.filmes.data.remote.repository

import android.util.Log
import com.example.filmes.utilis.LANGUAGE
import com.example.filmes.utilis.TAG_MOVIE
import com.example.filmes.utilis.USER_KEY
import com.example.filmes.data.remote.network.RetrofitTask
import com.example.filmes.domain.model.ResultsMoviesDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class MovieImplementation(val retrofitTask: RetrofitTask) : MovieRepository {

    override suspend fun getAllPopulars(): ResultsMoviesDto {
        return withContext(Dispatchers.Default){
                var response = retrofitTask.getRetrofitTask().getAllPopularMovies()
            verificarResponse(response)
        }

    }

    override suspend fun getSearchMovies(name:String): ResultsMoviesDto {
        return withContext(Dispatchers.Default){
                var response = retrofitTask.getRetrofitTask()
                    .getSearchName( name = name)
                verificarResponse(response)
        }
    }

    fun verificarResponse(response : Response<ResultsMoviesDto>) : ResultsMoviesDto{
        return if(response.isSuccessful){
                response.body()!!
        }else{
            Log.d(TAG_MOVIE, "Error: ${response.errorBody()} ")
            Log.d(TAG_MOVIE, "Code: ${response.code()} ")
            ResultsMoviesDto(arrayListOf(), 0, 0)
        }
    }
}

interface MovieRepository {
    suspend fun getAllPopulars() : ResultsMoviesDto
    suspend fun getSearchMovies(name:String) : ResultsMoviesDto
}