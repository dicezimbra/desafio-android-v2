package com.example.filmes.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FilmeRetrofitTask {

    companion object{
        val BASE_IMAGEM = "https://image.tmdb.org/t/p/original/"
    }

        private var retrofit:Retrofit? = null
        private val BASE_URL = "https://api.themoviedb.org/3/"
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

        fun getRetrofitTask() = retrofitInstance().create(FilmeApi::class.java)
}