package com.example.filmes.domain.usecase.remote

import com.example.filmes.data.remote.repository.CategoriesImplementation
import com.example.filmes.domain.model.ResultsCategoriesDto
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class GetCategoriesTest{

    val repository:CategoriesImplementation = mockk()

    @Test
    fun `Quando invoke do GetCategories for chamado deve ser chamado o getAllCategorias`(){

        coEvery { repository.getAllCategorias() } returns ResultsCategoriesDto(arrayListOf())

        runBlockingTest {
            GetCategories(repository).invoke()
        }

        coVerify { repository.getAllCategorias() }
    }
}