package com.example.filmes.domain.usecase.local

import com.example.filmes.data.local.repository.MovieLocalRepository
import com.example.filmes.domain.model.MovieDto
import io.mockk.InternalPlatformDsl.toStr
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Test
import java.util.*

class InsertMovieImplementationTest{

    val dataSource: MovieLocalRepository = mockk()

    @Test
    fun `Quando invoke do InsertMovieImpl for chamado deve ser chamado o insert do DataSouce e retornar o Id`(){

        val movie = MovieDto("Filme1","","", 1.0, Date(),intArrayOf(1,3),
                false,"",0,"","",0.0,false,0)

        coEvery { dataSource.insertMovie(movie, movie.dataLancamento.toString()) } returns movie.id.toLong()

        runBlockingTest {
            InsertMovieImplementation(dataSource).invoke(movie, movie.dataLancamento.toString())
        }

        coVerify {
            val data = dataSource.insertMovie(movie, movie.dataLancamento.toString())
            assertEquals(data, movie.id.toLong())
        }
    }
}