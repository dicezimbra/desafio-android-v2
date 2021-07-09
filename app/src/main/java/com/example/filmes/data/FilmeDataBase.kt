package com.example.filmes.data

import com.example.filmes.data.model.ResultadoFilmeDto

interface FilmeDataBase {

    fun getAllPopulare() : ArrayList<ResultadoFilmeDto>
}