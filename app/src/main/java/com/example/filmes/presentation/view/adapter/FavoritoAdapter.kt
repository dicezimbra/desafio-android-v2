package com.example.filmes.presentation.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.filmes.R
import com.example.filmes.data.api.FilmeRetrofitTask
import com.example.filmes.data.model.FilmeDto
import kotlinx.android.synthetic.main.favorito_item.view.*
import kotlinx.android.synthetic.main.popular_item.view.*

class FavoritoAdapter(
    var listener: OnItemClickFilmeListener,
    var listFilmes: ArrayList<FilmeDto>
) : RecyclerView.Adapter<FavoritoAdapter.viewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.favorito_item, parent, false)
        return viewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        var filme = listFilmes[position]
        with(holder.itemView){
            var urlImagem = FilmeRetrofitTask.BASE_IMAGEM + filme.backdropPath
            Glide.with(this).load(urlImagem).into(imagemFavorito)
            textViewNomeFavorito.text = filme.tituloFilme
            textViewAnoLancamentoFavorito.text = filme.dataLancamento
            textViewDescricaoFavorito.text = filme.sinopse
        }
    }

    override fun getItemCount(): Int = listFilmes.size

    class viewHolder(itemView: View, listener: OnItemClickFilmeListener) : RecyclerView.ViewHolder(itemView){
        init {
            itemView.setOnClickListener { listener.onClick(adapterPosition) }
        }
    }

}