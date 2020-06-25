package com.adityasundawa.lawancorona
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_friend")
data class Konsultasi(
    var nama: String,
    var email: String,
    var telp: String,
    var pesan: String,
    @PrimaryKey var key: String
){
    constructor() : this("","","","",""
    )
}