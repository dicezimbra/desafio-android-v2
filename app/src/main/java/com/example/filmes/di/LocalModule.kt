package com.example.filmes.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.filmes.R
import com.example.filmes.domain.usecase.SharedPreferecesConfig
import com.example.filmes.presentation.viewmodel.PreferencesViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val preferencesModule = module {
    viewModel { PreferencesViewModel(preferecesConfig = get()) }

    single { SharedPreferecesConfig(configurarPreferences(androidApplication())) }
}

private fun configurarPreferences(app: Application): SharedPreferences =
    app.getSharedPreferences(R.string.KEY_PREFERENCES.toString(), Context.MODE_PRIVATE)