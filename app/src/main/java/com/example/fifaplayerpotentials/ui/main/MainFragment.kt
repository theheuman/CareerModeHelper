package com.example.fifaplayerpotentials.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.example.fifaplayerpotentials.R
import com.example.fifaplayerpotentials.LOG_TAG
import com.example.fifaplayerpotentials.data.Player
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.fifaplayerpotentials.ui.shared.SharedViewModel


class MainFragment : Fragment(), MainRecyclerAdapter.PlayerItemListener {

    private lateinit var viewModel: SharedViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        (requireActivity() as AppCompatActivity).run {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }

        val view = inflater.inflate(R.layout.main_fragment, container, false)
        recyclerView = view.findViewById(R.id.playerList)
        navController = Navigation.findNavController(
            requireActivity(), R.id.nav_host
        )


        viewModel = ViewModelProviders.of(requireActivity()).get(SharedViewModel::class.java)
        viewModel.playerData.observe(this, Observer
        {
            val adapter = MainRecyclerAdapter(requireContext(), it, this)
            recyclerView.adapter = adapter
        })

        return view
    }

    override fun onPlayerItemClick(player: Player) {
        viewModel.selectedPlayer.value = player
        navController.navigate(R.id.action_nav_detail)
        Log.i(LOG_TAG, "Selected Player: ${player.playerName}")
    }

}