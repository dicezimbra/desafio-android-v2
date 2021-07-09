package com.example.filmes.data.model

import com.google.gson.annotations.SerializedName

class ResultadoCategoriaDto (

    @SerializedName("genres")
    var generosFilme : ArrayList<CategoriaDto>
)