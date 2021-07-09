package com.example.filmes.data.api

import retrofit2.Callback
import retrofit2.http.Query

interface FilmeApi {

    @Query("")
    fun getAllPopulares() : Callback<>
}