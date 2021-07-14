package com.example.filmes.di

import com.example.filmes.data.remote.network.RetrofitTask
import com.example.filmes.data.remote.repository.CategoriesImplementation
import com.example.filmes.data.remote.repository.CategoriesRepository
import com.example.filmes.data.remote.repository.MovieImplementation
import com.example.filmes.data.remote.repository.MovieRepository
import com.example.filmes.domain.usecase.remote.CategoriesUseCase
import com.example.filmes.domain.usecase.remote.MovieUseCase
import com.example.filmes.presentation.viewmodel.remote.CategoriesViewModel
import com.example.filmes.presentation.viewmodel.remote.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val movieModule = module {
    viewModel { MovieViewModel(movieUseCase = get()) }
    viewModel { CategoriesViewModel(categoriesUseCase = get()) }
}

val appModule = module {
    single { RetrofitTask() }
    single { MovieUseCase(MovieImplementation(get()) as MovieRepository) }
    single { MovieImplementation(retrofitTask = get()) }

    single { CategoriesUseCase(CategoriesImplementation(get()) as CategoriesRepository) }
}