package com.example.repaso.cliente

import com.example.repaso.service.MascotaService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MascotaCliente {

    companion object{
        const val URL_BASRE  = "https://dog.ceo/api/"

        fun getCliente(url:String): MascotaService
        {
            val retrofit = Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build()
            return retrofit.create(MascotaService::class.java)
        }
    }
}