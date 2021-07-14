package com.example.filmes.presentation.viewmodel.local

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.filmes.domain.usecase.local.DeleteMovieCaseUse
import kotlinx.coroutines.launch

class DeleteViewModel(
    private val deleteMovieUseCase: DeleteMovieCaseUse
) : ViewModel() {

    private val mMensagem = MutableLiveData<String>()
    val mensagem: LiveData<String>
        get() = mMensagem


    fun deleteMovie(id: Int){
        viewModelScope.launch {
            try{
                deleteMovieUseCase.invoke(id)
                mMensagem.value = "Deletado com sucesso"
            }catch (ex:Exception){
                Log.d("TAG", "insertMovie: ")
                mMensagem.value = "Falha ao deletar"
            }
        }
    }
}