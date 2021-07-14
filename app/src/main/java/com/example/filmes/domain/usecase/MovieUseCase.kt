package com.example.filmes.domain.usecase

import android.util.Log
import com.example.filmes.utilis.TAG_MOVIE
import com.example.filmes.data.repository.MovieImplementation
import com.example.filmes.data.repository.MovieRepository
import com.example.filmes.domain.model.ResultsMoviesDto

class MovieUseCase(val movieRepository: MovieRepository) {

    suspend operator fun invoke(name:String?): ResultsMoviesDto = try {
        if(name == null) movieRepository.getAllPopulars()
        else movieRepository.getSearchMovies(name)
    }catch (ex:Exception){
        Log.d(TAG_MOVIE, "movieUseCase: $ex")
        ResultsMoviesDto(arrayListOf(), 0, 0)
    }
}