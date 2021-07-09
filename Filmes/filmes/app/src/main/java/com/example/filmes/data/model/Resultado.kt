package com.example.filmes.data.model

import com.google.gson.annotations.SerializedName

class Resultado(
    @SerializedName("page")
    var page : Integer,

    @SerializedName("results")
    var listFilmes : ArrayList<Filme>,

    @SerializedName("total_pages")
    var totalPages : Integer,

    @SerializedName("total_results")
    var totalResults : Integer
)