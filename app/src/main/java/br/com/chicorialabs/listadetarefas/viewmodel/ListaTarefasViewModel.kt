package br.com.chicorialabs.listadetarefas.viewmodel

import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.chicorialabs.listadetarefas.model.Tarefa
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListaTarefasViewModel(val preferences: SharedPreferences) : ViewModel() {

    private val _listaTarefas = MutableLiveData<ArrayList<Tarefa>>().also {
        it.value = arrayListOf()
    }

    init {
        fetchListaTarefas()
    }

    val listaTarefas: LiveData<ArrayList<Tarefa>>
        get() = _listaTarefas


    //adiciona

    //remove

    fun getListTarefa(): ArrayList<Tarefa> {
        val list = preferences.getString("tarefas", "[]")
        val turnsType = object : TypeToken<List<Tarefa>>() {}.type
        return Gson().fromJson(list, turnsType)
    }

    fun save() {
        val listaAtualizada = _listaTarefas.value
        preferences.edit {
            putString("tarefas", Gson().toJson(listaAtualizada))
            commit()
        }
    }


    private fun fetchListaTarefas() {

        //vai gravar uma lista fake se não tiver nada gravado nas SharedPreferences
        if (getListTarefa().isEmpty()) {
            val list = arrayListOf(
                Tarefa(nome = "Compras", data = "17/03/2021"),
                Tarefa(nome = "Lavar Carro", data = "30/03/2021"),
                Tarefa(nome = "Consulta Dentista", data = "28/03/2021"),
                Tarefa(nome = "Cortar grama", data = "18/03/2021", concluida = true),
                Tarefa(nome = "Estudar Kotlin", data = "24/03/2021", true)
            )
            _listaTarefas.value = list
        } else {
            _listaTarefas.value = getListTarefa()
        }

    }

    fun remove(tarefa: Tarefa) {
        _listaTarefas.value?.remove(tarefa)
        save()
    }

    fun atualiza(tarefa: Tarefa, checked: Boolean, position: Int) {
        tarefa.concluida = checked
        _listaTarefas.value?.set(position, tarefa)
        save()
    }
}