package com.example.filmes.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.filmes.data.model.FilmeDto
import com.example.filmes.utils.SharedPreferecesConfig

class SharedPreferencesViewModel(var preferecesConfig: SharedPreferecesConfig) :ViewModel(){

    var mutableVerificarFavorito = MutableLiveData<Boolean>()
    var mutableAllFilmesSalvos = MutableLiveData<ArrayList<FilmeDto>>()

    val liveVerificarFavorito:LiveData<Boolean>
        get() = mutableVerificarFavorito

    val liveAllFilmesSalvos:LiveData<ArrayList<FilmeDto>>
        get() = mutableAllFilmesSalvos

    fun inserirListFavorito(filme:FilmeDto, listFilmesSalvo:ArrayList<FilmeDto>){
        var jaEstaSalvo = false
        var posicaoList:Int = -1
        for(posicao in listFilmesSalvo.indices){
            if (filme.id == listFilmesSalvo[posicao].id){
                jaEstaSalvo = true
                posicaoList = posicao
            }
        }
        if(jaEstaSalvo) {
            listFilmesSalvo.removeAt(posicaoList)
        } else {
            listFilmesSalvo.add(filme)
        }

        preferecesConfig.setShared(listFilmesSalvo)
        getListaSalva()
    }

    fun verificarFavorito(filme:FilmeDto, listFilmesSalvo:ArrayList<FilmeDto>){
        var foiSalvo = false
        for(posicao in listFilmesSalvo.indices){
            if (filme.id == listFilmesSalvo[posicao].id){
                foiSalvo = true
            }
        }
        mutableVerificarFavorito.value = foiSalvo

        preferecesConfig.setShared(listFilmesSalvo)
    }

    fun getListaSalva(){
        mutableAllFilmesSalvos.value = preferecesConfig.getListaSalva()
    }


    class ViewModelFactory(var preferecesConfig: SharedPreferecesConfig) :ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(SharedPreferecesConfig::class.java)
                .newInstance(preferecesConfig)
        }
    }
}