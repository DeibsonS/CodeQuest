package com.example.codequest

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Menu : AppCompatActivity() {
    private var usuario: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu3)

        // Recupera o nome do usuário vindo da tela de login
        usuario = intent.getStringExtra("usuario")

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
        val intent = Intent(this, MenuQuests::class.java)
        intent.putExtra("usuario", usuario)
        startActivity(intent)
    }

    private fun TL_RANKING() {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("usuario", usuario)
        startActivity(intent)
    }

    private fun TL_VOLTA() {
        val intent = Intent(this, TelaRanking::class.java)
        startActivity(intent)
    }
}