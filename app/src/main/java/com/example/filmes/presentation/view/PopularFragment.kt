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
import com.example.filmes.data.repository.FilmeRepository
import com.example.filmes.data.api.FilmeRetrofitTask
import com.example.filmes.data.model.FilmeDto
import com.example.filmes.presentation.view.adapter.OnItemClickPopularListener
import com.example.filmes.presentation.viewModel.FilmeViewModel
import com.example.filmes.presentation.view.adapter.PopularAdapter
import kotlinx.android.synthetic.main.fragment_popular.*

class PopularFragment : Fragment() , OnItemClickPopularListener{

    lateinit var filmeViewModel: FilmeViewModel
    lateinit var listFilme:ArrayList<FilmeDto>
    var retrofitTask = FilmeRetrofitTask()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_popular, container, false)

        filmeViewModel = ViewModelProvider(
            this,
            FilmeViewModel.ViewModelFactory(FilmeRepository(retrofitTask))
        )
            .get(FilmeViewModel::class.java)

        filmeViewModel.getAllFilmes()
        initObserver()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBarPopular.visibility = View.VISIBLE
    }

    fun initObserver(){
        filmeViewModel.liveDataListFilmes.observe(requireActivity(), Observer { filmes ->
            if(filmes != null){
                listFilme = filmes
                atualizarAdapterPopular(listFilme)
            }else{
                textViewErroConexao.text = "Erro de conexão com a rede"
                textViewErroConexao.visibility = View.VISIBLE
            }
            progressBarPopular.visibility = View.GONE
    })}

    fun atualizarAdapterPopular(listFilmes: ArrayList<FilmeDto>){
        var popularAdapter = PopularAdapter(this, listFilmes)
        recyclerViewPopulares.layoutManager = LinearLayoutManager(activity)
        recyclerViewPopulares.adapter = popularAdapter
    }

    override fun onClick(posicao: Int) {
//        filmeViewModel.getPesquisarFilmes(listFilme[posicao].tituloFilme)

        var intent = Intent(activity, DetalhesActivity::class.java)
        intent.putExtra(R.string.KEY_FILME.toString(), listFilme[posicao])
        startActivity(intent)
    }

}