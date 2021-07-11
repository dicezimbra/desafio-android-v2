package com.example.filmes.presentation.view

import android.R.attr.data
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.SearchView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.filmes.R
import com.example.filmes.data.api.FilmeRetrofitTask
import com.example.filmes.data.repository.FilmeRepository
import com.example.filmes.presentation.view.adapter.ViewPageAdapter
import com.example.filmes.presentation.viewModel.FilmeViewModel
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    lateinit var filmeViewModel:FilmeViewModel
    var retrofitTask = FilmeRetrofitTask()
    private val TAG = "MainActivity"

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        filmeViewModel = ViewModelProvider(
            this,
            FilmeViewModel.ViewModelFactory(FilmeRepository(retrofitTask))
        )
            .get(FilmeViewModel::class.java)

        initView()
    }

    private fun initView() {
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