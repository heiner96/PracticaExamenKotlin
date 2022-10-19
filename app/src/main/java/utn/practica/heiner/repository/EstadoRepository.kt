package utn.practica.heiner.repository

import androidx.lifecycle.LiveData
import utn.practica.heiner.data.EstadoDao
import utn.practica.heiner.model.Estado

class EstadoRepository (private val estadoDao: EstadoDao)
{

    suspend fun saveEstado(estado: Estado)
    {
        if(estado.id==0){//estado nuevo
            estadoDao.addEstado(estado)
        }
        else{//estado existe, se modifica
            estadoDao.updateEstado(estado)
        }

    }

    suspend fun deleteEstado(estado: Estado)
    {
        estadoDao.deleteEstado(estado)
    }


    val getEstados : LiveData<List<Estado>> =  estadoDao.getEstados()

}