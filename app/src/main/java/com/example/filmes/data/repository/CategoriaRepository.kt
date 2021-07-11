package com.example.filmes.data.repository

import android.util.Log
import com.example.filmes.data.api.FilmeRetrofitTask
import com.example.filmes.data.database.CategoriaDatabase
import com.example.filmes.data.model.ResultadoCategoriaDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CategoriaRepository(val retrofitTask: FilmeRetrofitTask) : CategoriaDatabase {

    private val TAG = "CategoriaRepository"

    override suspend fun getAllCategorias(): ResultadoCategoriaDto? {
        return withContext(Dispatchers.Default){
            try{
                var response = retrofitTask.getRetrofitTask().getAllCategorias(
                    retrofitTask.chaveApi,
                    retrofitTask.linguagem
                )

                if(response.isSuccessful){
                    response.body()
                }else{
                    Log.d(TAG, "Erro: ${response.errorBody()} ")
                    Log.d(TAG, "Codigo: ${response.code()} ")
                    null
                }
            }catch (ex:Exception){
                Log.d(TAG, "Erro: ${ex.message} ")
                null
            }
        }
    }

}