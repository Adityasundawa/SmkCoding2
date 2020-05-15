package com.adityasundawa.lawancorona.data

import com.adityasundawa.lawancorona.DataIndonesiaItem
import retrofit2.Call
import retrofit2.http.GET

interface CoronaService{
    @GET("indonesia")
    fun getData():Call<List<DataIndonesiaItem>>
}