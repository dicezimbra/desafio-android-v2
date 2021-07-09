package com.example.filmes.data

import android.util.Log
import com.example.filmes.data.api.FilmeRetrofitTask
import com.example.filmes.data.model.ResultadoCategoriaDto
import com.example.filmes.data.model.ResultadoFilmeDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.awaitResponse

class FilmeRepository(val retrofitTask: FilmeRetrofitTask) : FilmeDataBase{

    private val TAG = "FilmeRepository"

    override fun getAllPopulare(): ResultadoFilmeDto {
        var resultadoFilmeDto:ResultadoFilmeDto? = null

        var response = retrofitTask.getRetrofitTask().getAllPopularesFilmes(retrofitTask.chaveApi, retrofitTask.linguagem)
            .execute()
        if(response.isSuccessful){
            resultadoFilmeDto = response.body()!!
        }else{
            Log.d(TAG, "Erro: "+response.errorBody().toString())
            Log.d(TAG, "CODE: "+response.code())
        }

        return resultadoFilmeDto!!
    }
//
//    override fun getPesquisarFilme(): ResultadoFilmeDto {
//    }
//
//    override fun getAllCategorias(): ResultadoCategoriaDto {
//
//    }

}