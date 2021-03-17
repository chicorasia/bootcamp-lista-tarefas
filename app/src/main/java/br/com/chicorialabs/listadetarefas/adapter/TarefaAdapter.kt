package br.com.chicorialabs.listadetarefas.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.chicorialabs.listadetarefas.databinding.TarefaItemBinding
import br.com.chicorialabs.listadetarefas.model.Tarefa

class TarefaAdapter : RecyclerView.Adapter<TarefaAdapter.TarefaAdapterViewHolder>() {

    private lateinit var binding: TarefaItemBinding
    val lista: MutableList<Tarefa> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TarefaAdapterViewHolder {
        binding = TarefaItemBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)
//        val view = binding.root
        return TarefaAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TarefaAdapterViewHolder, position: Int) {
        holder.bind(lista[position])
    }

    override fun getItemCount(): Int = lista.size

    fun updateList(listaRecebida: List<Tarefa>) {
        this.lista.clear()
        this.lista.addAll(listaRecebida)
        notifyDataSetChanged()
    }


    class TarefaAdapterViewHolder(private val binding: TarefaItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(tarefa: Tarefa){
            binding.itemNomeTxt.text = tarefa.nome
            binding.itemDataTxt.text = tarefa.data.toString()
            binding.itemConcluidoCbx.isChecked = tarefa.concluida
            binding.itemImagemIv.setImageDrawable(Drawable
                .createFromPath(tarefa.imagem))
        }

    }
}