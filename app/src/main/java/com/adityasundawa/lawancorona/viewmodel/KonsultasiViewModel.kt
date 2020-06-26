package com.adityasundawa.lawancorona.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adityasundawa.lawancorona.Konsultasi
import com.adityasundawa.lawancorona.db.KonsultasiDatabase
import com.adityasundawa.lawancorona.repo.KonsultasiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class KonsultasiViewModel() : ViewModel() {
    lateinit var repository: KonsultasiRepository
    fun init(context: Context) {
        val myFriendDao = KonsultasiDatabase.getDatabase(context).myFriendDao()
        repository = KonsultasiRepository(myFriendDao)
    }
    fun addData(myFriend: Konsultasi) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(myFriend)
        }
}
