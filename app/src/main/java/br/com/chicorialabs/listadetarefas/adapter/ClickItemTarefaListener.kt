package br.com.chicorialabs.listadetarefas.adapter

import br.com.chicorialabs.listadetarefas.model.Tarefa

interface ClickItemTarefaListener {

    fun onItemClickListener(tarefa: Tarefa)

    fun onItemLongClickListener(tarefa: Tarefa)
}