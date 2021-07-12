package com.example.filmes.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.filmes.domain.model.MovieDto
import com.example.filmes.domain.SharedPreferecesConfig

class SharedPreferencesViewModel(var preferecesConfig: SharedPreferecesConfig) :ViewModel(){

    private val _favorito = MutableLiveData<Boolean>()
    private val _listaFilme = MutableLiveData<ArrayList<MovieDto>>()
    private var foiSalvo = false
    var posicaoDaLista:Int = -1

    val favorito:LiveData<Boolean>
        get() = _favorito

    val listaFilmes:LiveData<ArrayList<MovieDto>>
        get() = _listaFilme

    fun inserirListFavorito(movie: MovieDto, listaSalva:ArrayList<MovieDto>){
        verificar(listaSalva, movie)
        if(foiSalvo)
            listaSalva.removeAt(posicaoDaLista)
        else
            listaSalva.add(movie)

        preferecesConfig.setListaSalva(listaSalva)
        getListaSalva()
    }

    fun verificarFavorito(movie: MovieDto, listFilmesSalvo:ArrayList<MovieDto>){
        verificar(listFilmesSalvo, movie)
        _favorito.value = foiSalvo
        preferecesConfig.setListaSalva(listFilmesSalvo)
    }

    fun getListaSalva(){
        _listaFilme.value = preferecesConfig.getListaSalva()
    }

    private fun verificar(listFilmesSalvo: ArrayList<MovieDto>, movie: MovieDto){
        foiSalvo = false
        for(posicao in listFilmesSalvo.indices){
            if (movie.id == listFilmesSalvo[posicao].id){
                foiSalvo = true
                posicaoDaLista = posicao
            }
        }
    }

    class ViewModelFactory(var preferecesConfig: SharedPreferecesConfig) :ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(SharedPreferecesConfig::class.java)
                .newInstance(preferecesConfig)
        }
    }
}