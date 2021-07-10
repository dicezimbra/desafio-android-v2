package com.example.filmes.domain

import com.example.filmes.data.FilmeRepository

class AllFilmesUseCase(val repository: FilmeRepository) {

    suspend operator fun invoke() = repository.getAllPopulare()
}