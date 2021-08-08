package com.example.librosapp

import androidx.recyclerview.widget.RecyclerView
import com.example.librosapp.databinding.ItemLibrosBinding

/*(12.3) Como al adaptador de 12.2 le falta el view Holder, se crea acá la clase LibrosHolder para llenarlo. Como
es costumbre esta clase va a tener de parámetros la vista sobre la cual se va a pintar la informacion, per
como esta vista se imlementó con un VieBinding, se le pasa como parametro a usar una constante binding
de ItemLibrosBinding. _Todo esto va a pertenecer a la clase RecyclerView, conteniendo el ViewHolder
que estamos creando y que recibe de parámetro la vista sobre la cual va a pintar (binding.root que hace
referencia a la raiz donde esta item_libros = el cardview a pintar)*/


/*ojo --> acá al hacer lo de las descripciones de los libros agregue el listener:onItemblah a la clase
y eso es un poco distinto a lo del video*/
class LibrosHolder(val binding: ItemLibrosBinding, listener : LibrosAdapter.onItemClickListener): RecyclerView.ViewHolder(binding.root) {

    init {
        //salen 2 setOn... escojer el de las llaves {} el otro no funciona

        itemView.setOnClickListener {

            listener.onItemClick(adapterPosition)

        //ahora me devuelvo al adapter y en la parte onCreateViewHolder al retorno le entrego mListener
        }
    }
}