package com.example.filmes.presentation.view

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.filmes.R
import com.example.filmes.data.model.FilmeDto
import com.example.filmes.presentation.view.adapter.FavoritoAdapter
import com.example.filmes.presentation.view.adapter.OnItemClickFavoritoListener
import com.example.filmes.presentation.viewModel.SharedPreferencesViewModel
import com.example.filmes.utils.SharedPreferecesConfig
import kotlinx.android.synthetic.main.fragment_favorito.*

class FavoritoFragment : Fragment() , OnItemClickFavoritoListener{

    lateinit var listFilmeSalvo:ArrayList<FilmeDto>
    lateinit var preferencesConfig: SharedPreferecesConfig
    lateinit var preferencesViewModel : SharedPreferencesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_favorito, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initObserver()
    }

    private fun initView() {
        preferencesConfig = SharedPreferecesConfig(sharedInstance())
        preferencesViewModel = ViewModelProvider(
            this,
            SharedPreferencesViewModel.ViewModelFactory(preferencesConfig)
        ).get(SharedPreferencesViewModel::class.java)

        preferencesViewModel.getListaSalva()
    }

    private fun initObserver() {
        preferencesViewModel.liveAllFilmesSalvos.observe(requireActivity(), Observer { listSalvo ->
            if(listSalvo.isNotEmpty()) textViewNenhumFavorito.visibility = View.GONE
            else textViewNenhumFavorito.visibility = View.VISIBLE

            listFilmeSalvo = listSalvo
            atualizarRecycler(listFilmeSalvo)
        })
    }

    private fun sharedInstance() : SharedPreferences {
        return requireActivity().getSharedPreferences("com.example.filmes", AppCompatActivity.MODE_PRIVATE)
    }

    private fun atualizarRecycler(listFilmeSalvo : ArrayList<FilmeDto>) {
        recyclerViewFavoritos.layoutManager = LinearLayoutManager(activity)
        recyclerViewFavoritos.adapter = FavoritoAdapter(this, listFilmeSalvo)
    }

    override fun onClick(posicao: Int) {
        var intent = Intent(activity, DetalhesActivity::class.java)
        intent.putExtra(R.string.KEY_FILME.toString(), listFilmeSalvo[posicao])
        startActivity(intent)
    }

    override fun onClickButtonFavorito(filme: FilmeDto) {
        Log.d("TAG", "tamanho"+listFilmeSalvo.size)
        preferencesViewModel.inserirListFavorito(filme, listFilmeSalvo)
    }
}