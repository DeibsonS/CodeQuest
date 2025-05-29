package com.example.codequest

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu3)

        val BTN_QUESTOES = findViewById<Button>(R.id.BTN_QUESTOES)
        val BTN_RANKING = findViewById<Button>(R.id.BTN_RANKING)
        val BTN_VOLTAR = findViewById<Button>(R.id.BTN_VOLTAR)

        BTN_QUESTOES.setOnClickListener {
            TL_QUESTOES()
        }

        BTN_RANKING.setOnClickListener {
            TL_RANKING()
        }

        BTN_VOLTAR.setOnClickListener {
            TL_VOLTA()
        }

    }
    private fun TL_QUESTOES() {
        val telamenu = Intent(this, MenuQuests::class.java)
        startActivity(telamenu)
    }

    private fun TL_RANKING() {
        //ALTERAR A TELA PARA TELA DE RANKING
        val telaranking = Intent(this, MenuQuests::class.java)
        startActivity(telaranking)
    }

    private fun TL_VOLTA() {
        val telalogin = Intent(this, MainActivity::class.java)
        startActivity(telalogin)
    }

    }