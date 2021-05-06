package br.com.chicorialabs.listadetarefas.ui.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.chicorialabs.listadetarefas.R
import br.com.chicorialabs.listadetarefas.adapter.ClickItemTarefaListener
import br.com.chicorialabs.listadetarefas.adapter.TarefaAdapter
import br.com.chicorialabs.listadetarefas.databinding.DrawerMenuBinding
import br.com.chicorialabs.listadetarefas.model.Tarefa
import br.com.chicorialabs.listadetarefas.ui.activity.DetalheTarefaActivity.Companion.EXTRA_TAREFA
import br.com.chicorialabs.listadetarefas.viewmodel.ListaTarefasViewModel

// TODO 001: criar um pacote ui.touchhelper
// TODO 002: criar uma classe abstrata SwipeTouchHelper que extende ItemTouchHelper.SimpleCallback indicando as direções do movimento (dragflags e swipeflags)
// TODO 003: Sobrescrever os métodos da classe SwipeTouchHelper

class MainActivity : AppCompatActivity(), ClickItemTarefaListener {

    private val listaTarefaViewModel by lazy {
        ListaTarefasViewModel(getInstanceSharedPreferences())
    }
    private lateinit var binding: DrawerMenuBinding

    private lateinit var adapter: TarefaAdapter

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

//        TODO 004: Criar um swipeHandler como um SwipeToDeleteCallback via object expression

//        TODO 005: criar um ItemTouchHelper que recebe o swipehandler e vincular ao recyclerview


    }

    private fun inicializaToolbar() {
        val drawerLayout = binding.root
        setSupportActionBar(binding.drawerInclude.mainToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val actionBarToggle = ActionBarDrawerToggle(
            this, drawerLayout,
            R.string.open_drawer, R.string.close_drawer
        )
        drawerLayout.addDrawerListener(actionBarToggle)
        actionBarToggle.syncState()
    }

    private fun inicializaRecyclerView() {
        listaTarefaViewModel.listaTarefas.observe(this) {
            adapter = TarefaAdapter(it, this)
            rvList.adapter = adapter
            rvList.layoutManager = LinearLayoutManager(this)
        }
    }

    private fun updateList() {
        adapter.updateList()
    }

    fun getListTarefa() = listaTarefaViewModel.getListTarefa()

    private fun getInstanceSharedPreferences(): SharedPreferences =
        getSharedPreferences(
            "br.com.chicorialabs.listadetarefas.PREFERENCES",
            Context.MODE_PRIVATE
        )

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
        return when (item.itemId) {
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


    override fun onSupportNavigateUp(): Boolean {
        val actionBarToggle = ActionBarDrawerToggle(
            this, binding.root,
            R.string.open_drawer, R.string.close_drawer
        )
        binding.root.addDrawerListener(actionBarToggle)
        actionBarToggle.syncState()
        showToast("Clicou no hamburger!")
        return true
    }

    override fun onItemClickListener(tarefa: Tarefa) {
        val intent = Intent(this, DetalheTarefaActivity::class.java)
        intent.putExtra(EXTRA_TAREFA, tarefa)
        startActivity(intent)
    }

    override fun onItemLongClickListener(tarefa: Tarefa) {
        listaTarefaViewModel.remove(tarefa)
        updateList()
    }

    override fun onItemCheckedChangeListener(tarefa: Tarefa, isChecked: Boolean, position: Int) {
        listaTarefaViewModel.atualiza(tarefa, isChecked, position)

    }
    




}