package com.adityasundawa.lawancorona


import com.google.gson.annotations.SerializedName

data class DataIndonesiaItem(
    @SerializedName("meninggal")
    val meninggal: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("positif")
    val positif: String,
    @SerializedName("sembuh")
    val sembuh: String
)