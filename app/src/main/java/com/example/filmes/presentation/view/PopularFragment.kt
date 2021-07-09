package com.example.filmes.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmes.R
import com.example.filmes.data.FilmeRepository
import com.example.filmes.data.api.FilmeRetrofitTask
import com.example.filmes.data.model.FilmeDto
import com.example.filmes.presentation.FilmeViewModel
import com.example.filmes.presentation.view.adapter.OnItemClickFilmesListener
import com.example.filmes.presentation.view.adapter.PopularAdapter
import kotlinx.android.synthetic.main.fragment_popular.*

class PopularFragment : Fragment() , OnItemClickFilmesListener{

    private val TAG = "PopularFragment"
    lateinit var filmeViewModel:FilmeViewModel
    lateinit var listFilme:ArrayList<FilmeDto>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_popular, container, false)

        var repository = FilmeRepository(FilmeRetrofitTask())
        filmeViewModel = ViewModelProvider(this, FilmeViewModel.ViewModelFactory(repository)).get(FilmeViewModel::class.java)
        filmeViewModel.getAllFilmes()
        initObserver()

        return view
    }

    fun initObserver(){
        filmeViewModel.liveDataListFilmes.observe(requireActivity(), Observer { listFilme ->
            this.listFilme = listFilme
            atualizarAdapterPopular(listFilme)
    })}

    fun atualizarAdapterPopular(listFilmes : ArrayList<FilmeDto>){
        var popularAdapter = PopularAdapter(this, listFilmes)
        recyclerViewPopulares.layoutManager = LinearLayoutManager(activity)
        recyclerViewPopulares.adapter = popularAdapter
    }

    override fun onClick(posicao: Int) {
        Toast.makeText(activity, listFilme[posicao].tituloFilme, Toast.LENGTH_SHORT).show()
    }

}