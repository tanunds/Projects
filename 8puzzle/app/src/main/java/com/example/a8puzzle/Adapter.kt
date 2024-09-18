package com.example.a8puzzle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter (private val dao: ArrayList<PlayerItemDAO>?) : RecyclerView.Adapter<Adapter.ViewHolder>(){
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val player_name : TextView = itemView.findViewById(R.id.tv_player_name)
        val score : TextView = itemView.findViewById(R.id.tv_score)
        val size : TextView = itemView.findViewById(R.id.tv_size)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.player_name.text = dao?.get(position)?.strName
        holder.score.text = dao?.get(position)?.score.toString()
        holder.size.text = dao?.get(position)?.size

    }

    override fun getItemCount(): Int {
        if(dao == null)
            return 0
        if (dao == null)
            return 0
        return dao?.size!!
    }
//    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
//        val player_name : TextView = itemView.findViewById(R.id.tv_player_name)
//        val score : TextView = itemView.findViewById(R.id.tv_score)
//    }
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
//        return ViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.player_name.text = dao?.players?.get(position)?.strName
//        holder.score.text = dao?.players?.get(position)?.strScore
//    }
//
//    override fun getItemCount(): Int {
//        if(dao == null)
//            return 0
//        if (dao.players == null)
//            return 0
//        return dao.players?.size!!
//    }

}