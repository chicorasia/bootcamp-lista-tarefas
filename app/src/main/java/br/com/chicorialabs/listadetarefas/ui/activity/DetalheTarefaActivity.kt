package br.com.chicorialabs.listadetarefas.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toolbar
import br.com.chicorialabs.listadetarefas.R
import br.com.chicorialabs.listadetarefas.model.Tarefa

class DetalheTarefaActivity : AppCompatActivity() {

    private var tarefaRecebida: Tarefa? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_tarefa)
        //implementar a toolbar com os comportamentos do botão
        //setHomeAsUpEnabled
        //.. e o método para encerrar a atividade após clicar no retorno

        initToolbar()
        getExtra()
        inicializaCampos(tarefaRecebida)

    }

    private fun initToolbar() {
        val toolbar = findViewById<View>(R.id.detalhe_toolbar) as androidx.appcompat.widget.Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun inicializaCampos(tarefaRecebida: Tarefa?) {
        val campoNome = findViewById<TextView>(R.id.detalhe_nome_txt) as TextView
        val campoData = findViewById<TextView>(R.id.detalhe_data_txt) as TextView
        val checkBox = findViewById<CheckBox>(R.id.checkBox) as CheckBox

        campoNome.text = tarefaRecebida?.nome
        campoData.text = tarefaRecebida?.data
        checkBox.isChecked = tarefaRecebida?.concluida == true

    }

    private fun getExtra() {

        tarefaRecebida = intent.getParcelableExtra(EXTRA_TAREFA)

    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    companion object {

        const val EXTRA_TAREFA: String = "EXTRA_TAREFA"
    }


}