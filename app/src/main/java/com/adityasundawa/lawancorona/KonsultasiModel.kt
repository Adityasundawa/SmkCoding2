package com.adityasundawa.lawancorona

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "konsultasi")
data class KonsultasiModel(
    var nama: String,
    var email: String,
    var telp: String,
    var pesan: String,
    @PrimaryKey var key: String
){
    constructor() : this("","","","",""
    )
}