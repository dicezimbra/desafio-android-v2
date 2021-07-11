package com.example.filmes.presentation.view

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.filmes.R
import com.example.filmes.data.api.FilmeRetrofitTask
import com.example.filmes.data.model.FilmeDto
import com.example.filmes.presentation.viewModel.SharedPreferencesViewModel
import com.example.filmes.utils.SharedPreferecesConfig
import kotlinx.android.synthetic.main.activity_detalhes.*
import java.text.SimpleDateFormat


class DetalhesActivity : AppCompatActivity() {

    var listFilmesSalvo = ArrayList<FilmeDto>()
    lateinit var filme:FilmeDto
    lateinit var preferencesViewModel:SharedPreferencesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes)

        var preferencesConfig = SharedPreferecesConfig(sharedInstance())
        preferencesViewModel = ViewModelProvider(
            this,
            SharedPreferencesViewModel.ViewModelFactory(preferencesConfig)
        ).get(SharedPreferencesViewModel::class.java)


        initView()
        initObserver()
        floatingSalvar.setOnClickListener {
            preferencesViewModel.inserirListFavorito(filme, listFilmesSalvo)
            preferencesViewModel.verificarFavorito(filme, listFilmesSalvo)
        }
    }

    private fun initView() {
        filme = intent.getParcelableExtra(R.string.KEY_FILME.toString())!!
        setSupportActionBar(toolbarDetalhes)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        Glide.with(this).load(FilmeRetrofitTask.BASE_IMAGEM + filme.backdropPath).into(imagemFilmeDetalhes)
        textViewNomeDetalhes.text = filme.tituloFilme
        textViewDescricaoDetalhes.text = filme.sinopse
        val formatoData = SimpleDateFormat("dd/MM/yyyy")
        val dataLancamento = formatoData.format(filme.dataLancamento)
        textViewDataDetalhes.text = "Lançamento: $dataLancamento"
        textViewNotaDetalhes.text = "${filme.notaMedia}/10 \nAvaliação"

        preferencesViewModel.getListaSalva()
    }

    private fun initObserver() {
        preferencesViewModel.liveAllFilmesSalvos.observe(this, Observer { listSalvos ->
            listFilmesSalvo = listSalvos
            preferencesViewModel.verificarFavorito(filme, listFilmesSalvo)
        })

        preferencesViewModel.liveVerificarFavorito.observe(this, Observer { foiSalvo ->
            var imagemInt = if(foiSalvo){
                R.drawable.favorito
            }else{
                R.drawable.nao_favorito
            }
            floatingSalvar.setImageDrawable(ContextCompat.getDrawable(getBaseContext(), imagemInt));
        })
    }

    private fun sharedInstance() : SharedPreferences{
        return getSharedPreferences("com.example.filmes", MODE_PRIVATE)
    }

    override fun onSupportNavigateUp(): Boolean {
        var intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        return super.onSupportNavigateUp()
    }
}