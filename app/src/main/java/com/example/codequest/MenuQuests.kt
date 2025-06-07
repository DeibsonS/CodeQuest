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
            ),
            Questao(
                "Qual linguagem é mais usada para desenvolvimento web no lado do cliente?",
                0,
                listOf(
                    "A - JavaScript",
                    "B - Python",
                    "C - C#",
                    "D - Java"
                )
            ),
            Questao(
                "Qual dessas linguagens é tipicamente usada para scripts de automação?",
                1,
                listOf(
                    "A - Java",
                    "B - Python",
                    "C - C++",
                    "D - Swift"
                )
            ),
            Questao(
                "Qual linguagem é conhecida por sua portabilidade graças à JVM?",
                2,
                listOf(
                    "A - C",
                    "B - JavaScript",
                    "C - Java",
                    "D - Ruby"
                )
            ),
            Questao(
                "Qual linguagem é conhecida pelo uso em ciência de dados?",
                1,
                listOf(
                    "A - PHP",
                    "B - Python",
                    "C - Go",
                    "D - Kotlin"
                )
            ),
            Questao(
                "Qual linguagem é usada nativamente em sistemas iOS?",
                3,
                listOf(
                    "A - Kotlin",
                    "B - C#",
                    "C - Java",
                    "D - Swift"
                )
            ),
            Questao(
                "O que significa 'compilado' em linguagens de programação?",
                2,
                listOf(
                    "A - Interpretado em tempo real",
                    "B - Escrito em alto nível",
                    "C - Traduzido para código de máquina antes da execução",
                    "D - Executado diretamente pelo navegador"
                )
            ),
            Questao(
                "Qual dessas linguagens é mais usada para desenvolvimento Android?",
                0,
                listOf(
                    "A - Kotlin",
                    "B - Swift",
                    "C - Ruby",
                    "D - Rust"
                )
            ),
            Questao(
                "Qual linguagem é famosa por seu uso em sistemas embarcados?",
                1,
                listOf(
                    "A - JavaScript",
                    "B - C",
                    "C - PHP",
                    "D - Java"
                )
            ),
            Questao(
                "Qual linguagem é mais comum em bancos de dados relacionais?",
                2,
                listOf(
                    "A - Python",
                    "B - Java",
                    "C - SQL",
                    "D - Bash"
                )
            ),
            Questao(
                "Qual linguagem tem tipagem dinâmica?",
                0,
                listOf(
                    "A - Python",
                    "B - C++",
                    "C - Java",
                    "D - C#"
                )
            ),
            Questao(
                "Qual linguagem foi criada por Guido van Rossum?",
                1,
                listOf(
                    "A - Ruby",
                    "B - Python",
                    "C - Perl",
                    "D - Go"
                )
            ),
            Questao(
                "Qual dessas linguagens usa chaves {} para definir blocos de código?",
                2,
                listOf(
                    "A - Python",
                    "B - Visual Basic",
                    "C - Java",
                    "D - Lua"
                )
            ),
            Questao(
                "Qual linguagem usa indentação obrigatória para definir blocos?",
                0,
                listOf(
                    "A - Python",
                    "B - JavaScript",
                    "C - PHP",
                    "D - C"
                )
            ),
            Questao(
                "Qual dessas linguagens é funcional e imutável por padrão?",
                3,
                listOf(
                    "A - Java",
                    "B - Kotlin",
                    "C - PHP",
                    "D - Haskell"
                )
            ),
            Questao(
                "Qual linguagem é usada principalmente com a Unity?",
                1,
                listOf(
                    "A - Java",
                    "B - C#",
                    "C - Lua",
                    "D - Perl"
                )
            ),
            Questao(
                "Qual linguagem foi criada pela Microsoft e roda na .NET?",
                1,
                listOf(
                    "A - Java",
                    "B - C#",
                    "C - Python",
                    "D - Dart"
                )
            ),
            Questao(
                "Qual linguagem é conhecida pela sintaxe enxuta e uso em scripts Unix?",
                3,
                listOf(
                    "A - Java",
                    "B - Kotlin",
                    "C - PHP",
                    "D - Bash"
                )
            ),
            Questao(
                "Qual linguagem é geralmente usada para backend com Node.js?",
                0,
                listOf(
                    "A - JavaScript",
                    "B - Ruby",
                    "C - Java",
                    "D - Elixir"
                )
            ),
            Questao(
                "Qual linguagem é conhecida por segurança de memória e performance?",
                2,
                listOf(
                    "A - Python",
                    "B - JavaScript",
                    "C - Rust",
                    "D - Ruby"
                )
            ),
            Questao(
                "Qual linguagem foi criada por Dennis Ritchie?",
                0,
                listOf(
                    "A - C",
                    "B - Java",
                    "C - Python",
                    "D - Kotlin"
                )
            ),
            Questao(
                "Qual dessas é uma linguagem de programação declarativa?",
                3,
                listOf(
                    "A - Java",
                    "B - C#",
                    "C - Ruby",
                    "D - SQL"
                )
            ),
            Questao(
                "Qual linguagem foi projetada para desenvolvimento web com Ruby on Rails?",
                1,
                listOf(
                    "A - PHP",
                    "B - Ruby",
                    "C - Go",
                    "D - Java"
                )
            ),
            Questao(
                "Qual linguagem é usada no Arduino?",
                2,
                listOf(
                    "A - Python",
                    "B - Java",
                    "C - C++",
                    "D - Dart"
                )
            ),
            Questao(
                "Qual linguagem é mais usada para scripts no Excel?",
                1,
                listOf(
                    "A - Java",
                    "B - VBA",
                    "C - Python",
                    "D - SQL"
                )
            ),
            Questao(
                "Qual linguagem tem como principal característica a concorrência leve com goroutines?",
                0,
                listOf(
                    "A - Go",
                    "B - C",
                    "C - Rust",
                    "D - Java"
                )
            ),
            Questao(
                "Qual linguagem foi criada pelo Google?",
                1,
                listOf(
                    "A - Kotlin",
                    "B - Go",
                    "C - Ruby",
                    "D - PHP"
                )
            ),
            Questao(
                "Qual linguagem é usada no Flutter?",
                3,
                listOf(
                    "A - Swift",
                    "B - Java",
                    "C - Kotlin",
                    "D - Dart"
                )
            ),
            Questao(
                "Qual linguagem é considerada de baixo nível?",
                0,
                listOf(
                    "A - Assembly",
                    "B - Python",
                    "C - JavaScript",
                    "D - Java"
                )
            ),
            Questao(
                "Qual linguagem foi projetada para ser uma alternativa moderna ao C++?",
                2,
                listOf(
                    "A - Java",
                    "B - Swift",
                    "C - Rust",
                    "D - Bash"
                )
            ),
            Questao(
                "Qual linguagem é mais associada à web dinâmica com PHP?",
                0,
                listOf(
                    "A - HTML",
                    "B - CSS",
                    "C - Java",
                    "D - Ruby"
                )
            )
        )

        val perguntasAleatorias = resp.shuffled().toMutableList()
        var questaoIndex = 0
        var respostaSelecionada: Int? = null
        val botoes = listOf(BTNquestA, BTNquestB, BTNquestC, BTNquestD)

        fun atualizarBotoes(){
            val questao = perguntasAleatorias[questaoIndex]
            TXTperg.text = questao.pergunta
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

        fun verificarResposta(indiceSelecionado: Int) {
            val questaoAtual = perguntasAleatorias[questaoIndex]
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
        BTNproxi.isEnabled = false

        BTNvoltar.setOnClickListener {
            val TelaMenu = Intent(this, Menu::class.java)
            startActivity(TelaMenu)
        }

        fun desativarTodosBotoes() {
            for (botao in botoes) {
                botao.isEnabled = false
            }
        }

        fun desativarBotao(indice: Int) {
            botoes[indice].isEnabled = false
        }

        fun ativaBotao(indice: Int) {
            botoes[indice].isEnabled = true
        }

        fun verificarRespostaImediata(indiceSelecionado: Int) {
            val questaoAtual = perguntasAleatorias[questaoIndex]
            if (indiceSelecionado == questaoAtual.correta) {
                Toast.makeText(this, "Resposta Correta!", Toast.LENGTH_SHORT).show()
                pontuacao++
                salvarPontuacao(usuario, pontuacao)
                desativarTodosBotoes()
                ativaBotao(indiceSelecionado)
                //Ativa botao de proxima
                BTNproxi.isEnabled = true

            } else {
                Toast.makeText(this, "Resposta Errada!", Toast.LENGTH_SHORT).show()
                desativarBotao(indiceSelecionado)
            }
        }

        BTNquestA.setOnClickListener { verificarRespostaImediata(0) }
        BTNquestB.setOnClickListener { verificarRespostaImediata(1) }
        BTNquestC.setOnClickListener { verificarRespostaImediata(2) }
        BTNquestD.setOnClickListener { verificarRespostaImediata(3) }

        BTNproxi.setOnClickListener {
            questaoIndex++
            if (questaoIndex < perguntasAleatorias.size) {
                atualizarBotoes()
                for (botao in botoes) {
                    botao.isEnabled = true
                }
                BTNproxi.isEnabled = false
            } else {
                AlertDialog.Builder(this)
                    .setTitle("Fim do Quiz")
                    .setMessage("$usuario acertou $pontuacao de ${resp.size} perguntas.")
                    .setCancelable(false)
                    .setPositiveButton("Ranking") { _, _ ->
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }
                    .setNegativeButton("Início") { _, _ ->
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }
                    .show()
            }
        }

    }
}