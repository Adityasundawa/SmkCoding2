package com.adityasundawa.lawancorona.repo

import androidx.lifecycle.LiveData
import com.adityasundawa.lawancorona.Konsultasi
import com.adityasundawa.lawancorona.dao.KonsultasiDao

class KonsultasiRepository(private val myFriendDao: KonsultasiDao) {
    // Room executes all queries on a separate thread.
// Observed LiveData will notify the observer when the data has changed.
    val allMyFriend: LiveData<List<Konsultasi>> =
        myFriendDao.getAllMyFriend()
    suspend fun insert(myFriend: Konsultasi) {
        myFriendDao.insert(myFriend)
    }
    suspend fun insertAll(myFriends: List<Konsultasi>) {
        myFriendDao.insertAll(myFriends)
    }
    suspend fun deleteAll() {
        myFriendDao.deleteAll()
    }
    suspend fun update(myFriend: Konsultasi) {
        myFriendDao.update(myFriend)
    }
    suspend fun delete(myFriend: Konsultasi) {
        myFriendDao.delete(myFriend)
    }
}
