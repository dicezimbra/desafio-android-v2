package com.example.filmes.di

import com.example.filmes.data.api.RetrofitTask
import com.example.filmes.data.repository.CategoriesImplementation
import com.example.filmes.domain.usecase.CategoriesUseCase
import com.example.filmes.presentation.viewmodel.CategoriesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val CategoriesModule = module {

    single { RetrofitTask() }
    single { CategoriesUseCase(CategoriesImplementation(retrofitTask = get())) }

    viewModel { CategoriesViewModel(categoriesUseCase = get()) }
}