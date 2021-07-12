package com.example.filmes.data.api

import com.example.filmes.domain.model.CategoriesDto
import com.example.filmes.domain.model.ResultsCategoriesDto
import com.example.filmes.domain.model.ResultsMoviesDto
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/popular")
    suspend fun getAllPopularMovies(
        @Query("api_key") keyApi:String,
        @Query("language") language:String
    ) : Response<ResultsMoviesDto>

    @GET("search/movie")
    suspend fun getSearchName(
        @Query("api_key") keyApi:String,
        @Query("language") language:String,
        @Query("query") movieName:String,
    ) : Response<ResultsMoviesDto>

    @GET("genre/movie/list")
    suspend fun getAllCategories(
        @Query("api_key") keyApi:String,
        @Query("language") language:String
    ) : Response<ResultsCategoriesDto>


}