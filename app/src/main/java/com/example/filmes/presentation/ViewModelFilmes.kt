package com.example.filmes.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.filmes.data.model.ResultadoFilmeDto

class ViewModelFilmes : ViewModel() {

    var mutableAllFilmes = MutableLiveData<ArrayList<ResultadoFilmeDto>>()
    var mutablePesquisarFilmes = MutableLiveData<ArrayList<ResultadoFilmeDto>>()
}