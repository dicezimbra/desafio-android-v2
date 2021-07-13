package com.example.filmes.presentation.view

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.filmes.R
import com.example.filmes.domain.model.MovieDto
import com.example.filmes.presentation.view.adapter.FavoritoAdapter
import com.example.filmes.presentation.view.adapter.OnItemClickFavoritoListener
import com.example.filmes.presentation.viewmodel.SharedPreferencesViewModel
import com.example.filmes.domain.usecase.SharedPreferecesConfig
import kotlinx.android.synthetic.main.fragment_favorito.*

class FavoritoFragment : Fragment() , OnItemClickFavoritoListener {

    lateinit var listMovieSalvo:ArrayList<MovieDto>
    lateinit var preferencesViewModel : SharedPreferencesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_favorito, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        ConfigViewModel()
        getListaFilmes()
        refresh_favorite.setOnRefreshListener {
            preferencesViewModel.getListaSalva()
            refresh_favorite.isRefreshing = false
        }
    }

    private fun ConfigViewModel() {
        preferencesViewModel = ViewModelProvider(
            this,
            SharedPreferencesViewModel.ViewModelFactory(sharedInstance())
        ).get(SharedPreferencesViewModel::class.java)
    }

    private fun getListaFilmes() {
        preferencesViewModel.getListaSalva()
        preferencesViewModel.listaFilmes.observe(requireActivity()) { listSalvo ->
            if(listSalvo.isNotEmpty())
                txt_none_favorite.visibility = View.GONE
            else
                txt_none_favorite.visibility = View.VISIBLE

            listMovieSalvo = listSalvo
            updateAdapter(listMovieSalvo)
        }
    }

    private fun sharedInstance() : SharedPreferences {
        return requireActivity().getSharedPreferences("com.example.filmes", AppCompatActivity.MODE_PRIVATE)
    }

    private fun updateAdapter(listMovieSalvo : ArrayList<MovieDto>) {
        recyclerView_favorite.layoutManager = LinearLayoutManager(activity)
        recyclerView_favorite.adapter = FavoritoAdapter(this, listMovieSalvo)
    }

    override fun onClick(posicao: Int) {
        var intent = Intent(activity, DetalhesActivity::class.java)
        intent.putExtra(R.string.KEY_MOVIE.toString(), listMovieSalvo[posicao])
        startActivity(intent)
    }

    override fun onClickButtonFavorito(movie: MovieDto) {
        preferencesViewModel.inserirListFavorito(movie, listMovieSalvo)
        preferencesViewModel.getListaSalva()
    }
}