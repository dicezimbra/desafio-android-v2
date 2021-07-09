package com.example.filmes.data.database

import com.example.filmes.data.model.ResultadoCategoriaDto

interface CategoriaDatabase {

    fun getAllCategorias() : ResultadoCategoriaDto
}