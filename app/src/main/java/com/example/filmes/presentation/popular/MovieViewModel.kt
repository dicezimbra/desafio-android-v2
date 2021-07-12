package com.example.filmes.presentation.popular

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.filmes.domain.usecase.MovieUseCase
import com.example.filmes.domain.model.ResultsMoviesDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieViewModel(private var movieUseCase: MovieUseCase) : ViewModel() {

    private val _movieList = MutableLiveData<ResultsMoviesDto>()

    val movieList: LiveData<ResultsMoviesDto>
        get() = _movieList

    fun getAllMovies(){
        CoroutineScope(Dispatchers.Main).launch {
            var movieList = withContext(Dispatchers.Default) {
                    movieUseCase.invoke()
                }
            this@MovieViewModel._movieList.value = movieList
        }
    }

}