package com.example.filmes.data.local.repository

import androidx.lifecycle.LiveData
import com.example.filmes.data.local.dao.MovieDao
import com.example.filmes.data.local.entity.MovieEntity
import com.example.filmes.data.local.entity.MovieTypeConverter
import com.example.filmes.domain.model.MovieDto

class MovieDataSource(
    private var movieDao: MovieDao
) : MovieLocalRepository {
    override suspend fun insertMovie(movie: MovieDto): Long {
        val generos = MovieTypeConverter().fromJson(movie.generosIds)

        val movieEntity = MovieEntity(
            id = movie.id.toLong(),
            posterFilme = movie.posterFilme,
            tituloFilme = movie.tituloFilme,
            sinopse = movie.sinopse,
            notaMedia = movie.notaMedia,
            dataLancamento = movie.dataLancamento.toString(),
            adult = movie.adult,
            backdropPath = movie.backdropPath,
            originalLanguage = movie.originalLanguage,
            originalTitle = movie.originalTitle,
            popularity = movie.popularity,
            video = movie.video,
            voteCount = movie.voteCount,
            generosIds = generos
        )

        return movieDao.insertMovie(movieEntity)
    }

    override suspend fun deleteMovie(id: Long) {
        movieDao.deleteMovie(id)
    }

    override suspend fun verificarFilme(id: Long): Boolean {
        return movieDao.verificarFilme(id)
    }

    override fun getAllMovie(): List<MovieEntity> {
        return movieDao.getAllMovie()
    }

    override fun getSearchName(titulo: String): List<MovieEntity> {
        return movieDao.getSearchName(titulo)
    }
}