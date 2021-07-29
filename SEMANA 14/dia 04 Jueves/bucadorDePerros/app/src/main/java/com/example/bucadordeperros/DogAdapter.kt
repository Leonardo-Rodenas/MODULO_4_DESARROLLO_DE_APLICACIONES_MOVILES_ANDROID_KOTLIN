package com.example.bucadordeperros

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/*(12.2) A la clase DogAdapter creada se le da como parámetros una constante images, del tipo Lista
de Strings (List<String>) y que esta hereda (creo que eso son los dos puntos) de recyclerview un adaptador
(Adapter) que tiene en su interior el ViewHolder (aún no creado en este punto, por lo que va a dar un error)*/

/*(12.5) Para corregir el error de 12.2, pasamos por los pasos 12.3 y 12.4 y luego en este 12.5 le metemos
al Adapter el DogViewHolder creado en los mencionados pasos. Aún así, despues de hacer esto, la parte de
 class DogAdapter va a quedar marcada con un error, para eso lo que hay que hacer es dar click en el
 nombre de la funcion y luego en la ampolleta roja, selecionando posteriormente la opcion "implement
 members" y crear los tres metodos que nos va a sugerir el IDE*/

/*En estos métodos generados automaticamente lo que se hace es lo siguiente:

override fun onCreateViewHolder --> Esta función, se activa automáticamente cuando ella lo necesite,
por lo que nosotros no tenemos que hacer nada, y lo que hace es crear el View Holder (el de DogViewHolder)
para inicializar la lista,  esto lo logra creando una función (línea 50) que crear una instancia de la
clase LayoutInflater (no es una clase creada por nosotros, es del mismo sistema, viene creada por
defecto al crear el proyecto, y lo que hace es que nos permite crear vistas), finalmente en linea 51
se retorna la clase DogViewHolder, para que luego llege a la función onBindViewHolder y como argumentos
dentro de los paréntesis se le da la clase Layoutinflater y se le dice que "infle" (inflate = llenar con info)
lo que se encuentra en la ubicación dada (R.layout.[item aun no creado y que generará un error en el
codigo, por el momento dejar así]), que es el .xml donde se diseñan las tarjeats que van a contener
las imagenes de los perros... ¿Pero no caché por que se le da un parent y tambien un attachToRoot: false,
eso debo investigarlo?

OJO --> el error en la ubicación se arregla creando el nuevo .xml llamado item_dog y colocandolo en
la ubicación pedida. Ese .xml se trabaja con cardview, y para ver como esta hecho mirar el código en
pantalla split (como no puedo poner comentarios en el codigo del .xml, dejo escrito aquí que ese punto
es el (13), así que se verá un salto del 12 al 14, pero es por eso.

override fun onBindViewHolder --> Esta función lo que hace es que dice "soy la posición X (de hay el
atributo position: Int), y tengo la instancia el ViewHolder (holder: DogViewHolder), por lo que con
ello voy a pintar lo que me indiquen, en la posición y en los campos que tenga disponible para eso. Además
en la linea 56 se le da una funcion holder, la cual se le le enlza al item (que son las tarjetas
donde van las fotos de los perros).

override fun getItemCount --> retornar el numero de items que va a tener la lista (si son muchos, .size es
el parámetro que identifica que la cantidad va a ser el tamaño de la lista)

*/

class DogAdapter (private val images:List<String>):RecyclerView.Adapter<DogViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return DogViewHolder(layoutInflater.inflate(R.layout.item_dog, parent, false))
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        val item:String = images[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return images.size
    }
}