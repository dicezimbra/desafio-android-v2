package com.example.filmes.data.api

import com.example.filmes.data.model.ResultadoCategoriaDto
import com.example.filmes.data.model.ResultadoFilmeDto
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FilmeApi {

    @GET("movie/popular")
    suspend fun getAllPopularesFilmes(
        @Query("api_key") apiChave:String,
        @Query("language") idioma:String
    ) : Response<ResultadoFilmeDto>

    @GET("search/movie")
    suspend fun getPesquisarNomeFilme(
        @Query("api_key") chaveApi:String,
        @Query("language") idioma:String,
        @Query("query") nomeFilme:String,
    ) : Response<ResultadoFilmeDto>

    @GET("genre/movie/list")
    suspend fun getAllCategorias(
        @Query("api_key") chaveApi:String,
        @Query("language") idioma:String
    ) : Response<ResultadoCategoriaDto>


}