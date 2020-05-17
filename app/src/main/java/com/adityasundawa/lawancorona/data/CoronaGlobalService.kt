package com.adityasundawa.lawancorona.data


import com.adityasundawa.lawancorona.DataGlobalItem
import retrofit2.Call
import retrofit2.http.GET

interface CoronaGlobalService{
    @GET(".")
    fun getData():Call<List<DataGlobalItem>>
}