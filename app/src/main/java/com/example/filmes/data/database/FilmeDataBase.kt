package com.example.filmes.data.database

import com.example.filmes.data.model.ResultadoFilmeDto

interface FilmeDataBase {

    suspend fun getAllPopulare() : ResultadoFilmeDto?

//    fun getPesquisarFilme() : ResultadoFilmeDto

}