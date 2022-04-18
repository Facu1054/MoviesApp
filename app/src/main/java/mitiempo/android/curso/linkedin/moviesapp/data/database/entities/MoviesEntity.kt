package mitiempo.android.curso.linkedin.moviesapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import mitiempo.android.curso.linkedin.moviesapp.domain.model.Movies
import mitiempo.android.curso.linkedin.moviesapp.domain.model.Tv

@Entity(tableName = "movies_table")
data class MoviesEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name  = "id") val id: Int = 0,
    @ColumnInfo(name  = "name") val name: String?,
    @ColumnInfo(name  = "image") val image: String?,
    @ColumnInfo(name  = "descripcion") val description: String?
    )

fun Movies.toDatabase() = MoviesEntity(name = name,image = image,description = description)


@Entity(tableName = "movies_top_table")
data class MoviesEntityTop (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name  = "id") val id: Int = 0,
    @ColumnInfo(name  = "name") val name: String?,
    @ColumnInfo(name  = "image") val image: String?,
    @ColumnInfo(name  = "descripcion") val description: String?
)

fun Movies.toDatabaseTop() = MoviesEntityTop(name = name,image = image,description = description)

@Entity(tableName = "movies_popular_table")
data class MoviesEntityPopular(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name  = "id") val id: Int = 0,
    @ColumnInfo(name  = "name") val name: String?,
    @ColumnInfo(name  = "image") val image: String?,
    @ColumnInfo(name  = "descripcion") val description: String?
)

fun Movies.toDatabasePopular() = MoviesEntityPopular(name = name, image = image, description = description)

/////////////////////////////////
@Entity(tableName = "tv_popular_table")
data class TvEntityPopular(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name  = "id") val id: Int = 0,
    @ColumnInfo(name  = "name") val name: String?,
    @ColumnInfo(name  = "image") val image: String?,
    @ColumnInfo(name  = "descripcion") val description: String?
)

fun Tv.toDomainPopular() = TvEntityPopular(name = name, image = image, description = description)
////////////

@Entity(tableName = "tv_top_table")
data class TvEntityTop(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name  = "id") val id: Int = 0,
    @ColumnInfo(name  = "name") val name: String?,
    @ColumnInfo(name  = "image") val image: String?,
    @ColumnInfo(name  = "descripcion") val description: String?
)

fun Tv.toDatabaseTop() = TvEntityTop(name = name, image = image, description = description)
