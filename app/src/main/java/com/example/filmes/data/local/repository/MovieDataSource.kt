package com.example.filmes.data.local.repository

import androidx.lifecycle.LiveData
import com.example.filmes.data.local.dao.MovieDao
import com.example.filmes.data.local.entity.MovieEntity

class MovieDataSource(
    private var movieDao: MovieDao
) : MovieLocalRepository {
    override suspend fun insertMovie(movieEntity: MovieEntity): Long {
        return movieDao.insertMovie(movieEntity)
    }

    override suspend fun deleteMovie(id: Long) {
        movieDao.deleteMovie(id)
    }

    override suspend fun verificarFilme(id: Long): Boolean {
        return movieDao.verificarFilme(id)
    }

    override fun getAllMovie(): LiveData<List<MovieEntity>> {
        return movieDao.getAllMovie()
    }

    override fun getSearchName(titulo: String): LiveData<List<MovieEntity>> {
        return movieDao.getSearchName(titulo)
    }
}