package com.example.filmes.data.model

import com.google.gson.annotations.SerializedName

class ResultadoFilmeDto(

    @SerializedName("page")
    var page : Integer,

    @SerializedName("results")
    var listFilmeDtos : ArrayList<FilmeDto>,

    @SerializedName("total_pages")
    var totalPages : Integer,

    @SerializedName("total_results")
    var totalResults : Integer
)