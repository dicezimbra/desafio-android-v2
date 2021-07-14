package com.example.filmes.domain.usecase

import android.util.Log
import com.example.filmes.utilis.TAG_CATEGORIES
import com.example.filmes.data.remote.repository.CategoriesImplementation
import com.example.filmes.data.remote.repository.CategoriesRepository
import com.example.filmes.domain.model.ResultsCategoriesDto

class CategoriesUseCase(private var categoriesRepository: CategoriesRepository) {

    suspend operator fun invoke():ResultsCategoriesDto = try{
        categoriesRepository.getAllCategorias()
    }catch (ex:Exception){
        Log.d(TAG_CATEGORIES, "categoriesUseCase: $ex")
        ResultsCategoriesDto(arrayListOf())
    }
}