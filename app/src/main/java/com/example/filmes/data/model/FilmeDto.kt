package com.example.filmes.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.lang.reflect.Array

@Parcelize
class FilmeDto(

    @SerializedName("poster_path")
    val posterFilme: String,

    @SerializedName("title")
    val tituloFilme: String,

    @SerializedName("overview")
    val sinopse: String,

    @SerializedName("vote_average")
    val notaMedia: Double,

    @SerializedName("release_date")
    val dataLancamento: String,

    @SerializedName("genre_ids")
    val generosIds: IntArray,

    @SerializedName("adult")
    var adult: Boolean,
    
    @SerializedName("backdrop_path")
    val backdropPath: String,

    @SerializedName("id")
    val id: Int,

    @SerializedName("original_language")
    val originalLanguage: String,

    @SerializedName("original_title")
    val originalTitle: String,

    @SerializedName("popularity")
    val popularity: Double,

    @SerializedName("video")
    val video: Boolean,

    @SerializedName("vote_count")
    val voteCount: Int
):Parcelable