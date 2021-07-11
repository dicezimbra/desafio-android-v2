package com.example.filmes.data.repository

import android.util.Log
import com.example.filmes.data.api.FilmeRetrofitTask
import com.example.filmes.data.database.FilmeDataBase
import com.example.filmes.data.model.ResultadoFilmeDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.*
import java.nio.channels.NotYetConnectedException

class FilmeRepository(val retrofitTask: FilmeRetrofitTask) : FilmeDataBase {

    private val TAG = "FilmeRepository"

    override suspend fun getAllPopulare(): ResultadoFilmeDto? {
        return withContext(Dispatchers.Default){
            try{
                var response = retrofitTask.getRetrofitTask().getAllPopularesFilmes(
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

    override suspend fun getPesquisarFilme(nomeFilme:String): ResultadoFilmeDto? {
        return withContext(Dispatchers.Default){
            try{
                var response = retrofitTask.getRetrofitTask().getPesquisarNomeFilme(
                    retrofitTask.chaveApi, retrofitTask.linguagem, nomeFilme)

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