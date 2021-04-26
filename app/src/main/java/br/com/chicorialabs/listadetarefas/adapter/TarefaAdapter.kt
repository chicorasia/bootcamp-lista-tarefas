package br.com.chicorialabs.listadetarefas.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.chicorialabs.listadetarefas.databinding.TarefaItemBinding
import br.com.chicorialabs.listadetarefas.model.Tarefa

class TarefaAdapter(val listener: ClickItemTarefaListener) :
    RecyclerView.Adapter<TarefaAdapter.TarefaAdapterViewHolder>() {

    private lateinit var binding: TarefaItemBinding
    val lista: MutableList<Tarefa> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TarefaAdapterViewHolder {
        binding = TarefaItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )

        return TarefaAdapterViewHolder(binding, listener = listener, lista = lista)
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
        internal val binding: TarefaItemBinding,
        private val listener: ClickItemTarefaListener,
        lista: List<Tarefa>
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(tarefa: Tarefa) {
            binding.itemNomeTxt.text = tarefa.nome
            binding.itemDataTxt.text = tarefa.data.toString()
            binding.itemConcluidoCbx.isChecked = tarefa.concluida
        }


        init {
            binding.root.setOnClickListener {
                listener.onItemClickListener(lista[adapterPosition])
            }
            binding.root.setOnLongClickListener {
                listener.onItemLongClickListener(lista[adapterPosition])
                true
            }
            binding.itemConcluidoCbx.setOnCheckedChangeListener { buttonView, isChecked ->
                listener.onItemCheckedChangeListener(
                    lista[adapterPosition],
                    isChecked, adapterPosition
                )
            }


        }
    }

}
