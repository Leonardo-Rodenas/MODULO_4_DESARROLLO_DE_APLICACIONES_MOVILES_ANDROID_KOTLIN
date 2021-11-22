package cl.duoc.ejemploroom.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.duoc.ejemploroom.R
import cl.duoc.ejemploroom.databinding.ItemLayoutBinding
import cl.duoc.ejemploroom.model.Mensaje

class MensajeAdapter() : RecyclerView.Adapter<MensajeAdapter.MensajeHolder>() {

    var lista:List<Mensaje> = ArrayList()
    lateinit var listener:OnItemClickListener


    class MensajeHolder(val view: View, var listener: OnItemClickListener): RecyclerView.ViewHolder(view)
    {
        fun bindData(mensaje:Mensaje)
        {
            val binding = ItemLayoutBinding.bind(view)
            with(binding)
            {
                lblMensaje.text = mensaje.contenido
            }
            view.setOnClickListener{
                listener.onMensajeClick(mensaje)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MensajeHolder {
        val v:View = LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false)
        return MensajeHolder(v,listener)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: MensajeHolder, position: Int) {
        holder.bindData(lista[position])
    }

    fun setMensaje(mensajes:List<Mensaje>)
    {
        lista = mensajes
        notifyDataSetChanged()
    }

    interface OnItemClickListener{
        fun onMensajeClick(mensaje: Mensaje)
    }

    fun setOnItemClickListener(listener: OnItemClickListener)
    {
        this.listener = listener
    }

}