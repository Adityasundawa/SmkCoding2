package com.adityasundawa.lawancorona.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.adityasundawa.lawancorona.Konsultasi

@Dao
interface KonsultasiDao {
    @Query("SELECT * from my_friend")
    fun getAllMyFriend(): LiveData<List<Konsultasi>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(myFriend: Konsultasi)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(myFriends: List<Konsultasi>)
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(myFriend: Konsultasi)
    @Delete()
    suspend fun delete(myFriend: Konsultasi)
    @Query("DELETE FROM my_friend")
    suspend fun deleteAll()
}