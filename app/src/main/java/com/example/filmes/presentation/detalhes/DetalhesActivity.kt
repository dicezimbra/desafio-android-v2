package com.example.filmes.presentation.detalhes

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.filmes.R
import com.example.filmes.data.api.RetrofitTask
import com.example.filmes.domain.model.MovieDto
import com.example.filmes.presentation.main.MainActivity
import com.example.filmes.presentation.SharedPreferencesViewModel
import com.example.filmes.domain.SharedPreferecesConfig
import kotlinx.android.synthetic.main.activity_detalhes.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat


class DetalhesActivity : AppCompatActivity() {

    private val categoriesViewModel:CategoriesViewModel by viewModel()
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
        setupCategories()
    }

    private fun setupCategories(){
        categoriesViewModel.getCategories()
        categoriesViewModel.categories.observe(this, { resultsC ->
            var genresList = resultsC.generosFilme
            var nomeCategorias = ""
            if(!genresList.isNullOrEmpty()){
                genresList.forEach { idGenero ->
                    movie.generosIds.forEach { idDoFilme ->
                        if(idGenero.id.equals(idDoFilme)){
                            nomeCategorias += idGenero.nome+", "
                        }
                    }
                }
            }
            txt_movie_genre_details.text = nomeCategorias
        })
    }

    private fun initView() {
        setSupportActionBar(toolbar_details)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setupFilme()
        setupViewModel()
        floating_save_details.setOnClickListener {
            preferencesViewModel.inserirListFavorito(movie, listaSalva)
            preferencesViewModel.verificarFavorito(movie, listaSalva)
        }
    }

    private fun setupFilme() {
        movie = intent.getParcelableExtra(R.string.KEY_MOVIE.toString())!!
        Glide.with(this).load(RetrofitTask.BASE_IMAGEM + movie.backdropPath).into(img_movie_details)
        txt_movie_title_details.text = movie.tituloFilme
        txt_movie_description_details.text = movie.sinopse
        val formatoData = SimpleDateFormat("dd/MM/yyyy")
        val dataLancamento = formatoData.format(movie.dataLancamento)
        txt_movie_date_details.text = "Lançamento: $dataLancamento"
        txt_movie_note_details.text = "${movie.notaMedia}/10 \nAvaliação"
    }

    private fun setupViewModel() {
        preferencesViewModel.getListaSalva()
        preferencesViewModel.listaFilmes.observe(this, { listSalvos ->
            listaSalva = listSalvos
            preferencesViewModel.verificarFavorito(movie, listaSalva)
        })

        preferencesViewModel.favorito.observe(this, Observer { foiSalvo ->
            var imagemInt = if(foiSalvo){
                R.drawable.favorito
            }else{
                R.drawable.nao_favorito
            }
            floating_save_details.setImageDrawable(ContextCompat.getDrawable(getBaseContext(), imagemInt));
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