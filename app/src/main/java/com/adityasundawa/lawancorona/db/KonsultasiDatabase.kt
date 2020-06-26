package com.adityasundawa.lawancorona.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.adityasundawa.lawancorona.Konsultasi
import com.adityasundawa.lawancorona.dao.KonsultasiDao

@Database(entities = arrayOf(Konsultasi::class), version = 1,
    exportSchema = false)
public abstract class KonsultasiDatabase : RoomDatabase() {
    abstract fun myFriendDao(): KonsultasiDao
    companion object {
        // Singleton prevents multiple instances of database opening at the
// same time.
        @Volatile
        private var INSTANCE: KonsultasiDatabase? = null
        fun getDatabase(context: Context): KonsultasiDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    KonsultasiDatabase::class.java,
                    "my_friend_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}