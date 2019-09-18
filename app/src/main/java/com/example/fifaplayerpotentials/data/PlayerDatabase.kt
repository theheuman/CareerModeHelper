package com.example.fifaplayerpotentials.data

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Room

@Database(entities = [Player::class], version = 2, exportSchema = false)
abstract class PlayerDatabase: RoomDatabase() {

    abstract fun playerDao(): PlayerDAO

    companion object {
        @Volatile
        private var INSTANCE: PlayerDatabase? = null

        fun getDatabase(context: Context): PlayerDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext, PlayerDatabase::class.java, "players.db").fallbackToDestructiveMigration().build()
                }

            }
            //crash the app if it is still null
            return INSTANCE!!
        }
    }
}