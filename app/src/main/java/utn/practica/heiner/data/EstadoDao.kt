package utn.practica.heiner.data

import androidx.lifecycle.LiveData
import androidx.room.*
import utn.practica.heiner.model.Estado

@Dao
interface EstadoDao
{
    //CRUD
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addEstado(estado: Estado)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateEstado(estado: Estado)

    @Delete
    suspend fun deleteEstado(estado: Estado)

    @Query("SELECT * FROM ESTADO")
    fun getEstados() : LiveData<List<Estado>>
}