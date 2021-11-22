package com.example.repaso.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.repaso.cliente.MascotaCliente
import com.example.repaso.model.FavoritoModel
import com.example.repaso.model.MascotaJson
import com.example.repaso.model.RazaJson
import com.example.repaso.model.RazaModel
import com.example.repaso.room.ProyectoDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository(var context: Context) {

    private val db = ProyectoDatabase.getDatabase(context)
    private val service = MascotaCliente.getCliente(MascotaCliente.URL_BASRE)
    var images = MutableLiveData<MascotaJson>()

    fun callRazas()
    {
        val call = service.getRazas()
        call.enqueue(object : Callback<RazaJson>{
            override fun onResponse(call: Call<RazaJson>, response: Response<RazaJson>) {
                response.body().let {
                    var razaJson = it
                    Log.d("RAZAS",it?.message.toString())
                    CoroutineScope(IO).launch {
                        db.razaDao().agregarAll(convertidorRazas(razaJson!!))
                    }
                }
            }

            override fun onFailure(call: Call<RazaJson>, t: Throwable) {
                call.cancel()
            }
        })
    }

    fun callImagenes(raza:String)
    {
        val call = service.getMascotas(raza)
        call.enqueue(object : Callback<MascotaJson>{
            override fun onResponse(call: Call<MascotaJson>, response: Response<MascotaJson>) {
                response.body().let {
                    var mascotaJson = it
                    images.postValue(it)
                }
            }

            override fun onFailure(call: Call<MascotaJson>, t: Throwable) {
                call.cancel()
            }

        })
    }


    fun listarRazas() : LiveData<List<RazaModel>>
    {
        return db.razaDao().listar()
    }

    fun agregarFavorito(favoritoModel: FavoritoModel)
    {
        CoroutineScope(IO).launch {
            db.favoritoDao().agregar(favoritoModel)
        }
    }

    fun eliminarFavorito(favoritoModel: FavoritoModel)
    {
        CoroutineScope(IO).launch {
            db.favoritoDao().eliminar(favoritoModel)
        }
    }

    fun listarFavorito() : LiveData<List<FavoritoModel>>
    {
        return db.favoritoDao().listar()
    }

    private fun convertidorRazas(razaJson: RazaJson) : List<RazaModel>
    {
        val lista = ArrayList<RazaModel>()
        for(i in razaJson.message.indices)
        {
            var razaModel = RazaModel(i,razaJson.message[i])
            lista.add(razaModel)
        }
        return  lista
    }

}