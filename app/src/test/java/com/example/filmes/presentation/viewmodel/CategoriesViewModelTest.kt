package com.example.filmes.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.filmes.domain.model.CategoriesDto
import com.example.filmes.domain.model.MovieDto
import com.example.filmes.domain.model.ResultsCategoriesDto
import com.example.filmes.domain.usecase.CategoriesUseCase
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.*
import kotlin.collections.ArrayList

@ExperimentalCoroutinesApi
class CategoriesViewModelTest{

    @get:Rule
    val rule = InstantTaskExecutorRule()
    val categoriesUse = mockk<CategoriesUseCase>()
    val categoriesLiveData = mockk<Observer<String>>()
    private val mainThreadSurrogate = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.cleanupTestCoroutines()
    }

    @Test
    fun `Quando getCategories for chamado deve retornar uma observer lista de categorias`(){
        val viewModel = viewModelInstance()
        // filme com os gêneros 1 e 3
        val filme = mockFilme()
        val listaCategorias = mockCategorias()
        val ResultadoCategoria = ResultsCategoriesDto(listaCategorias)

        coEvery { categoriesUse.invoke() } returns ResultadoCategoria

        viewModel.getCategories(filme)

        coVerifyOrder {
            categoriesUse.invoke()
            categoriesLiveData.onChanged("romance, animação, ")
        }
    }

    @Test
    fun `Quando for chamado o getCategories deve retornar uma String de sem conexao`(){
        val viewModel = viewModelInstance()
        val filme = mockFilme()
        val ResultadoCategoria = ResultsCategoriesDto(arrayListOf())

        coEvery { categoriesUse.invoke() } returns ResultadoCategoria

        viewModel.getCategories(filme)

        coVerify { categoriesLiveData.onChanged("Sem conexão") }
    }

    private fun viewModelInstance():CategoriesViewModel{
        val viewModel = CategoriesViewModel(categoriesUse)
        viewModel.categories.observeForever(categoriesLiveData)
        return viewModel
    }

    private fun mockFilme():MovieDto{
        return MovieDto("","","", 1.0, Date()
            , intArrayOf(1,3),
            false,"",0,"","",0.0,false,0)
    }

    private fun mockCategorias(): ArrayList<CategoriesDto> {
        return arrayListOf(
            CategoriesDto(1,"romance"),
            CategoriesDto(2,"comédia"),
            CategoriesDto(3,"animação"))
    }
}