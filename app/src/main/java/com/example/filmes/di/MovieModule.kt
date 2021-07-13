package com.example.filmes.di

import com.example.filmes.data.api.RetrofitTask
import com.example.filmes.data.repository.MovieImplementation
import com.example.filmes.domain.usecase.MovieUseCase
import com.example.filmes.presentation.viewmodel.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val MovieModule = module {

    single { RetrofitTask() }
    single { MovieUseCase(MovieImplementation(retrofitTask = get())) }

    viewModel {
        MovieViewModel(movieUseCase = get())
    }
}