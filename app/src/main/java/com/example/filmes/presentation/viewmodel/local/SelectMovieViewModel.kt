package com.example.filmes.presentation.viewmodel.local

import androidx.lifecycle.*
import com.example.filmes.data.local.entity.MovieEntity
import com.example.filmes.domain.model.MovieDto
import com.example.filmes.domain.usecase.local.InsertMovieUseCase
import com.example.filmes.domain.usecase.local.SelectMovieUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SelectMovieViewModel(
    private val selectMovieUseCase: SelectMovieUseCase
) : ViewModel() {

    private val mLista = MutableLiveData<List<MovieEntity>>()

    val lista = selectMovieUseCase.invoke()


    fun getSeachMovie(nome:String?){
        viewModelScope.launch {
            val listaEntity = withContext(Dispatchers.Default){
                selectMovieUseCase.invoke()
            }


        }
    }


    class ViewModelFactory(val selectMovieUseCase: SelectMovieUseCase) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(SelectMovieUseCase::class.java)
                .newInstance(selectMovieUseCase)
        }
    }

}