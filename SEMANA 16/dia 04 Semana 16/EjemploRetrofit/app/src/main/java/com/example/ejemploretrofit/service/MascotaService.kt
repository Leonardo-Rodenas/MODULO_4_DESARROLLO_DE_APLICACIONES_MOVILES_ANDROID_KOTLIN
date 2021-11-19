package com.example.ejemploretrofit.service

import com.example.ejemploretrofit.model.Mascota
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MascotaService {

    @GET("{raza}/images")
    fun getMascotas(@Path("raza") raza:String) : Call<Mascota>



}