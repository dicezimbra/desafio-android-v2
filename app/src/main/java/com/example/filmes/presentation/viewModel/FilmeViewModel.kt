package com.example.filmes.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.filmes.data.FilmeRepository
import com.example.filmes.data.model.FilmeDto
import com.example.filmes.domain.FilmesUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FilmeViewModel(filmeRepository: FilmeRepository) : ViewModel() {

    var filmesUseCase = FilmesUseCase(filmeRepository)

    private val TAG = "FilmeViewModel"
    var mutableAllFilmes = MutableLiveData<ArrayList<FilmeDto>>()
    var mutablePesquisarFilmes = MutableLiveData<ArrayList<FilmeDto>>()

    val liveDataListFilmes: LiveData<ArrayList<FilmeDto>>
        get() = mutableAllFilmes

    val liveDataPesquisarFilmes: LiveData<ArrayList<FilmeDto>>
        get() = mutablePesquisarFilmes

    fun getAllFilmes(){
        CoroutineScope(Dispatchers.Main).launch {
            var listFilmes = withContext(Dispatchers.Default) {
                 filmesUseCase.invoke()!!.listFilmeDtos
            }
            mutableAllFilmes.value = listFilmes
        }
    }


    class ViewModelFactory(val filmeRepository: FilmeRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(FilmeRepository::class.java)
                .newInstance(filmeRepository)
        }
    }
}