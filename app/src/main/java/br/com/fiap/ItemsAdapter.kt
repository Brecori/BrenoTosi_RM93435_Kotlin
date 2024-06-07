package br.com.fiap

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gskotlin.ItemModel


class ItemsAdapter : RecyclerView.Adapter<ItemsAdapter.ItemViewHolder>()  {

    private val praias = mutableListOf<ItemModel>()
    override fun getItemCount(): Int = praias.size

    //    Funções de iteração
    fun addPraia(novaPraia: ItemModel) {
        praias.add(novaPraia)
        notifyDataSetChanged()
    }

    fun removeItem(item: ItemModel) {
        praias.remove(item)
        notifyDataSetChanged()
    }

    fun clearData() {
        praias.clear()
        notifyDataSetChanged()
    }


//    Funções de ViewHolder


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val praia = praias[position]

        holder.bind(praia)

        holder.buttonDelete.setOnClickListener {
            removeItem(praia)
        }
    }

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewPraia= view.findViewById<TextView>(R.id.praiaView)
        val textViewEstado= view.findViewById<TextView>(R.id.estadoView)
        val textViewCidade= view.findViewById<TextView>(R.id.cidadeView)

        val buttonDelete = view.findViewById<ImageButton>(R.id.buttonDelete)
        fun bind(praiaDados: ItemModel) {
            textViewPraia.text = praiaDados.praia
            textViewCidade.text = praiaDados.cidade
            textViewEstado.text = praiaDados.estado

            buttonDelete.setOnClickListener {
                praiaDados.onRemove(praiaDados)
            }
        }
    }
}