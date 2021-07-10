package com.example.filmes.domain

import com.example.filmes.data.FilmeRepository

class PesquisarUseCase(val repository: FilmeRepository) {

    suspend operator fun invoke(nome:String) = repository.getPesquisarFilme(nome)
}