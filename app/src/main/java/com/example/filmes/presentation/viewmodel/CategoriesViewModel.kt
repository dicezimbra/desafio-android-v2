package com.example.filmes.presentation.viewmodel

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

    private val mCategories = MutableLiveData<ResultsCategoriesDto>()

    val categories:LiveData<ResultsCategoriesDto>
        get() = mCategories

    fun getCategories(){
        CoroutineScope(Dispatchers.Main).launch {
            var resultsCategories = withContext(Dispatchers.Default){
                categoriesUseCase.invoke()
            }
            if(!resultsCategories.generosFilme.isNullOrEmpty())
                mCategories.value = resultsCategories
        }
    }
}