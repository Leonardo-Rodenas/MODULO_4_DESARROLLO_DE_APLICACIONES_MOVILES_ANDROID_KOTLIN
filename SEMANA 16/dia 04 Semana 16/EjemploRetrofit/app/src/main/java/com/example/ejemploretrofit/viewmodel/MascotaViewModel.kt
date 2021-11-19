package com.example.ejemploretrofit.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ejemploretrofit.cliente.MascotaClient
import com.example.ejemploretrofit.model.Mascota
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MascotaViewModel : ViewModel() {

    //private val repo = MascotaClienteRepository()
    private val service = MascotaClient.getCliente(MascotaClient.URL_BASE)
    val mascota = MutableLiveData<Mascota>()

    fun buscarMascota(raza:String)
    {
        //val m = repo.getData(raza)
        //Log.d("MASCOTA_MODEL",m.toString())

        val call = service.getMascotas(raza)
        call.enqueue(object : Callback<Mascota> {

            override fun onResponse(call: Call<Mascota>, response: Response<Mascota>) {
                response.body().let {
                    Log.d("BODY", response.body().toString())
                    mascota.postValue(it)
                    Log.d("MASCOTA_CALL",mascota.toString())
                }
            }

            override fun onFailure(call: Call<Mascota>, t: Throwable) {
                call.cancel()
            }
        })
    }
}