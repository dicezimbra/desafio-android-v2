package com.example.filmes.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.filmes.data.repository.MovieImplementation
import com.example.filmes.domain.MovieUseCase
import com.example.filmes.domain.SearchUseCase
import com.example.filmes.domain.model.ResultsMoviesDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieViewModel(movieImplementation: MovieImplementation) : ViewModel() {

    var movieUseCase = MovieUseCase(movieImplementation)
    var searchUseCase = SearchUseCase(movieImplementation)

    var movieListMutable = MutableLiveData<ResultsMoviesDto>()

    val movieLiveData: LiveData<ResultsMoviesDto>
        get() = movieListMutable

    fun getAllMovies(){
        CoroutineScope(Dispatchers.Main).launch {
            var movieList = withContext(Dispatchers.Default) {
                    movieUseCase.invoke()
                }
            movieListMutable.value = movieList
        }
    }

    fun searchMovie(nome:String){
        CoroutineScope(Dispatchers.Main).launch {
            var movieList = withContext(Dispatchers.Default) {
                searchUseCase.invoke(nome)
            }
            movieListMutable.value = movieList
        }
    }

    class ViewModelFactory(val movieImplementation: MovieImplementation) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(MovieImplementation::class.java)
                .newInstance(movieImplementation)
        }
    }
}