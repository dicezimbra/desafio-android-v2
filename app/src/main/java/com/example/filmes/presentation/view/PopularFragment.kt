package com.example.filmes.presentation.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.filmes.R
import com.example.filmes.domain.model.MovieDto
import com.example.filmes.presentation.view.adapter.OnItemClickPopularListener
import com.example.filmes.presentation.view.adapter.PopularAdapter
import com.example.filmes.presentation.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_popular.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PopularFragment : Fragment() , OnItemClickPopularListener {

    private val movieViewModel: MovieViewModel by viewModel()
    lateinit var movieList:ArrayList<MovieDto>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_popular, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
    }

    private fun initView() {
        getPopularMovies()
        getErro()
        progressBar_popular.visibility = View.VISIBLE
        refresh_popular.setOnRefreshListener {
            movieViewModel.getAllMovies()
            refresh_popular.isRefreshing = false
        }
    }

    fun getPopularMovies(){
        movieViewModel.getAllMovies()
        movieViewModel.movieList.observe(requireActivity()) { resultsMovies ->
            movieList = resultsMovies.movieList
            updateAdapter(movieList)
            progressBar_popular.visibility = View.GONE
        }
    }

    private fun getErro() {
        movieViewModel.error.observe(requireActivity()) {
            if(it == false) Toast.makeText(activity, "Erro de conexão", Toast.LENGTH_SHORT).show()
        }
    }

    fun updateAdapter(listMovies: ArrayList<MovieDto>){
        var popularAdapter = PopularAdapter(this, listMovies)
        recyclerView_popular.layoutManager = LinearLayoutManager(activity)
        recyclerView_popular.adapter = popularAdapter
    }

    override fun onClick(posicao: Int) {
        var intent = Intent(activity, DetalhesActivity::class.java)
        intent.putExtra(R.string.KEY_MOVIE.toString(), movieList[posicao])
        startActivity(intent)
    }

    fun searchMovie(name:String){
//        movieViewModel.searchMovie(name+"")
    }
}