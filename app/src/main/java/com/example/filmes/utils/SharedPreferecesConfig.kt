package com.example.filmes.utils

import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.filmes.data.model.FilmeDto
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SharedPreferecesConfig(val sharedPreferences: SharedPreferences) {

    fun setShared(listFilme : ArrayList<FilmeDto>){
        sharedPreferences.edit {
            val json = Gson().toJson(listFilme)
            putString("listFilmesFavoritos", json).apply()
        }
    }

    fun getListaSalva() : ArrayList<FilmeDto>{
        var listSalva = sharedPreferences.getString("listFilmesFavoritos", "[]")
        val turnsType = object : TypeToken<List<FilmeDto>>() {}.type

        return Gson().fromJson(listSalva, turnsType)
    }
}