package com.example.fifaplayerpotentials.ui.main

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fifaplayerpotentials.data.Player
import android.widget.ImageView
import android.widget.TextView
import android.widget.RatingBar
import com.example.fifaplayerpotentials.R
import android.view.LayoutInflater

import kotlinx.android.synthetic.main.player_grid_item.view.*
import org.w3c.dom.Text


class MainRecyclerAdapter(val context: Context, val players: List<Player>, val itemListener: PlayerItemListener):
    RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.player_grid_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = players.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val player = players[position]


        with(holder) {
            nameText?.let {
                it.text = player.playerName
                it.contentDescription = player.country
            }

            skillsRatingBar?.let {
                it.rating = player.skills.toFloat()
            }

            weakFootRatingBar?.let {
                it.rating = player.weakFoot.toFloat()
            }

            overall?.let {
                it.text = "" + player.overall
            }

            potential?.let {
                it.text = "" + player.potential
            }

            //ratingBar?.skillsRatingBar?.rating = player.skills.toFloat()

            holder.itemView.setOnClickListener() {
                itemListener.onPlayerItemClick(player)
            }
        }
    }

    inner class ViewHolder(itemView: View) :
                RecyclerView.ViewHolder(itemView) {
        val nameText = itemView.findViewById<TextView>(R.id.nameText)
        val playerImage = itemView.findViewById<ImageView>(R.id.playerImage)
        val skillsRatingBar = itemView.findViewById<RatingBar>(R.id.skillsRatingBar)
        val weakFootRatingBar = itemView.findViewById<RatingBar>(R.id.weakFootRatingBar)
        val overall = itemView.findViewById<TextView>(R.id.overallTextView)
        val potential = itemView.findViewById<TextView>(R.id.potentialTextView)


    }

    interface PlayerItemListener {
        fun onPlayerItemClick(player: Player)
    }
}