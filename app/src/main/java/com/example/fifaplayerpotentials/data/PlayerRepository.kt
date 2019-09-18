package com.example.fifaplayerpotentials.data

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.annotation.WorkerThread
import com.example.fifaplayerpotentials.utilities.FileHelper
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import androidx.lifecycle.MutableLiveData
import com.example.fifaplayerpotentials.LOG_TAG
import kotlinx.coroutines.CoroutineScope
import androidx.core.content.ContextCompat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit

class PlayerRepository(val app: Application) {

    val playerData = MutableLiveData<List<Player>>()

    private val playerDao = PlayerDatabase.getDatabase(app).playerDao()

    init {
        CoroutineScope(Dispatchers.IO).launch {
            val data = playerDao.getAll()
            if (data.isEmpty()) {
                getPlayerData()
            }
            else {
                playerData.postValue(data)
                withContext(Dispatchers.Main) {
                    Toast.makeText(app, "Using database", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    @WorkerThread
    suspend fun getPlayerData() {

        val text = FileHelper.getTextFromAssets(app, "players.json")

        withContext(Dispatchers.Main) {
            Toast.makeText(app, "Using json file", Toast.LENGTH_LONG).show()
        }
        val playerList = getMoshiAdapter().fromJson(text) ?: emptyList()
        //saveDataToCache(playerList)
        playerData.postValue(playerList)
        playerDao.deleteAll()
        playerDao.insertPlayers(playerList)

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