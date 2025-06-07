package com.example.codequest

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val BTNsair = findViewById<Button>(R.id.BTN_SAIR)
        val TVranking = findViewById<TextView>(R.id.RKnome)

        // Atualiza o ranking
        val ranking = carregarRanking()
        TVranking.text = ranking

        BTNsair.setOnClickListener {
            TLmenuprincipal()
        }
    }

    private fun TLmenuprincipal() {
        val telamenu = Intent(this, Menu::class.java)
        startActivity(telamenu)
    }

    private fun carregarRanking(): String {
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

        val top7 = rankingList.sortedByDescending { it.second }.take(7)

        if (top7.isEmpty()) return "Nenhum ranking disponível ainda."

        return top7.mapIndexed { index, (nome, pontos) ->
            "${index + 1}º - $nome: $pontos pontos"
        }.joinToString("\n")
    }
}