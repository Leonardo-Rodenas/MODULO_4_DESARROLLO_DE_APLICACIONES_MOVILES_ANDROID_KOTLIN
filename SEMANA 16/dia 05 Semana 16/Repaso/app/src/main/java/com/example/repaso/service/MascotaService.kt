package com.example.repaso.service

import com.example.repaso.model.MascotaJson
import com.example.repaso.model.RazaJson
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MascotaService {

    @GET("breed/{raza}/images")
    fun getMascotas(@Path("raza")raza:String): Call<MascotaJson>

    @GET("breeds/list")
    fun getRazas(): Call<RazaJson>
}