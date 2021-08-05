package com.example.librosapp

import androidx.recyclerview.widget.RecyclerView
import com.example.librosapp.databinding.ItemLibrosBinding

/*(12.3) Como al adaptador de 12.2 le falta el view Holder, se crea acá la clase LibrosHolder para llenarlo. Como
es costumbre esta clase va a tener de parámetros la vista sobre la cual se va a pintar la informacion, per
como esta vista se imlementó con un VieBinding, se le pasa como parametro a usar una constante binding
de ItemLibrosBinding. _Todo esto va a pertenecer a la clase RecyclerView, conteniendo el ViewHolder
que estamos creando y que recibe de parámetro la vista sobre la cual va a pintar (binding.root que hace
referencia a la raiz donde esta item_libros = el cardview a pintar)*/

class LibrosHolder(val binding: ItemLibrosBinding): RecyclerView.ViewHolder(binding.root) {}