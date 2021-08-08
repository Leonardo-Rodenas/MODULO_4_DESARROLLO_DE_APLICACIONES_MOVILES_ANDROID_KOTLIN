package com.example.librosapp

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.librosapp.databinding.ItemLibrosBinding
import com.example.librosapp.datosdeAPI.googleBooks

/*(12.2) A la clase LibrosAdapter creada se le da como parámetros una constante libros, de la clase
googleBooks (creada en (1)) y que esta hereda (creo que eso son los dos puntos) de recyclerview un adaptador
(Adapter) que tiene en su interior el ViewHolder (aún no creado en este punto, por lo que va a dar un error,
en la parte de <>, pero hay que dejarlo así por mientras, para comenzar a solucionarlo esto se crea
una nueva clase con el Holder que falta llamada LibrosHolder.tk [sigue en 12.3 - LibrosHolder.tk])*/
/*(12.5) Para corregir el error de 12.2, pasamos por los pasos 12.3 y 12.4 y luego en este 12.5 le metemos
al Adapter el LibrosHolder creado en los mencionados pasos. Aún así, despues de hacer esto, la parte de
class DogAdapter va a quedar marcada con un error, para eso lo que hay que hacer es dar click en el
nombre de la funcion y luego en la ampolleta roja, selecionando posteriormente la opcion "implement
members" y crear los tres metodos que nos va a sugerir el IDE

En estos métodos generados automaticamente lo que se hace es lo siguiente:

override fun onCreateViewHolder --> Esta función, se activa automáticamente cuando ella lo necesite,
por lo que nosotros no tenemos que hacer nada, y lo que hace es retornar (return) el View Holder (el LibrosHolder)
y usar como parámetros el ItemLibrosBinding (usado en la viewBinding) e inflarlo (inflate = Each activity
has an associated layout file. The activity and the layout are connected by a process known as layout
inflation. When the activity starts, the views that are defined in the XML layout files are turned into
(or "inflated" into) Kotlin view objects in memory. Once this happens, the activity can draw these objects
to the screen and also dynamically modify them), esto lo logra a traves de la clase LayoutInfalter, que recibe de
parámetros parent (el recycler view, donde se van a llenar las cardview) y context (la cardview)... el false
ni idea porque va, pero es necesario ponerlo

override fun onBindViewHolder --> Esta función lo que hace es que dice "soy la posición X (de hay el
atributo position: Int), y tengo la instancia el ViewHolder (holder: LibrosHolder), por lo que con
ello voy a pintar lo que me indiquen, en la posición y en los campos que tenga disponible para eso (se indica
titulo, autor e imagen en los campos disponibles de tvtitulo, tvautor e ivlibro que estan vacios hasta
este momento), Importante que como la imagen puede faltar, se maneja un posible error en el bloque try/catch
que indica que si hay imagen, se ponga en ivlibro, si no es así, para evitar el error (e: NullPointerException)
se reemplace la imagen faltante con la imagen de mipmap llamada "noimagendisponible".

override fun getItemCount --> retornar el numero de items que va a tener la lista (si son muchos, .size es
el parámetro que identifica que la cantidad va a ser el tamaño de la lista)

*/

class LibrosAdapter (private val libros:googleBooks):RecyclerView.Adapter<LibrosHolder>() {

    /*Aqui comienzo con lo de la Activity con la descripcion de los libros, basado en este video
    https://www.youtube.com/watch?v=UbP8E6I91NA
    https://www.youtube.com/watch?v=dB9JOsVx-yY <-- desde este video (el de arriba es para saber donde colocar lo del minuto  4:48
    https://www.youtube.com/watch?v=EoJX7h7lGxM&t=64s <-- al terminar el anterior seguir con este

    al inicio intente poner otro nombre a la variable mListener (le puse Itemescuchador) y no resulta,
    eso es raro, hay que importar una cosa para que funcione*/

    private  lateinit var mListener: onItemClickListener

    interface onItemClickListener {

        fun onItemClick (position : Int)

    }

    fun setOnItemClickListener (listener: onItemClickListener) {

        mListener = listener
        //ahora saltar a la clase librosHolder.kt
    }

    //Acá al final le pongo el mListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibrosHolder {
       return LibrosHolder(ItemLibrosBinding.inflate( LayoutInflater.from(parent.context), parent, false), mListener)
   /*continuo acá con los clicklistener de la activity libros*/
    }

    override fun onBindViewHolder(holder: LibrosHolder, position: Int) {
        holder.binding.apply {

            var autores = libros.items[position].volumeInfo.authors
            tvtitulo.text = libros.items[position].volumeInfo.title

            try {
                Glide.with(ivlibro.context)
                    .load(libros.items.get(position).volumeInfo.imageLinks.thumbnail)
                    .fitCenter()
                    .into(ivlibro)
                tvautor.text = autores[0]
                tvtitulo.text = libros.items[position].volumeInfo.title
            } catch (e: NullPointerException) {
                ivlibro.setImageResource(R.mipmap.noimagendisponible)
                tvautor.text = "Sin autor"
                tvtitulo.text = "Sin título"
            }
        }
    }

    override fun getItemCount(): Int {
        return libros.items.size
    }

}