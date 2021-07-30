package com.example.listadesuperheroes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_superhero.view.* //esto no se importo solo, y tenia que agregarlo yo

/*(4) Se crea el adaptador cuya función es decir (solo decir, no lo hace, este adapta la info, de hay su nombre)
con que info se llena y en donde debe ir cada bloque de información. En esto se inicia borrando las llaves
y dandole el parámetro al adaptador, el cual siempre se va a necesitar cuando este sea instanciado,
en este caso el parámetro es lo de la data class SuperHero.kt*/

/*OJO --> Al crear esta línea, va a der un error, especificamente en la parte de HeroHolder, esto se debe
* a que desde la parte RecyclerView.Adapter<HeroAdapter.HeroHolder>() estamos llamando al adaptador para
* que diga y adapte la info que se va a usar, pero eso se la pasa al HeroHolder (un Holder) para que sea
* este  h   older el que va a pintar la info en cada tarjeta creada en item_superhero.xml, y como aún
* el Holder no está creado (se creará más adelante), al darle la ruta donde debe buscarlo (esta clase.
* HeroHolder), no lo encuentra y marca un error  */

class HeroAdapter(val superhero:List<SuperHero>):RecyclerView.Adapter<HeroAdapter.HeroHolder>(){

    //(6) Las siguientes 3 funciones los crea de forma automatica, dandole click derecho a la ampolleta roja
    //que aparece en HeroAdapter al momento de crearlo (esa ampolleta aparece porque estamos trabajando
    // con la libreria del recycleview, y como tiene código esa libreria, se establece que para usar esta
    // parte, hay que declarar esos metodos en amarillo para que funcione correctamente.


    /*Esta función, se activa automáticamente cuando ella lo necesite, por lo que nosotros no tenemos que
    * hacer nada, y lo que hace es crear el View Holder (el de HeroHolder) para inicializar la lista,  esto lo
    * logra con lo que esta dentro de las llaves de la función*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroHolder {
        /*Acá se crea una instancia de la clase LayoutInflater (no es una clase creada por nosotros, es
        del mismo sistema, viene creada por defecto al crear el proyecto, y lo que hace es que nos permite
        crear vistas)*/
        val layoutInflater = LayoutInflater.from(parent.context)
        /*acá se retorna la clase HeroHolder, para que luego llege a la función onBindViewHolder y como argumentos
        dentro de los paréntesis se le da la clase Layoutinflater y se le dice que "infle" (inflate = llenar con info)
        lo que se encuentra en la ubicación dada (R.layout.item_superhero), lo cual resulta ser el .xml donde se
        diseñan las tarjeats que van a contener la información final a mostrar de cara al usuario... ¿Pero no caché por
        que se le da un parent y tambien un attachToRoot: false, eso debo investigarlo?*/
        return HeroHolder(layoutInflater.inflate(R.layout.item_superhero, parent, false))
    }

    /*Esta función lo que hace es que dice "soy la posición X (de hay el atributo position: Int), y tengo la instancia
    * del ViewHolder (holder: HeroHolder), por lo que con ello voy a pintar lo que me indiquen, en la posición y
    * en los campos que tenga disponible para eso*/
    override fun onBindViewHolder(holder: HeroHolder, position: Int) {
        //holder = objeto de la clase HeroHolder que hereda sus características y puede hacer lo mismo que ella,
        //en este caso usar el método render para que según la posición infle los item_superhero.xml
        holder.render(superhero[position])
    }

    //Este sirve para decirle a la lista cuantos items va a tener
    override fun getItemCount(): Int {
        return superhero.size //En vez de poner 10, retorna el tamaño de la lista, en caso de que esta sea muy larga
    }

    //(5) Esto lo crea en el segundo video y me da error, es más o menos en el min 05:26, en _todo caso, acá se hace
    //referencia en view al item_superhero.xml
    class HeroHolder (val view:View):RecyclerView.ViewHolder(view){
        fun render (superhero:SuperHero){
            //Acá en render lo que hace es traerme la info de la lista creada antes, y llenar en cada una de las vueltas
            //que se va a dar en la función getItemCount la info que trae sobre cada uno de los TextView
            // (tvRealName, tvSuperHeroName y tvPublisher) de acuerdo a su posición en la lista
            view.tvRealName.text = superhero.realName
            view.tvSupeHeroName.text = superhero.superHeroName
            view.tvPublisher.text = superhero.publisher
            /*La imagen se carga con la libreria importada al inicio del proyecto ("Picasso"), esto se hace
            dandole la indicación del nombre de la libreria, obteniendo (.get = .obtener) la carga (.load = .carga)
            del atributo superhero.image (que en este caso es el link de internet a la imagen que está
            dado en la lista del archivo MainActivity.kt) y que lo coloque dentro (.into) de la vista (view)
            llamada con la id = ivHero (ubidada en el item_superhero.xml)*/
            Picasso.get().load(superhero.image).into(view.ivAvatar)
        }
    }

}
