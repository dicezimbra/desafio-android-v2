package com.example.filmes.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.filmes.R
import com.example.filmes.data.FilmeRepository
import com.example.filmes.data.api.FilmeRetrofitTask
import com.example.filmes.presentation.view.adapter.ViewPageAdapter
import com.example.filmes.presentation.viewModel.FilmeViewModel
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var filmeViewModel:FilmeViewModel
    var repository = FilmeRepository(FilmeRetrofitTask())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    private fun initView() {
        filmeViewModel = ViewModelProvider(this, FilmeViewModel.ViewModelFactory(repository))
            .get(FilmeViewModel::class.java)
        setSupportActionBar(toolbarFilme)
        tabLayoutFilme.tabSelectedIndicator
        var pageAdapter = ViewPageAdapter(supportFragmentManager, lifecycle)
        viewPageFilme.adapter = pageAdapter

        TabLayoutMediator(tabLayoutFilme, viewPageFilme){tab, position ->
            when(position){
                0 -> {
                    tab.text = "Populares"
                }
                1 -> {
                    tab.text = "Favoritos"
                }
            }
        }.attach()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val search = menu!!.findItem(R.id.searchBar)
        val searchView:SearchView = search.actionView as SearchView
        searchView.queryHint = "Buscar filme"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filmeViewModel.getPesquisarFilmes(newText+"")
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }
}