package com.example.filmes.presentation.viewmodel.local

import androidx.lifecycle.*
import com.example.filmes.domain.model.MovieDto
import com.example.filmes.domain.usecase.local.InsertMovieUseCase
import kotlinx.coroutines.launch

class InsertViewModel(
    private val insertMovieUseCase: InsertMovieUseCase
) :ViewModel(){

    private val mMensagem = MutableLiveData<String>()
    val mensagem:LiveData<String>
        get() = mMensagem


    fun insertMovie(movie: MovieDto){
        viewModelScope.launch {
            val insert = insertMovieUseCase.invoke(movie)

            if(insert == movie.id.toLong())
                mMensagem.value = "Cadastrado com Sucesso"
            else
                mMensagem.value = "Falha ao cadastrar"
        }
    }
}