package com.example.filmes.di

import com.example.filmes.data.network.RetrofitTask
import com.example.filmes.data.repository.CategoriesImplementation
import com.example.filmes.data.repository.CategoriesRepository
import com.example.filmes.data.repository.MovieImplementation
import com.example.filmes.data.repository.MovieRepository
import com.example.filmes.domain.usecase.CategoriesUseCase
import com.example.filmes.domain.usecase.MovieUseCase
import com.example.filmes.domain.usecase.SharedPreferecesConfig
import com.example.filmes.presentation.viewmodel.CategoriesViewModel
import com.example.filmes.presentation.viewmodel.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val movieModule = module {
    viewModel { MovieViewModel(movieUseCase = get()) }
}

val categoriesModule = module {
    viewModel { CategoriesViewModel(categoriesUseCase = get()) }
}

val appModule = module {
    single { RetrofitTask() }
    single { MovieUseCase(MovieImplementation(get()) as MovieRepository) }
    single { MovieImplementation(retrofitTask = get())}

    single { CategoriesUseCase(CategoriesImplementation(get()) as CategoriesRepository) }
    single { SharedPreferecesConfig(sharedPreferences = get()) }
}