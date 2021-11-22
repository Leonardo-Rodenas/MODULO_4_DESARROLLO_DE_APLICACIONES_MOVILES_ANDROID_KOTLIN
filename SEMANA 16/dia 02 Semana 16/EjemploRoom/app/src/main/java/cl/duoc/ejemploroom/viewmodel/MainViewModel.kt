package cl.duoc.ejemploroom.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.duoc.ejemploroom.MensajeApp
import cl.duoc.ejemploroom.model.Mensaje
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    var mensajes:LiveData<List<Mensaje>>? = null

    init {
        mensajes = MensajeApp.repo.listar()
    }

    fun onAgregarClicked(mensaje:Mensaje){
        MensajeApp.repo.agregar(mensaje)
    }

    fun onItemClicked(mensaje: Mensaje)
    {
        MensajeApp.repo.eliminar(mensaje)
    }
}