package com.example.m4s3retrofitkey.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TiempoAPIService {

    @GET("current?key=fea16a591b9e4c1c96a17ac50f142722&country=chile&lang=es")
    fun obtenerTiempo(@Query("city") city: String): Call<Tiempo>

}