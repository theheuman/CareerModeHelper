package com.example.fifaplayerpotentials.data

import android.app.Application
import android.util.Log
import com.example.fifaplayerpotentials.utilities.FileHelper
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import androidx.lifecycle.MutableLiveData
import com.example.fifaplayerpotentials.LOG_TAG

class PlayerRepository(val app: Application) {

    val playerData = MutableLiveData<List<Player>>()

    private val listType = Types.newParameterizedType(List::class.java, Player::class.java)

    init {
        val data = readDataFromFile()
        if (data.isEmpty()) {
            getPlayerData()
        }
        else {
            playerData.value = data
        }
    }

    fun getPlayerData() {

        val text = FileHelper.getTextFromAssets(app, "players.json")

        val moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<List<Player>> = moshi.adapter(listType)

        val playerList = adapter.fromJson(text) ?: emptyList()
        saveDataToCache(playerList)
        playerData.value = playerList

    }

    private fun saveDataToCache(playerData: List<Player>) {
        val json = getMoshiAdapter().toJson((playerData))
        FileHelper.saveTextToFile(app, json)
    }

    private fun readDataFromFile(): List<Player> {
        val json = FileHelper.readTextFromFile(app)
        if (json == null) {
            return emptyList()
        }
        return getMoshiAdapter().fromJson(json) ?: emptyList()

    }

    private fun getMoshiAdapter(): JsonAdapter<List<Player>> {
        val moshi = Moshi.Builder().build()
        val listType = Types.newParameterizedType(List::class.java, Player::class.java)
        val adapter: JsonAdapter<List<Player>> = moshi.adapter(listType)
        return adapter
    }
}