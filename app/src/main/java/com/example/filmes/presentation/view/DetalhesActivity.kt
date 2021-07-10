package com.example.filmes.presentation.view

import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.filmes.R
import com.example.filmes.data.api.FilmeRetrofitTask
import com.example.filmes.data.model.FilmeDto
import kotlinx.android.synthetic.main.activity_detalhes.*
import kotlinx.android.synthetic.main.filme_item.*
import kotlinx.android.synthetic.main.filme_item.view.*

class DetalhesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes)

        var filme = intent.getParcelableExtra<FilmeDto>(R.string.KEY_FILME.toString())
        setSupportActionBar(toolbarDetalhes)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        Glide.with(this).load(FilmeRetrofitTask.BASE_IMAGEM + filme?.backdropPath).into(imagemFilmeDetalhes)
        textViewNomeDetalhes.text = filme!!.tituloFilme
        textViewDescricaoDetalhes.text = filme!!.sinopse
        textViewNotaDetalhes.text = "${filme!!.notaMedia}/10 \nAvaliação"
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}