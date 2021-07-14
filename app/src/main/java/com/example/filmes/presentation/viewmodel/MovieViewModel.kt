package com.example.filmes.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.filmes.domain.model.MovieDto
import com.example.filmes.domain.usecase.MovieUseCase
import com.example.filmes.domain.model.ResultsMoviesDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieViewModel(private var movieUseCase: MovieUseCase) : ViewModel() {

    private val mMovieList = MutableLiveData<ArrayList<MovieDto>>()
    private val mError = MutableLiveData<Boolean>()

    val movieList: LiveData<ArrayList<MovieDto>>
        get() = mMovieList

    val error: LiveData<Boolean>
        get() = mError

    fun getAllMovies(name:String?){
        CoroutineScope(Dispatchers.Main).launch {
            var resultsMovies = withContext(Dispatchers.Default) {
                    movieUseCase.invoke(name)
                }
            if(!resultsMovies.movieList.isNullOrEmpty())
                mMovieList.value = resultsMovies.movieList
            else mError.value = name == null
        }
    }

}