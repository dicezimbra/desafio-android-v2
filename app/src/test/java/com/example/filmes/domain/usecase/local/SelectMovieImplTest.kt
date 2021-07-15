package com.example.filmes.domain.usecase.local

import com.example.filmes.data.local.repository.MovieLocalRepository
import com.example.filmes.data.remote.repository.MovieImplementation
import com.example.filmes.domain.model.ResultsMoviesDto
import com.example.filmes.domain.usecase.remote.GetMovie
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Test

class SelectMovieImplementationTest{
    val repository: MovieLocalRepository = mockk()

    @Test
    fun `Quando o invoke do SelectMovieImple tiver o paramento null deve chamar o getAllMovie`(){

        coEvery { repository.getAllMovie() } returns arrayListOf()

        runBlockingTest {
            SelectMovieImplementation(repository).invoke(null)
        }

        coVerify { repository.getAllMovie() }
    }

    @Test
    fun `Quando o invoke do SelectMovieImple tiver paramentro diferente de null deve chamar o getSearchName`(){
        val name = "Harry"
        coEvery { repository.getSearchName(name) } returns arrayListOf()

        runBlockingTest {
            SelectMovieImplementation(repository).invoke(name)
        }

        coVerify { repository.getSearchName(name) }
    }
}