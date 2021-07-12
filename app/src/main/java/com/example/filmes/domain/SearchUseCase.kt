package com.example.filmes.domain

import android.util.Log
import com.example.filmes.data.repository.MovieRepository
import com.example.filmes.domain.model.ResultsMoviesDto

class SearchUseCase(val movieRepository: MovieRepository) {

    private val TAG = "SearchUseCase"

    suspend operator fun invoke(nome:String): ResultsMoviesDto = try {
        movieRepository.getAllPopulars()
    }catch (ex:Exception){
        Log.d(TAG, "Error: $ex")
        ResultsMoviesDto(arrayListOf(), Integer(0), Integer(0))
    }
}