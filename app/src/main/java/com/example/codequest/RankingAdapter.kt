package com.example.codequest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RankingAdapter(private val rankingList: List<Pair<String, Int>>) :
    RecyclerView.Adapter<RankingAdapter.RankingViewHolder>() {

    class RankingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtPosicao: TextView = itemView.findViewById(R.id.txt_posicao)
        val txtNome: TextView = itemView.findViewById(R.id.txt_nome)
        val txtPontos: TextView = itemView.findViewById(R.id.txt_pontos)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ranking, parent, false)
        return RankingViewHolder(view)
    }

    override fun onBindViewHolder(holder: RankingViewHolder, position: Int) {
        val (nome, pontos) = rankingList[position]

        val posicaoStr = when (position) {
            0 -> "🥇"
            1 -> "🥈"
            2 -> "🥉"
            else -> "${position + 1}º"
        }

        holder.txtPosicao.text = posicaoStr
        holder.txtNome.text = nome
        holder.txtPontos.text = "$pontos pts"
    }

    override fun getItemCount(): Int = rankingList.size
}