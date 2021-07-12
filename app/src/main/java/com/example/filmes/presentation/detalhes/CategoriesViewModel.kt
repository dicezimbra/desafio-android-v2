package com.example.filmes.presentation.detalhes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.filmes.domain.model.ResultsCategoriesDto
import com.example.filmes.domain.usecase.CategoriesUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CategoriesViewModel(private var categoriesUseCase: CategoriesUseCase):ViewModel() {

    private val _resultsCategories = MutableLiveData<ResultsCategoriesDto>()

    val categories:LiveData<ResultsCategoriesDto>
        get() = _resultsCategories

    fun getCategories(){
        CoroutineScope(Dispatchers.Main).launch {
            var resultsCategories = withContext(Dispatchers.Default){
                categoriesUseCase.invoke()
            }
            _resultsCategories.value = resultsCategories
        }
    }
}