package com.example.filmes

import android.app.Application
import com.example.filmes.di.categoriesModule
import com.example.filmes.di.movieModule
import com.example.filmes.di.appModule
import com.example.filmes.di.preferencesModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApp  : Application(){

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.INFO)
            androidContext(this@MainApp)
            modules(listOf(appModule, movieModule, categoriesModule, preferencesModule))
        }
    }
}