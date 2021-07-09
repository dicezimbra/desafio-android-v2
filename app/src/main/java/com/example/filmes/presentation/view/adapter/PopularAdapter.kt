package com.example.filmes.presentation.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.filmes.R
import com.example.filmes.data.api.FilmeRetrofitTask
import com.example.filmes.data.model.FilmeDto
import kotlinx.android.synthetic.main.filme_item.view.*
import kotlin.collections.ArrayList

class PopularAdapter(
    var listener: OnItemClickFilmesListener,
    var listFilmes: ArrayList<FilmeDto>
) : RecyclerView.Adapter<PopularAdapter.viewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.filme_item, parent, false)
        return viewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        var filme = listFilmes[position]
        with(holder.itemView){
            var urlImagem = FilmeRetrofitTask.BASE_IMAGEM + filme!!.backdropPath
            Glide.with(this).load(urlImagem).into(imagemFilme)
            textViewNomeFilme.text = filme!!.tituloFilme
            textViewAnoLancamento.text = "Data de Lançamento: ${filme.dataLancamento}"
        }
    }

    override fun getItemCount(): Int = listFilmes.size

    class viewHolder(
        itemView: View, listener: OnItemClickFilmesListener
    ) : RecyclerView.ViewHolder(itemView){
        init {
            itemView.setOnClickListener { listener.onClick(adapterPosition) }
        }
    }

}
interface OnItemClickFilmesListener {

    fun onClick(posicao : Int)
}