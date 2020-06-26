package com.adityasundawa.lawancorona.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adityasundawa.lawancorona.Konsultasi
import com.adityasundawa.lawancorona.db.KonsultasiDatabase
import com.adityasundawa.lawancorona.repo.KonsultasiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class KonsultasiFragmentViewModel() : ViewModel() {
    lateinit var repository: KonsultasiRepository
    lateinit var allMyFriends: LiveData<List<Konsultasi>>
    fun init(context: Context) {
        val myFriendDao = KonsultasiDatabase.getDatabase(context).myFriendDao()
        repository = KonsultasiRepository(myFriendDao)
        allMyFriends = repository.allMyFriend
    }
    fun delete(myFriend: Konsultasi) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(myFriend)
    }
    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insertAll(myFriends: List<Konsultasi>) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
            repository.insertAll(myFriends)
        }
}
