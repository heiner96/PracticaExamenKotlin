package utn.practica.heiner.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "estado")
data class Estado
(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name ="nombre")
    val nombre: String,
    @ColumnInfo(name ="capital")
    val capital: String?,
    @ColumnInfo(name ="nPoblacion")
    val nPoblacion: Int?,
    @ColumnInfo(name ="continente")
    val continente: String?,
    @ColumnInfo(name ="nDepartamentos")
    val nDepartamentos: Int?,
): Parcelable