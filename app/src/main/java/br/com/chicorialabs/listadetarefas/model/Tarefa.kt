package br.com.chicorialabs.listadetarefas.model

import java.util.*

data class Tarefa(
    val nome: String,
    val data: String = Calendar.getInstance().toString(),
    var concluida: Boolean = false,
    val imagem: String = "@tools:sample/avatars"
) {



}