package com.example.filmes.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.filmes.R
import com.example.filmes.data.network.RetrofitTask
import com.example.filmes.data.repository.CategoriesImplementation
import com.example.filmes.data.repository.MovieImplementation
import com.example.filmes.domain.usecase.CategoriesUseCase
import com.example.filmes.domain.usecase.MovieUseCase
import com.example.filmes.domain.usecase.SearchUseCase
import com.example.filmes.domain.usecase.SharedPreferecesConfig
import com.example.filmes.presentation.viewmodel.CategoriesViewModel
import com.example.filmes.presentation.viewmodel.MovieViewModel
import com.example.filmes.presentation.viewmodel.PreferencesViewModel
import com.example.filmes.presentation.viewmodel.SeachViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val movieModule = module {
    viewModel { MovieViewModel(movieUseCase = get()) }
}

val categoriesModule = module {
    viewModel { CategoriesViewModel(categoriesUseCase = get()) }
}

val preferencesModule = module {
    viewModel { PreferencesViewModel(configurarPreferences(androidApplication())) }
}

val searchModule = module {
    viewModel { SeachViewModel(searchUseCase = get()) }
}

val appModule = module {
    single { RetrofitTask() }
    single { MovieUseCase(movieImplementation = get()) }
    single { MovieImplementation(retrofitTask = get())}
    single { SearchUseCase(movieImplementation = get()) }
    single { CategoriesImplementation(retrofitTask = get()) }
    single { CategoriesUseCase(categoriesImplementation = get()) }
    single { SharedPreferecesConfig(sharedPreferences = get()) }
}

private fun configurarPreferences(app: Application): SharedPreferences =
    app.getSharedPreferences(R.string.KEY_PREFERENCES.toString(), Context.MODE_PRIVATE)