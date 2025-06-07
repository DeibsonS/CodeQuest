package com.example.codequest

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class TelaRanking : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tela_ranking)

        val BTlogin = findViewById<Button>(R.id.BTN_LOGIN)
        val BTregistre = findViewById<Button>(R.id.BTN_REGISTRE)
        val usuario = findViewById<EditText>(R.id.ED_USUARIO)
        val senha = findViewById<EditText>(R.id.ED_SENHA)

        // Lista de logins válidos
        val loginsValidos = listOf(
            Pair("Bruno", "12345"),
            Pair("Deibson", "12345"),
            Pair("Caetano", "12345"),
            Pair("Gabriel", "12345"),
            Pair("1", "1")
        )

        BTlogin.setOnClickListener {
            val usuarioDigitado = usuario.text.toString()
            val senhaDigitada = senha.text.toString()

            val loginValido = loginsValidos.any { it.first == usuarioDigitado && it.second == senhaDigitada }
            if (loginValido) {
                TLmenuprincipal(usuarioDigitado)
            } else {
                Toast.makeText(this, "Nome ou senha inválidos", Toast.LENGTH_SHORT).show()
            }
        }

        BTregistre.setOnClickListener {
            Toast.makeText(this, "Não é possivel fazer o registro de novos usuarios", Toast.LENGTH_SHORT).show()
        }
    }
    private fun TLmenuprincipal(usuario: String) {
        val intent = Intent(this, Menu::class.java)
        intent.putExtra("usuario", usuario)
        startActivity(intent)
    }

    private fun TLranki() {
        val telaranking = Intent(this, MenuQuests::class.java)
        startActivity(telaranking)
    }
}