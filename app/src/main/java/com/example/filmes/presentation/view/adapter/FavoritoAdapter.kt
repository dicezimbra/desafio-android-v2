package com.example.filmes.presentation.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.filmes.R
import com.example.filmes.data.local.entity.MovieEntity
import com.example.filmes.utilis.BASE_IMAGEM
import kotlinx.android.synthetic.main.favorite_item.view.*

class FavoritoAdapter(
    var listener: OnItemClickFavoritoListener,
    var movieList: List<MovieEntity>
) : RecyclerView.Adapter<FavoritoAdapter.viewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.favorite_item, parent, false)
        return viewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        var movie = movieList[position]
        with(holder.itemView){
            var imageUrl = BASE_IMAGEM + movie.posterFilme
            Glide.with(this)
                .load(imageUrl)
                .into(img_movie_favorite)

//            val formatDate = SimpleDateFormat("yy/MM/dd")
//            val realeseDate = formatDate.format(movie.dataLancamento)

            txt_movie_title_favorite.text = movie.tituloFilme
//            txt_release_date_favorite.text = "Lançamento $realeseDate"
            txt_movie_description_favorite.text = movie.sinopse

            bnt_favorite.setOnClickListener { listener.onClickButton(movie) }
        }
    }

    override fun getItemCount(): Int = movieList.size

    class viewHolder(itemView: View, listener: OnItemClickFavoritoListener) : RecyclerView.ViewHolder(itemView){
        init {
            itemView.setOnClickListener { listener.onClick(adapterPosition) }
        }
    }
}
interface OnItemClickFavoritoListener {
    fun onClick(position : Int)

    fun onClickButton(movie: MovieEntity)
}