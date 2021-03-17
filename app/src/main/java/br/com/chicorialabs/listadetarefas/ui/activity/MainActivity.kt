package br.com.chicorialabs.listadetarefas.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
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

    private fun updateList(){
        adapter.updateList(arrayListOf(
            Tarefa(nome="Compras", data="17/03/2021", imagem = ""),
            Tarefa(nome="Lavar Carro", data="30/03/2021", imagem = "")
        ))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.menu_item_1 -> {
                showToast("Clicou no item 1")
                true
            }
            R.id.menu_item_2 -> {
                showToast("Clicou no item 2")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}