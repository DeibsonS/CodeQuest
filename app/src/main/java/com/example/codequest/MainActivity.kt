package com.example.codequest

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        // colocar o texto email e senha nos campos da tela
        val BTlogin = findViewById<Button>(R.id.BTN_LOGIN)
        val BTrank = findViewById<Button>(R.id.BTN_REGISTRE)

        BTlogin.setOnClickListener {

            //verificar se usuario = juninho
            //verificar se usuario = teo

            TLmenuprincipal()
        }

        BTrank.setOnClickListener {
            TLranki()
        }
    }
    private fun TLmenuprincipal() {
        val telamenu = Intent(this, Menu::class.java)
        startActivity(telamenu)
    }

    private fun TLranki() {
        val telaranking = Intent(this, MenuQuests::class.java)
        startActivity(telaranking)
    }
}