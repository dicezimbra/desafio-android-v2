package com.example.filmes.data.remote.repository

import android.util.Log
import com.example.filmes.utilis.TAG_CATEGORIES
import com.example.filmes.data.remote.network.RetrofitTask
import com.example.filmes.domain.model.ResultsCategoriesDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CategoriesImplementation(val retrofitTask: RetrofitTask) : CategoriesRepository {

    override suspend fun getAllCategorias(): ResultsCategoriesDto {
        return withContext(Dispatchers.Default){
            var response = retrofitTask.getRetrofitTask().getAllCategories()
            if(response.isSuccessful){
                response.body()!!
            } else{
                Log.d(TAG_CATEGORIES, "Error: ${response.errorBody()} ")
                Log.d(TAG_CATEGORIES, "Code: ${response.code()} ")
                ResultsCategoriesDto(arrayListOf())
            }
        }

    }

}
interface CategoriesRepository {
    suspend fun getAllCategorias() : ResultsCategoriesDto
}