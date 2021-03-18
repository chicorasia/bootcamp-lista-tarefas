package br.com.chicorialabs.listadetarefas.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.chicorialabs.listadetarefas.R
import br.com.chicorialabs.listadetarefas.adapter.TarefaAdapter
import br.com.chicorialabs.listadetarefas.databinding.DrawerMenuBinding
import br.com.chicorialabs.listadetarefas.model.Tarefa
import br.com.chicorialabs.listadetarefas.ui.activity.DetalheTarefaActivity.Companion.EXTRA_TAREFA

class MainActivity : AppCompatActivity(), CLickItemTarefaListener {


    private lateinit var binding: DrawerMenuBinding
    private val adapter = TarefaAdapter(this)
    private val rvList: RecyclerView by lazy {
        binding.drawerInclude.mainRecyclerview
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DrawerMenuBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        inicializaToolbar()
        inicializaRecyclerView()
        updateList()

    }

    private fun inicializaToolbar() {
        val drawerLayout = binding.root
        //        val toolbar = findViewById<Toolbar>(R.id.main_toolbar)
        //        setSupportActionBar(toolbar)
        setSupportActionBar(binding.drawerInclude.mainToolbar)


        val actionBarToggle = ActionBarDrawerToggle(
            this, drawerLayout,
            R.string.open_drawer, R.string.close_drawer
        )
        drawerLayout.addDrawerListener(actionBarToggle)
        actionBarToggle.syncState()
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
        Log.i("listatarefas", "onCreateOptionsMenu: Inflando o menu")
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

    override fun clickItemTarefa(tarefa: Tarefa) {
        val intent = Intent(this, DetalheTarefaActivity::class.java)
        intent.putExtra(EXTRA_TAREFA, tarefa)
        startActivity(intent)

        showToast(tarefa.nome)

    }


}