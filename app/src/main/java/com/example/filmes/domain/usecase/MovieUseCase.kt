package com.example.filmes.domain.usecase

import android.util.Log
import com.example.filmes.data.repository.MovieImplementation
import com.example.filmes.data.repository.MovieRepository
import com.example.filmes.domain.model.ResultsCategoriesDto
import com.example.filmes.domain.model.ResultsMoviesDto

class MovieUseCase(val movieImplementation: MovieImplementation) {

    private val TAG = "MovieUseCase"

    suspend operator fun invoke(): ResultsMoviesDto = try {
        movieImplementation.getAllPopulars()
    }catch (ex:Exception){
        Log.d(TAG, "Error: $ex")
        ResultsMoviesDto(arrayListOf(), Integer(0), Integer(0))
    }
}