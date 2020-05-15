package com.adityasundawa.lawancorona.data


import com.adityasundawa.lawancorona.DataProvinsiItem
import retrofit2.Call
import retrofit2.http.GET

interface CoronaProvinsiService{
    @GET("indonesia/provinsi")
    fun getData():Call<List<DataProvinsiItem>>
}