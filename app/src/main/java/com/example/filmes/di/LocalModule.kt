package com.example.filmes.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModelProvider
import com.example.filmes.R
import com.example.filmes.data.local.AppDatabase
import com.example.filmes.data.local.repository.MovieDataSource
import com.example.filmes.data.local.repository.MovieLocalRepository
import com.example.filmes.domain.usecase.SharedPreferecesConfig
import com.example.filmes.domain.usecase.local.InsertMovieImplementation
import com.example.filmes.domain.usecase.local.InsertMovieUseCase
import com.example.filmes.domain.usecase.local.SelectMovieImplementation
import com.example.filmes.presentation.viewmodel.PreferencesViewModel
import com.example.filmes.presentation.viewmodel.local.InsertMovieViewModel
import com.example.filmes.presentation.viewmodel.local.SelectMovieViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val preferencesModule = module {
    viewModel { PreferencesViewModel(preferecesConfig = get()) }

    single { SharedPreferecesConfig(configurarPreferences(androidApplication())) }
}

val daoModule = module {

    viewModel {
        InsertMovieViewModel(insertMovieUseCase = get())
    }
}

val appDaoModule = module {
    single { MovieDataSource(AppDatabase.getInstance(androidContext()).movieDAO) as MovieLocalRepository }
    single { InsertMovieImplementation(movieLocalRepository = get()) as InsertMovieUseCase}
}

private fun configurarPreferences(app: Application): SharedPreferences =
    app.getSharedPreferences(R.string.KEY_PREFERENCES.toString(), Context.MODE_PRIVATE)