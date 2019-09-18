package com.example.fifaplayerpotentials.ui.shared

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.fifaplayerpotentials.data.PlayerRepository
import com.example.fifaplayerpotentials.data.Player


class SharedViewModel(app: Application) : AndroidViewModel(app) {

    private val dataRepo = PlayerRepository(app)
    val playerData = dataRepo.playerData

    val selectedPlayer = MutableLiveData<Player>()


}
