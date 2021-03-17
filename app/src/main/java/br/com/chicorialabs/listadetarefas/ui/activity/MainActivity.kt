package br.com.chicorialabs.listadetarefas.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.chicorialabs.listadetarefas.R
import br.com.chicorialabs.listadetarefas.adapter.TarefaAdapter
import br.com.chicorialabs.listadetarefas.databinding.ActivityMainBinding
import br.com.chicorialabs.listadetarefas.model.Tarefa

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter = TarefaAdapter()
    private val rvList: RecyclerView by lazy {
        binding.mainRecyclerview
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        inicializaRecyclerView()
        updateList()

    }

    private fun inicializaRecyclerView() {
        rvList.adapter = adapter
        rvList.layoutManager = LinearLayoutManager(this)
    }

    fun updateList(){
        adapter.updateList(arrayListOf(
            Tarefa(nome="Compras", data="17/03/2021", imagem = ""),
            Tarefa(nome="Lavar Carro", data="30/03/2021", imagem = "")
        ))
    }


}