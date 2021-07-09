package com.example.filmes.presentation

import android.graphics.Movie
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.filmes.data.FilmeRepository
import com.example.filmes.data.api.FilmeRetrofitTask
import com.example.filmes.data.model.FilmeDto
import com.example.filmes.data.model.ResultadoFilmeDto

class FilmeViewModel(
    val filmeRepository: FilmeRepository
) : ViewModel() {

    private val TAG = "FilmeViewModel"
    var mutableAllFilmes = MutableLiveData<ArrayList<FilmeDto>>()
    var mutablePesquisarFilmes = MutableLiveData<ResultadoFilmeDto>()

    val liveDataListFilmes: LiveData<ArrayList<FilmeDto>>
        get() = mutableAllFilmes

    fun getAllFilmes(){
        Thread{
            try {
                mutableAllFilmes.postValue(filmeRepository.getAllPopulare().listFilmeDtos)
            }catch (ex:Exception){ Log.d(TAG, ex.toString())}
        }.start()
    }

    class ViewModelFactory(val filmeRepository: FilmeRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(FilmeRepository::class.java)
                .newInstance(filmeRepository)
        }
    }
}