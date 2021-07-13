package com.example.filmes.presentation.view

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.filmes.R
import com.example.filmes.data.api.RetrofitTask
import com.example.filmes.domain.model.MovieDto
import com.example.filmes.presentation.viewmodel.SharedPreferencesViewModel
import com.example.filmes.domain.SharedPreferecesConfig
import com.example.filmes.presentation.viewmodel.CategoriesViewModel
import kotlinx.android.synthetic.main.activity_detalhes.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat


class DetalhesActivity : AppCompatActivity() {

    private val categoriesViewModel: CategoriesViewModel by viewModel()
    var listaSalva = ArrayList<MovieDto>()
    lateinit var movie: MovieDto
    lateinit var preferencesViewModel: SharedPreferencesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes)

        var preferencesConfig = SharedPreferecesConfig(sharedInstance())
        preferencesViewModel = ViewModelProvider(
            this,
            SharedPreferencesViewModel.ViewModelFactory(preferencesConfig)
        ).get(SharedPreferencesViewModel::class.java)

        initView()
    }

    private fun initView() {
        setupActionBar()
        setupMovie()
        setupViewModel()
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
        Glide.with(this).load(RetrofitTask.BASE_IMAGEM + movie.backdropPath).into(img_movie_details)
        val dateFormat = SimpleDateFormat("dd/MM/yyyy")
        val realeseDate = dateFormat.format(movie.dataLancamento)
        txt_movie_date_details.text = "Lançamento: $realeseDate"
        txt_movie_note_details.text = "${movie.notaMedia}/10 \nAvaliação"
        txt_movie_title_details.text = movie.tituloFilme
        txt_movie_description_details.text = movie.sinopse
    }

    private fun setupViewModel() {
        preferencesViewModel.getListaSalva()
        preferencesViewModel.listaFilmes.observe(this) { listaSalva ->
            this.listaSalva = listaSalva
            preferencesViewModel.verificarFavorito(movie, this.listaSalva)
        }
        preferencesViewModel.favorito.observe(this) { foiSalvo ->
            var imagemInt = if(foiSalvo){
                R.drawable.favorito
            }else{
                R.drawable.nao_favorito
            }
            floating_save_details.setImageDrawable(ContextCompat.getDrawable(getBaseContext(), imagemInt))
        }
    }

    private fun setupCategories(){
        categoriesViewModel.getCategories()
        categoriesViewModel.categories.observe(this) { resultsCategories ->
            var genresList = resultsCategories.generosFilme
            var nomeCategorias = ""
            //verifica os gêneros e coloca no TextView
            genresList.forEach { idGenero ->
                movie.generosIds.forEach { idDoFilme ->
                    if(idGenero.id.equals(idDoFilme)){
                        nomeCategorias += idGenero.nome+", "
                    }
                }
            }
            txt_movie_genre_details.text = nomeCategorias
        }
    }

    private fun sharedInstance() : SharedPreferences {
        return getSharedPreferences("com.example.filmes", MODE_PRIVATE)
    }

    override fun onSupportNavigateUp(): Boolean {
        var intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        return super.onSupportNavigateUp()
    }
}