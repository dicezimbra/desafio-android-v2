package com.example.filmes.presentation.viewmodel

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

    private val mMovieList = MutableLiveData<ResultsMoviesDto>()
    private val mError = MutableLiveData<Boolean>()

    val movieList: LiveData<ResultsMoviesDto>
        get() = mMovieList

    val error: LiveData<Boolean>
        get() = mError

    fun searchMovie(nome:String){
        CoroutineScope(Dispatchers.Main).launch {
            var resultsMovies = withContext(Dispatchers.Default) {
                searchUseCase.invoke(nome)
            }
            if(!resultsMovies.movieList.isNullOrEmpty())
                mMovieList.value = resultsMovies
            else
                mError.value = false
        }
    }
}