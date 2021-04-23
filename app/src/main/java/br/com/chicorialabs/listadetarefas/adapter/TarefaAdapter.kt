package br.com.chicorialabs.listadetarefas.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.chicorialabs.listadetarefas.databinding.TarefaItemBinding
import br.com.chicorialabs.listadetarefas.model.Tarefa

//TODO 001: Criar uma interface ClickItemTarefaListener
//TODO 002: Criar os métodos clickItemTarefa e longClickItemTarefa na interface (sem corpo)



class TarefaAdapter() :
    RecyclerView.Adapter<TarefaAdapter.TarefaAdapterViewHolder>() {

    private lateinit var binding: TarefaItemBinding
    val lista: MutableList<Tarefa> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TarefaAdapterViewHolder {
        binding = TarefaItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )

        //TODO 003: passar essa interface como parâmetro do TarefaAdapter
        return TarefaAdapterViewHolder(binding, lista=lista)
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


    class TarefaAdapterViewHolder(
        private val binding: TarefaItemBinding,
        lista: List<Tarefa>
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(tarefa: Tarefa) {
            binding.itemNomeTxt.text = tarefa.nome
            binding.itemDataTxt.text = tarefa.data.toString()
            binding.itemConcluidoCbx.isChecked = tarefa.concluida
        }

        //TODO 004: vincular os métodos da interface com o viewholder


    }
}