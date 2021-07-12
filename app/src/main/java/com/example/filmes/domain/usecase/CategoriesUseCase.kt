package com.example.filmes.domain.usecase

import android.util.Log
import com.example.filmes.data.repository.CategoriesImplementation
import com.example.filmes.data.repository.CategoriesRepository
import com.example.filmes.domain.model.ResultsCategoriesDto

class CategoriesUseCase(private var categoriesImplementation: CategoriesImplementation) {

    private val TAG = "CategoriesUseCase"

    suspend operator fun invoke():ResultsCategoriesDto = try{
        categoriesImplementation.getAllCategorias()
    }catch (ex:Exception){
        Log.d(TAG, "Error: $ex")
        ResultsCategoriesDto(arrayListOf())
    }
}