package com.example.codequest

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MenuQuests : AppCompatActivity() {

    fun salvarPontuacao(usuario: String, pontos: Int) {
        val nomeArquivo = "pontuacao.txt"
        openFileOutput(nomeArquivo, MODE_PRIVATE).use {
            it.write(pontos.toString().toByteArray())
        }
    }

    fun lerPontuacao(usuario: String): Int {
        val nomeArquivo = "pontuacao.txt"
        return try {
            val texto = openFileInput(nomeArquivo).bufferedReader().useLines { it.firstOrNull() }
            texto?.toInt() ?: 0
        } catch (e: Exception) {
            0
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu_quests)

        val BTNvoltar = findViewById<Button>(R.id.BTNvoltar)
        val BTNquestA = findViewById<Button>(R.id.BTNquestA)
        val BTNquestB = findViewById<Button>(R.id.BTNquestB)
        val BTNquestC = findViewById<Button>(R.id.BTNquestC)
        val BTNquestD = findViewById<Button>(R.id.BTNquestD)
        val BTNproxi = findViewById<Button>(R.id.BTNproxi)
        val TXTperg = findViewById<TextView>(R.id.TXT_QUEST)

        //para receber o nome do usuario de acordo com o login
        //val usuario = intent.getStringExtra("usuario") ?: "desconhecido"
        val usuario = "Jose"

        var pontuacao: Int  // para guardar a pontuacao
        pontuacao = lerPontuacao(usuario) // carrega pontuação salva

        data class Questao(
            val pergunta: String,
            val correta: Int, // índice da alternativa correta
            val alternativa: List<String>
        )

        val resp = listOf(
            Questao(
                "Como se declara um inteiro em COBOL?",
                2,
                listOf(
                    "A - INT numero = 10",
                    "B - <p>O número inteiro é: 10<p>",
                    "C - 01 numero PIC 9(05)",
                    "D - numero = 100"
                )
            ),
            Questao(
                "Qual é o comportamento de std::move em C++ quando aplicado a uma variável const?",
                0,
                listOf(
                    "A - Ele compila, mas se faz uma cópia em vez de mover",
                    "B - Ele sempre move o objeto",
                    "C - Gera um erro de compilação",
                    "D - Libera a memória do objeto original"
                )
            )
        )

        var questaoIndex = 0
        var respostaSelecionada: Int? = null
        val botoes = listOf(BTNquestA, BTNquestB, BTNquestC, BTNquestD)

        fun atualizarBotoes() {
            val questao = resp[questaoIndex]
            TXTperg.text = questao.pergunta

            //TXTperg.setTextSize(22f) // Tamanho em SP (scale-independent pixels)

            BTNquestA.text = questao.alternativa[0]
            BTNquestB.text = questao.alternativa[1]
            BTNquestC.text = questao.alternativa[2]
            BTNquestD.text = questao.alternativa[3]
        }

        fun atualizarEstadoBotoes(indiceSelecionado: Int?) {
            for (i in botoes.indices) {
                botoes[i].isEnabled = (indiceSelecionado == null || i == indiceSelecionado)
            }
        }

        //aumenta o tamanho do textview de acordo com a pergunta
        //val tamanho = if (Questao.pergunta.length > 80) 18f else 22f
        //TXTperg.setTextSize(tamanho)

        fun verificarResposta(indiceSelecionado: Int) {
            val questaoAtual = resp[questaoIndex]
            if (indiceSelecionado == questaoAtual.correta) {
                Toast.makeText(this, "Resposta Correta!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Resposta Errada!", Toast.LENGTH_SHORT).show()
            }
        }

        fun mostrarResultado(correta: Boolean) {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Resultado")

            if (correta) {
                pontuacao++ // aumenta a pontuacao
                salvarPontuacao(usuario, pontuacao)
                builder.setMessage("Resposta Correta!")
            } else {
                builder.setMessage("Resposta Errada!")
            }

            builder.setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss() // Fecha o pop-up
            }

            val dialog = builder.create()
            dialog.show()
        }

        atualizarBotoes()

        BTNvoltar.setOnClickListener {
            val TelaMenu = Intent(this, Menu::class.java)
            startActivity(TelaMenu)
        }

        BTNquestA.setOnClickListener {
            if (respostaSelecionada == 0) {
                respostaSelecionada = null
                atualizarEstadoBotoes(null)
            } else {
                respostaSelecionada = 0
                atualizarEstadoBotoes(0)
            }
        }
        BTNquestB.setOnClickListener {
            if (respostaSelecionada == 1) {
                respostaSelecionada = null
                atualizarEstadoBotoes(null)
            } else {
                respostaSelecionada = 1
                atualizarEstadoBotoes(1)
            }
        }
        BTNquestC.setOnClickListener {
            if (respostaSelecionada == 2) {
                respostaSelecionada = null
                atualizarEstadoBotoes(null)
            } else {
                respostaSelecionada = 2
                atualizarEstadoBotoes(2)
            }
        }
        BTNquestD.setOnClickListener {
            if (respostaSelecionada == 3) {
                respostaSelecionada = null
                atualizarEstadoBotoes(null)
            } else {
                respostaSelecionada = 3
                atualizarEstadoBotoes(3)
            }
        }

        BTNproxi.setOnClickListener {
            if (respostaSelecionada == null) {
                Toast.makeText(this, "Escolha uma alternativa!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val correta = respostaSelecionada == resp[questaoIndex].correta

            if (correta) {
                pontuacao++
                salvarPontuacao(usuario, pontuacao)
                Toast.makeText(this, "Resposta correta!", Toast.LENGTH_SHORT).show()

                if (questaoIndex == resp.size - 1) {
                    AlertDialog.Builder(this)
                        .setTitle("Fim do Quiz")
                        .setMessage("$usuario acertou $pontuacao de ${resp.size} perguntas.")
                        .setCancelable(false)
                        .setPositiveButton("Ranking") { _, _ -> //ALTERAR PARA TELA DO LOGIN
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            finish() // finaliza tela e vai pro ranking
                        }
                        .setNegativeButton("Início") { _, _ ->
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            finish() // finaliza tela e vai pro inicio
                        }
                        .show()
                } else {
                    questaoIndex++
                    atualizarBotoes()
                    respostaSelecionada = null
                    atualizarEstadoBotoes(null)
                }
            } else {
                Toast.makeText(this, "Resposta incorreta! Tente novamente.", Toast.LENGTH_SHORT)
                    .show()
            }
        }

    }
}