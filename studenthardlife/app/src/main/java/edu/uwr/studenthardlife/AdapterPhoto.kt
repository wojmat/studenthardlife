package pl.edu.uwr.studenthardlife

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.edu.uwr.studenthardlife.databinding.PhotoViewBinding

class AdapterPhoto (private val dbHandler: DBHandler, private val number: Int): RecyclerView.Adapter<AdapterPhoto.ViewHolder>() {
    class ViewHolder(private var itemBinding: PhotoViewBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        val del = itemBinding.DELETEPhoto

        fun bind(text: ListTable){
            if(text.opis != "none") {
                itemBinding.imageView.setImageURI(Uri.parse(text.opis))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = PhotoViewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(dbHandler.getElementtt(number)[position])
        holder.del.setOnClickListener {
            dbHandler.deleteElement(dbHandler.getElementtt(number)[position].id)
            notifyDataSetChanged()
        }

    }

    override fun getItemCount(): Int{
        return dbHandler.getElementtt(number).size
    }
}