package com.example.filmes.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.example.filmes.domain.model.MovieDto
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey(autoGenerate = false) val id: Long,
    @ColumnInfo val generosIds:String,
    @ColumnInfo val posterFilme: String,
    @ColumnInfo val tituloFilme: String,
    @ColumnInfo val sinopse: String,
    @ColumnInfo val notaMedia: Double,
    @ColumnInfo val dataLancamento: String,
    @ColumnInfo var adult: Boolean,
    @ColumnInfo val backdropPath: String,
    @ColumnInfo val originalLanguage: String,
    @ColumnInfo val originalTitle: String,
    @ColumnInfo val popularity: Double,
    @ColumnInfo val video: Boolean,
    @ColumnInfo val voteCount: Int
)

class MovieTypeConverter{

    @TypeConverter
    fun fromIntArray(json : String?) : IntArray {
        val turnsType = object : TypeToken<IntArray>() {}.type
        return Gson().fromJson(json, turnsType)
    }

    @TypeConverter
    fun fromJson(listaGeneros : IntArray) : String = Gson().toJson(listaGeneros)
}
