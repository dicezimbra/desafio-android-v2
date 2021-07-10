package com.example.filmes.presentation.view

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.edit
import com.bumptech.glide.Glide
import com.example.filmes.R
import com.example.filmes.data.api.FilmeRetrofitTask
import com.example.filmes.data.model.FilmeDto
import com.example.filmes.utils.SharedPreferecesConfig
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detalhes.*

class DetalhesActivity : AppCompatActivity() {

    var listFilmesSalvo = ArrayList<FilmeDto>()
    lateinit var preferencesConfig: SharedPreferecesConfig

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes)

        initView()
        floatingSalvar.setOnClickListener { salvarListFavorito(listFilmesSalvo) }
    }

    private fun initView() {
        var filme = intent.getParcelableExtra<FilmeDto>(R.string.KEY_FILME.toString())
        setSupportActionBar(toolbarDetalhes)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        Glide.with(this).load(FilmeRetrofitTask.BASE_IMAGEM + filme!!.backdropPath).into(imagemFilmeDetalhes)
        textViewNomeDetalhes.text = filme.tituloFilme
        textViewDescricaoDetalhes.text = filme.sinopse
        textViewNotaDetalhes.text = "${filme.notaMedia}/10 \nAvaliação"

        initPreferences()
    }

    private fun initPreferences() {
        preferencesConfig = SharedPreferecesConfig(sharedInstance())
        listFilmesSalvo = preferencesConfig.getListaSalva()
    }

    private fun sharedInstance() : SharedPreferences{
        return getSharedPreferences("com.example.filmes", MODE_PRIVATE)
    }

    private fun salvarListFavorito(listSalvo: ArrayList<FilmeDto>) {
        if(listSalvo.isNotEmpty()){
            preferencesConfig.setShared(listSalvo)
        }else{
            Toast.makeText(this, "Lista vazia", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}