package br.com.chicorialabs.listadetarefas.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import androidx.versionedparcelable.VersionedParcelize
import java.io.Serializable
import java.util.*

@Parcelize
data class Tarefa(
    val nome: String,
    val data: String = Calendar.getInstance().toString(),
    var concluida: Boolean = false,
    val imagem: String = "@tools:sample/avatars"
) : Parcelable