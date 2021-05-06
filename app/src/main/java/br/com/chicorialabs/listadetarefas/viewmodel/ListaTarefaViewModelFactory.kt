package br.com.chicorialabs.listadetarefas.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ListaTarefaViewModelFactory(
    private val sharedPreferences: SharedPreferences
    ) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ListaTarefaViewModelFactory(sharedPreferences) as T
    }
}