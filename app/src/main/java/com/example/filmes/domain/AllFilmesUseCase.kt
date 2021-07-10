package com.example.filmes.domain

import com.example.filmes.data.FilmeRepository
import com.example.filmes.data.model.ResultadoFilmeDto

class AllFilmesUseCase(val repository: FilmeRepository) {

    suspend operator fun invoke():ResultadoFilmeDto? = repository.getAllPopulare()
}