package com.example.filmes.domain.model

import com.google.gson.annotations.SerializedName

class CategoriesDto (

    @SerializedName("id")
    var id :Int,

    @SerializedName("name")
    var nome :String

)