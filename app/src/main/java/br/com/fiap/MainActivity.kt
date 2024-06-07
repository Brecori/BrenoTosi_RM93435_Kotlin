package br.com.fiap

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.gskotlin.ItemModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        setContentView(R.layout.activty_integrantes)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView);
        val itemsAdapter = ItemsAdapter()
        recyclerView.adapter = itemsAdapter

        val button = findViewById<Button>(R.id.button)
        val praia = findViewById<EditText>(R.id.praia)
        val cidade = findViewById<EditText>(R.id.cidade)
        val estado = findViewById<EditText>(R.id.estado)
        val deleteAllButton = findViewById<Button>(R.id.deleteAll)

        fun hideKeyboard(context: Context, view: View) {
            val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }

        deleteAllButton.setOnClickListener{
            itemsAdapter.clearData()
        }


        button.setOnClickListener {
            if (praia.text.isEmpty()) {
                praia.error = "Campo obrigatório"
                return@setOnClickListener
            }
            else if (cidade.text.isEmpty()) {
                cidade.error = "Campo obrigatório"
                return@setOnClickListener
            }
            else if (estado.text.isEmpty()) {
                estado.error = "Campo obrigatório"
                return@setOnClickListener
            }
            else {
                val dadosPraia = ItemModel(
                    praia = praia.text.toString(),
                    cidade = cidade.text.toString(),
                    estado = estado.text.toString(),
                    onRemove = {
                        itemsAdapter.removeItem(it)
                    }
                )

                itemsAdapter.addPraia(dadosPraia)
                praia.text.clear();
                cidade.text.clear();
                estado.text.clear();
            }

            hideKeyboard(this, it)
        }
    }
}