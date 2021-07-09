package com.example.filmes.data

import com.example.filmes.data.model.ResultadoCategoriaDto
import com.example.filmes.data.model.ResultadoFilmeDto

interface FilmeDataBase {

    fun getAllPopulare() : ResultadoFilmeDto

//    fun getPesquisarFilme() : ResultadoFilmeDto
//
//    fun getAllCategorias() : ResultadoCategoriaDto
}