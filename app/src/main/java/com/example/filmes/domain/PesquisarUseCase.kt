package com.example.filmes.domain

import com.example.filmes.data.model.ResultadoFilmeDto
import com.example.filmes.data.repository.FilmeRepository

class PesquisarUseCase(val repository: FilmeRepository) {

    suspend operator fun invoke(nome:String):ResultadoFilmeDto? = repository.getPesquisarFilme(nome)
}