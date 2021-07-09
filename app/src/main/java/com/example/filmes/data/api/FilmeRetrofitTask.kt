package com.example.filmes.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FilmeRetrofitTask {

    companion object{
        private var retrofit:Retrofit? = null
        private const val BASE_URL = "https://api.themoviedb.org/3/"
        val chaveApi = "ae69ec81dd60f4108a88423265126573"
        val linguagem = "pt-br"

        private fun retrofitInstance() : Retrofit{
            if(retrofit == null){
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!
        }


        fun retrofitTask() = retrofitInstance()
    }

}