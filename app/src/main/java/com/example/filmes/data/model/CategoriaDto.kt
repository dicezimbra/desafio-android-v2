package com.example.filmes.data.model

import com.google.gson.annotations.SerializedName

class CategoriaDto (

    @SerializedName("id")
    var id :Integer,

    @SerializedName("name")
    var nome :String

)