package utn.practica.heiner.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import utn.practica.heiner.data.PracticaDataBase
import utn.practica.heiner.model.Estado
import utn.practica.heiner.repository.EstadoRepository

class EstadoViewModel (application: Application) : AndroidViewModel(application)
{
    private val repository : EstadoRepository
    val getEstados : LiveData<List<Estado>>

    init {
        val estadoDao = PracticaDataBase.getDataBase(application).estadoDao()
        repository = EstadoRepository(estadoDao)
        getEstados = repository.getEstados
    }

    fun saveEstado(estado: Estado)
    {
        viewModelScope.launch { repository.saveEstado(estado) }
    }

    fun deleteEstado(estado: Estado)
    {
        viewModelScope.launch { repository.deleteEstado(estado) }
    }
}