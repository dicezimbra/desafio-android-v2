package com.example.filmes.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.filmes.data.model.FilmeDto
import com.example.filmes.data.repository.FilmeRepository
import com.example.filmes.domain.AllFilmesUseCase
import com.example.filmes.domain.PesquisarUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FilmeViewModel(filmeRepository: FilmeRepository) : ViewModel() {

    var filmesUseCase = AllFilmesUseCase(filmeRepository)
    var pesquisarUseCase = PesquisarUseCase(filmeRepository)

    private val TAG = "FilmeViewModel"
    var mutableAllFilmes = MutableLiveData<ArrayList<FilmeDto>>()

    val liveDataListFilmes: LiveData<ArrayList<FilmeDto>>
        get() = mutableAllFilmes

    fun getAllFilmes(){
        CoroutineScope(Dispatchers.Main).launch {
            var listFilmes = withContext(Dispatchers.Default) {
                    filmesUseCase.invoke()?.listFilmeDtos
                }
            mutableAllFilmes.value = listFilmes
        }
    }

    fun getPesquisarFilmes(nome:String){
        CoroutineScope(Dispatchers.Main).launch {
            var listFilmes = withContext(Dispatchers.Default) {
                pesquisarUseCase.invoke(nome)?.listFilmeDtos
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