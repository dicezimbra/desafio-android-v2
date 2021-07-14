package com.example.filmes.domain.usecase.local

import com.example.filmes.data.local.repository.MovieLocalRepository
import com.example.filmes.data.remote.repository.MovieRepository
import com.example.filmes.domain.model.MovieDto

class InsertMovieImplementation(
    private val movieLocalRepository: MovieLocalRepository
) : InsertMovieUseCase{
    override suspend operator fun invoke(movie: MovieDto) = movieLocalRepository.insertMovie(movie)
}
interface InsertMovieUseCase{
    suspend operator fun invoke(movie: MovieDto) : Long
}