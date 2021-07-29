package com.example.bucadordeperros

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.bucadordeperros.databinding.ItemDogBinding
import com.squareup.picasso.Picasso

/*(12.3) Como al adaptador de 12.2 le falta el view Holder, se crea acá la clase DogViewHolder para llenarlo. Como
es costumbre esta clase va a tener de parámetros la vista sobre la cual se va a pintar la informacion
y que es de clase View (view:View) y va ser _todo esto de la clase RecyclerView, conteniendo el ViewHolder
que estamos creando y que recibe de parámetro la vista sobre la cual va a pintar*/
class DogViewHolder(view: View):RecyclerView.ViewHolder(view) {

    /*(12.4) Dentro del Holder, se crea u método el cual va a tener la image:String, o sea la imagen
    a pintar en cada celda, y que por ahora se deja así a medio esccribir y que nos devolvemos a la
    clase DogAdapter para finalizarla y corregir el error que se habia dejado antes en 12.2*/

    /*(14) Lo que se hace acá es continuar despues de haber creado el item_dog.xml para dar el binding
    y proporcionar el entorno donde se va a crear y mostrar esa imagen. Para el binding, lo de la línea
    22*/

    private val binding = ItemDogBinding.bind(view)

    fun bind(image:String){
        /*(14.1)Acá la imagen de los perros se carga con la libreria importada al inicio del proyecto ("Picasso"),
        esto se hace dandole la indicación del nombre de la libreria, obteniendo (.get = .obtener) la
        carga (.load = .carga) del atributo image (que en este caso es el link de internet a la imagen
        que está dado por la respuesta que nos envia la API al hacer la query) y que lo coloque dentro
        (.into) de la vista llamada con la id = ivDog (ubidada en el item_Dog.xml)*/
        Picasso.get().load(image).into(binding.ivDog)

    }
}