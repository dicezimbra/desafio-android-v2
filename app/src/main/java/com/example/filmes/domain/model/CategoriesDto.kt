package com.example.filmes.domain.model

import com.google.gson.annotations.SerializedName

class CategoriesDto (

    @SerializedName("id")
    var id :Integer,

    @SerializedName("name")
    var nome :String

)