package com.example.repaso.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.repaso.model.FavoritoModel
import com.example.repaso.model.MascotaJson
import com.example.repaso.model.RazaModel
import com.example.repaso.repository.Repository

class ProyectoViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = Repository(application)
    val razas: LiveData<List<RazaModel>> = repo.listarRazas()
    val favoritos: LiveData<List<FavoritoModel>> = repo.listarFavorito()
    var images = repo.images

    fun callApi()
    {
        repo.callRazas()
    }

    fun callImagenes(raza:String)
    {
        repo.callImagenes(raza)
    }
}