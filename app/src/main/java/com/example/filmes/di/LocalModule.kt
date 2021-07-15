package com.example.filmes.di

import com.example.filmes.data.local.AppDatabase
import com.example.filmes.data.local.repository.MovieDataSource
import com.example.filmes.data.local.repository.MovieLocalRepository
import com.example.filmes.domain.usecase.local.*
import com.example.filmes.presentation.viewmodel.local.DeleteViewModel
import com.example.filmes.presentation.viewmodel.local.InsertViewModel
import com.example.filmes.presentation.viewmodel.local.SelectViewModel
import com.example.filmes.presentation.viewmodel.local.VerificarViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val daoModule = module {
    viewModel { InsertViewModel(insertMovieUseCase = get()) }
    viewModel { VerificarViewModel(verificarMovieUseCase = get()) }
    viewModel { DeleteViewModel(deleteMovieUseCase = get()) }
    viewModel { SelectViewModel(selectMovieUseCase = get()) }

    single { MovieDataSource(AppDatabase.getInstance(androidContext()).movieDAO) as MovieLocalRepository }
    single { InsertMovieImplementation(movieLocalRepository = get()) as InsertMovieUseCase}
    single { VerificarMovieImplementation(movieLocalRepository = get()) as VerificarMovieUseCase}
    single { DeleteMovieImplementation(movieLocalRepository = get()) as DeleteMovieCaseUse}
    single { SelectMovieImplementation(movieLocalRepository = get()) as SelectMovieUseCase}
}