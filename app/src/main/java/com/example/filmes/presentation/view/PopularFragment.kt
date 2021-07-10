package com.example.filmes.presentation.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.filmes.R
import com.example.filmes.data.FilmeRepository
import com.example.filmes.data.api.FilmeRetrofitTask
import com.example.filmes.data.model.FilmeDto
import com.example.filmes.presentation.viewModel.FilmeViewModel
import com.example.filmes.presentation.view.adapter.OnItemClickFilmesListener
import com.example.filmes.presentation.view.adapter.PopularAdapter
import kotlinx.android.synthetic.main.fragment_popular.*

class PopularFragment : Fragment() , OnItemClickFilmesListener{

    private val TAG = "PopularFragment"
    lateinit var filmeViewModel: FilmeViewModel
    lateinit var listFilme:ArrayList<FilmeDto>
    var repository = FilmeRepository(FilmeRetrofitTask())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_popular, container, false)
        initView()

        return view
    }

    private fun initView() {
        filmeViewModel = ViewModelProvider(this, FilmeViewModel.ViewModelFactory(repository))
            .get(FilmeViewModel::class.java)
        filmeViewModel.getAllFilmes()

        initObserver()
    }

    fun initObserver(){
        filmeViewModel.liveDataListFilmes.observe(requireActivity(), Observer { filmes ->
            if(filmes != null){
                this.listFilme = filmes
                atualizarAdapterPopular(listFilme)
            }else{
                mostrarToast("É erro de conexão com a rede")
            }
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

    fun mostrarToast(toast: String) = Toast.makeText(activity, toast, Toast.LENGTH_SHORT).show()

}