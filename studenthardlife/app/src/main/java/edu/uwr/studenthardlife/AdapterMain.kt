package pl.edu.uwr.studenthardlife

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

class AdapterMain: RecyclerView.Adapter<AdapterMain.listMainViewHolder>() {
    private val lista = ObjectLosts.list

    inner class listMainViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val listNameButton: Button = itemView.findViewById(R.id.button2)
        fun bind(item: ListOFLists) {
            println(item.id.toString())
            listNameButton.text = "Lista zadań " + item.id.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): listMainViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_view, parent, false)

        return listMainViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: listMainViewHolder, position: Int) {
        val currentItem = lista[position]
        holder.bind(currentItem)
        holder.listNameButton.setOnClickListener {
            holder.listNameButton.findNavController().navigate(
                FragmentMainDirections.actionFragmentMainToDetalFragment(
                    valNum = lista[position].num

                )
            )
        }

    }

    override fun getItemCount(): Int {
        return lista.size
    }
}
