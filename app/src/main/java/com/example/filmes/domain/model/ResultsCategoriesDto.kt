package com.example.filmes.domain.model

import com.google.gson.annotations.SerializedName

class ResultsCategoriesDto (

    @SerializedName("genres")
    var generosFilme : ArrayList<CategoriesDto>
)