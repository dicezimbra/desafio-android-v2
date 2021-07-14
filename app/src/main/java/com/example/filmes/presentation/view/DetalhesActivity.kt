package com.example.filmes.presentation.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.filmes.R
import com.example.filmes.utilis.BASE_IMAGEM
import com.example.filmes.domain.model.MovieDto
import com.example.filmes.presentation.viewmodel.PreferencesViewModel
import com.example.filmes.presentation.viewmodel.CategoriesViewModel
import kotlinx.android.synthetic.main.activity_detalhes.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat


class DetalhesActivity : AppCompatActivity() {

    private val categoriesViewModel: CategoriesViewModel by viewModel()
    private val preferencesViewModel: PreferencesViewModel by viewModel()
    var listaSalva = ArrayList<MovieDto>()
    lateinit var movie: MovieDto

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes)

        initView()
    }

    private fun initView() {
        setupActionBar()
        setupMovie()
        getListaSalva()
        setupFavorito()
        setupCategories()
        floating_save_details.setOnClickListener {
            preferencesViewModel.inserirListFavorito(movie, listaSalva)
            preferencesViewModel.verificarFavorito(movie, listaSalva)
            preferencesViewModel.getListaSalva()
        }
    }

    private fun setupActionBar() {
        setSupportActionBar(toolbar_details)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupMovie() {
        movie = intent.getParcelableExtra(R.string.KEY_MOVIE.toString())!!
        Glide.with(this).load(BASE_IMAGEM + movie.backdropPath).into(img_movie_details)
        val dateFormat = SimpleDateFormat("dd/MM/yyyy")
        val realeseDate = dateFormat.format(movie.dataLancamento)
        txt_movie_date_details.text = "Lançamento: $realeseDate"
        txt_movie_note_details.text = "${movie.notaMedia}/10 \nAvaliação"
        txt_movie_title_details.text = movie.tituloFilme
        txt_movie_description_details.text = movie.sinopse
    }

    private fun getListaSalva() {
        preferencesViewModel.getListaSalva()
        preferencesViewModel.listaFilmes.observe(this) { listaSalva ->
            this.listaSalva = listaSalva
            preferencesViewModel.verificarFavorito(movie, this.listaSalva)
        }
    }

    private fun setupFavorito() {
        preferencesViewModel.favorito.observe(this) { foiSalvo ->
            var imagemInt = if(foiSalvo) R.drawable.favorito
                            else R.drawable.nao_favorito

            floating_save_details.setImageDrawable(ContextCompat.getDrawable(getBaseContext(), imagemInt))
        }
    }

    private fun setupCategories(){
        categoriesViewModel.getCategories(movie)
        categoriesViewModel.categories.observe(this) { genres ->
            txt_movie_genre_details.text = genres
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        var intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        return super.onSupportNavigateUp()
    }
}