package com.example.filmes.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.filmes.domain.model.ResultsMoviesDto
import com.example.filmes.domain.usecase.SearchUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SeachViewModel(private val searchUseCase: SearchUseCase) : ViewModel() {

    private val _resultsMovie = MutableLiveData<ResultsMoviesDto>()

    val movieList: LiveData<ResultsMoviesDto>
        get() = _resultsMovie

    fun searchMovie(nome:String){
        CoroutineScope(Dispatchers.Main).launch {
            var movieList = withContext(Dispatchers.Default) {
                searchUseCase.invoke(nome)
            }
            _resultsMovie.value = movieList
        }
    }
}