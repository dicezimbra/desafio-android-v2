package com.example.filmes.data

import com.example.filmes.data.api.FilmeRetrofitTask
import com.example.filmes.data.database.FilmeDataBase
import com.example.filmes.data.model.ResultadoFilmeDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.*

class FilmeRepository(val retrofitTask: FilmeRetrofitTask) : FilmeDataBase {

    private val TAG = "FilmeRepository"

    override suspend fun getAllPopulare(): ResultadoFilmeDto? {
        var response:Response<ResultadoFilmeDto>
        return withContext(Dispatchers.Default){
            response = retrofitTask.getRetrofitTask().getAllPopularesFilmes(
                retrofitTask.chaveApi, retrofitTask.linguagem)

            if(response.isSuccessful){
                response.body()
            }else{
                null
            }
        }

    }

}