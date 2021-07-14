package com.example.filmes.domain.usecase.local

import androidx.lifecycle.LiveData
import com.example.filmes.data.local.repository.MovieLocalRepository

class VerificarMovieImplementation(
    private val movieLocalRepository: MovieLocalRepository
) : VerificarMovieUseCase{
    override suspend operator fun invoke(id:Int): Boolean = movieLocalRepository.verificarFilme(id.toLong())
}


interface VerificarMovieUseCase {
    operator suspend fun invoke(id:Int) :Boolean
}