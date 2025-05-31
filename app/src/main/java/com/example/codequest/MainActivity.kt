package com.example.codequest

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val BTNsair = findViewById<Button>(R.id.BTN_SAIR)

        BTNsair.setOnClickListener {
            TLmenuprincipal()
        }
    }

    private fun TLmenuprincipal() {
        val telamenu = Intent(this, Menu::class.java)
        startActivity(telamenu)
    }
}