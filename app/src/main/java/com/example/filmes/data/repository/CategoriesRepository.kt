package com.example.filmes.data.repository

import android.util.Log
import com.example.filmes.data.network.RetrofitTask
import com.example.filmes.domain.model.ResultsCategoriesDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CategoriesImplementation(val retrofitTask: RetrofitTask) : CategoriesRepository {

    private val TAG = "CategoriesRepository"

    override suspend fun getAllCategorias(): ResultsCategoriesDto {
        return withContext(Dispatchers.Default){
            var response = retrofitTask.getRetrofitTask().getAllCategories()
            if(response.isSuccessful){
                response.body()!!
            } else{
                Log.d(TAG, "Error: ${response.errorBody()} ")
                Log.d(TAG, "Code: ${response.code()} ")
                ResultsCategoriesDto(arrayListOf())
            }
        }

    }

}
interface CategoriesRepository {
    suspend fun getAllCategorias() : ResultsCategoriesDto
}