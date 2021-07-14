package com.example.filmes.presentation.viewmodel.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.filmes.domain.model.MovieDto

class PreferencesViewModel() :ViewModel(){

    private val mFavorito = MutableLiveData<Boolean>()
    private val mListaFilme = MutableLiveData<ArrayList<MovieDto>>()
    private var foiSalvo = false
    var posicaoDaLista:Int = -1

    val favorito:LiveData<Boolean>
        get() = mFavorito

    val listaFilmes:LiveData<ArrayList<MovieDto>>
        get() = mListaFilme

    fun inserirListFavorito(movie: MovieDto, listaSalva:ArrayList<MovieDto>){
        verificar(listaSalva, movie)
        if(foiSalvo) listaSalva.removeAt(posicaoDaLista)
        else listaSalva.add(movie)
//        preferecesConfig.setListaSalva(listaSalva)
    }

    fun verificarFavorito(movie: MovieDto, listFilmesSalvo:ArrayList<MovieDto>){
        verificar(listFilmesSalvo, movie)
        mFavorito.value = foiSalvo
//        preferecesConfig.setListaSalva(listFilmesSalvo)
    }

    fun getListaSalva(){
//        mListaFilme.value = preferecesConfig.getListaSalva()
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
}