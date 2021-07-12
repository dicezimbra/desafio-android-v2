package com.example.filmes.presentation.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.filmes.R
import com.example.filmes.data.repository.MovieImplementation
import com.example.filmes.data.api.RetrofitTask
import com.example.filmes.domain.model.MovieDto
import com.example.filmes.presentation.view.adapter.OnItemClickPopularListener
import com.example.filmes.presentation.viewModel.MovieViewModel
import com.example.filmes.presentation.view.adapter.PopularAdapter
import kotlinx.android.synthetic.main.fragment_popular.*

class PopularFragment : Fragment() , OnItemClickPopularListener{

    lateinit var movieViewModel: MovieViewModel
    lateinit var movieList:ArrayList<MovieDto>
    var retrofitTask = RetrofitTask()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_popular, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()

        progressBar_popular.visibility = View.VISIBLE
        refresh_popular.setOnRefreshListener {
            movieViewModel.getAllMovies()
            refresh_popular.isRefreshing = false
        }
    }

    private fun initView() {
        ConfigViewModel()
        ConfigObserver()
    }

    private fun ConfigViewModel() {
        movieViewModel = ViewModelProvider(
            this,
            MovieViewModel.ViewModelFactory(MovieImplementation(retrofitTask))
        ).get(MovieViewModel::class.java)

        movieViewModel.getAllMovies()
    }

    fun ConfigObserver(){
        movieViewModel.movieLiveData.observe(requireActivity(), Observer { filmes ->
            if(filmes.movieList.isNotEmpty()){
                movieList = filmes.movieList
                atualizarAdapterPopular(movieList)
                textViewErroConexao.visibility = View.GONE
            }else{
                textViewErroConexao.visibility = View.VISIBLE
            }
            progressBar_popular.visibility = View.GONE
    })}

    fun atualizarAdapterPopular(listMovies: ArrayList<MovieDto>){
        var popularAdapter = PopularAdapter(this, listMovies)
        recyclerView_popular.layoutManager = LinearLayoutManager(activity)
        recyclerView_popular.adapter = popularAdapter
    }

    override fun onClick(posicao: Int) {
//        movieViewModel.searchMovie(movieList[posicao].tituloFilme)
        var intent = Intent(activity, DetalhesActivity::class.java)
        intent.putExtra(R.string.KEY_MOVIE.toString(), movieList[posicao])
        startActivity(intent)
    }

}