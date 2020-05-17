package com.adityasundawa.lawancorona.data

import com.adityasundawa.lawancorona.MainModel
import retrofit2.Call
import retrofit2.http.*

interface ApiEndPoint {

    @GET("all")
    fun getData(): Call<List<MainModel>>
}