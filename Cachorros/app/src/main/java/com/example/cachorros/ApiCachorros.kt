package com.example.cachorros

import retrofit2.Call
import retrofit2.http.*

interface ApiCachorros {

    @POST("cachorros")
    fun post(@Body novoCachorro: Cachorro): Call<Cachorro>

    @GET("cachorros")
    fun get(): Call<List<Cachorro>>

}