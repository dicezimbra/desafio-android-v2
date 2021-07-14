package com.example.filmes.domain.usecase.local

import androidx.lifecycle.LiveData
import com.example.filmes.data.local.entity.MovieEntity
import com.example.filmes.data.local.repository.MovieLocalRepository
import com.example.filmes.domain.model.MovieDto

class SelectMovieImplementation(
    private val movieLocalRepository: MovieLocalRepository
) : SelectMovieUseCase{
    override operator fun invoke() = movieLocalRepository.getAllMovie()
//    }else{
//        movieLocalRepository.getSearchName(nome!!)
//    }
}

interface SelectMovieUseCase {
    operator fun invoke(): LiveData<List<MovieEntity>>
}