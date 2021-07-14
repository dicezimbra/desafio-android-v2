package com.example.filmes.data.local.repository

import androidx.lifecycle.LiveData
import com.example.filmes.data.local.entity.MovieEntity

interface MovieLocalRepository {

    suspend fun insertMovie(movieEntity: MovieEntity): Long

    suspend fun deleteMovie(id: Long)

    suspend fun verificarFilme(id: Long): Boolean

    fun getAllMovie(): LiveData<List<MovieEntity>>

    fun getSearchName(titulo: String): LiveData<List<MovieEntity>>
}