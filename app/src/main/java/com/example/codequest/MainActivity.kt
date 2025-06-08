package com.example.codequest

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSair = findViewById<Button>(R.id.BTN_SAIR)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_ranking)
        val usuario = intent.getStringExtra("usuario") ?: "Visitante"

        val rankingList = carregarRanking()
        if (rankingList.isEmpty()) {
            Toast.makeText(this, "Nenhum ranking disponível ainda.", Toast.LENGTH_SHORT).show()
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = RankingAdapter(rankingList)

        btnSair.setOnClickListener {
            val intent = Intent(this, Menu::class.java)
            intent.putExtra("usuario", usuario)  // passa o usuário junto
            startActivity(intent)
            finish()
        }
    }

    private fun carregarRanking(): List<Pair<String, Int>> {
        val rankingList = mutableListOf<Pair<String, Int>>()

        filesDir.listFiles()?.forEach { file ->
            if (file.name.startsWith("pontuacao_")) {
                val nomeUsuario = file.name.removePrefix("pontuacao_").removeSuffix(".txt")
                val pontos = try {
                    file.readText().toInt()
                } catch (e: Exception) {
                    0
                }
                rankingList.add(Pair(nomeUsuario, pontos))
            }
        }

        return rankingList.sortedByDescending { it.second }.take(7)
    }
}