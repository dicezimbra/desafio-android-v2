package com.example.filmes.presentation.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.filmes.R
import com.example.filmes.data.local.AppDatabase
import com.example.filmes.data.local.repository.MovieDataSource
import com.example.filmes.domain.model.MovieDto
import com.example.filmes.domain.usecase.local.SelectMovieImplementation
import com.example.filmes.presentation.view.adapter.FavoritoAdapter
import com.example.filmes.presentation.view.adapter.OnItemClickFavoritoListener
import com.example.filmes.presentation.viewmodel.PreferencesViewModel
import com.example.filmes.presentation.viewmodel.local.SelectMovieViewModel
import kotlinx.android.synthetic.main.fragment_favorito.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class FavoritoFragment : Fragment() , OnItemClickFavoritoListener {

    lateinit var listMovieSalvo:ArrayList<MovieDto>
    private val preferencesViewModel: PreferencesViewModel by viewModel()
    lateinit var selectMovieViewModel:SelectMovieViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorito, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setupDatabase()
    }

    private fun setupDatabase() {
        val appDatabase = AppDatabase.getInstance(requireContext()).movieDAO
        val movieDatabase = MovieDataSource(appDatabase)
        val selectMovieUseCase = SelectMovieImplementation(movieDatabase)

        selectMovieViewModel = ViewModelProvider(
            this, SelectMovieViewModel.ViewModelFactory(selectMovieUseCase))
            .get(SelectMovieViewModel::class.java)

        selectMovieViewModel.lista.observe(requireActivity()){
            Toast.makeText(requireActivity(), it[0].tituloFilme, Toast.LENGTH_SHORT)
//            updateAdapter()
        }
    }

    private fun initView() {
        getListaFilmes()
        refresh_favorite.setOnRefreshListener {
//            preferencesViewModel.getListaSalva()
            refresh_favorite.isRefreshing = false
        }
    }

    private fun getListaFilmes() {
        preferencesViewModel.getListaSalva()
        preferencesViewModel.listaFilmes.observe(requireActivity()) { listaSalvo ->
            if(listaSalvo.isNotEmpty())
                txt_none_favorite.visibility = View.GONE
            else
                txt_none_favorite.visibility = View.VISIBLE

            listMovieSalvo = listaSalvo
            updateAdapter(listMovieSalvo)
        }
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