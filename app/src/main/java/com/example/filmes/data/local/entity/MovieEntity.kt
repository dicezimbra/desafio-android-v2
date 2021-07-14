package com.example.filmes.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val posterFilme: String,
    val tituloFilme: String,
    val sinopse: String,
    val notaMedia: Double,
    val dataLancamento: String,
    val generosIds:Int = 1,
    var adult: Boolean,
    val backdropPath: String,
    val originalLanguage: String,
    val originalTitle: String,
    val popularity: Double,
    val video: Boolean,
    val voteCount: Int
)