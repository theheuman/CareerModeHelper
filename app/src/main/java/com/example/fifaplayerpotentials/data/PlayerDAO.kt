package com.example.fifaplayerpotentials.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PlayerDAO {

    @Query("SELECT * from players")
    fun getAll(): List<Player>

    @Insert
    suspend fun insertPlayer(player: Player)

    @Insert
    suspend fun insertPlayers(players: List<Player>)

    @Query("DELETE from players")
    suspend fun deleteAll()

    @Delete
    suspend fun deletePlayer(player: Player)
}