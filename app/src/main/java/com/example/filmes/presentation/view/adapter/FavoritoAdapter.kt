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
import java.text.SimpleDateFormat

class FavoritoAdapter(
    var listener: OnItemClickFavoritoListener,
    var listFilmes: ArrayList<FilmeDto>
) : RecyclerView.Adapter<FavoritoAdapter.viewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.favorito_item, parent, false)
        return viewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        var filme = listFilmes[position]
        with(holder.itemView){
            var urlImagem = FilmeRetrofitTask.BASE_IMAGEM + filme.posterFilme
            Glide.with(this).load(urlImagem).into(imagemFavorito)

            val formatoData = SimpleDateFormat("dd/MM/yyyy")
            val dataLancamento = formatoData.format(filme.dataLancamento)

            textViewNomeFavorito.text = filme.tituloFilme
            textViewAnoLancamentoFavorito.text = dataLancamento
            textViewDescricaoFavorito.text = filme.sinopse

            buttonFavorito.setOnClickListener { listener.onClickButtonFavorito(filme) }
        }
    }

    override fun getItemCount(): Int = listFilmes.size

    class viewHolder(itemView: View, listener: OnItemClickFavoritoListener) : RecyclerView.ViewHolder(itemView){
        init {
            itemView.setOnClickListener { listener.onClick(adapterPosition) }
        }
    }

}
interface OnItemClickFavoritoListener {
    fun onClick(posicao : Int)

    fun onClickButtonFavorito(filme: FilmeDto)
}