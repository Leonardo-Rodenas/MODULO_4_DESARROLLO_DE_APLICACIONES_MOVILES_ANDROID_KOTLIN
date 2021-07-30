package com.example.listadesuperheroes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    /*(2) Acá se crea una variable que es la lista de superheroes y se instancia. Esto significa que
    dentro de la lista se crea uno o varios objetos, que van a tener la info de la data class
    SuperHero.kt, y que esos mismos objetos al instanciarse, se les va a tener que llenar los valores
    solicitados*/

    val superheros = listOf(
        SuperHero("Spiderman", "Peter Parker", "Marvel", "https://e7.pngegg.com/pngimages/147/367/png-clipart-spider-man-child-male-spider-man-child-heroes.png"),
        SuperHero("Batman", "Bruce Wayne", "DC", "https://assets.stickpng.com/images/580b57fbd9996e24bc43c01d.png"),
        SuperHero("Wolverine", "James Howlett", "Marvel", "https://static.wikia.nocookie.net/death-battle-fanon-wiki-en-espanol/images/5/5d/Wolverine.png/revision/latest?cb=20190217160514&path-prefix=es"),
        SuperHero("Superman", "Clark Kent", "DC", "https://i.pinimg.com/originals/d3/65/85/d36585236340eef29506749b4cb29308.jpg"),
        SuperHero("Ironman", "Tony Stark", "Marvel", "https://www.kindpng.com/picc/m/366-3663870_iron-man-png-iron-man-caricatura-png-iron.png"),
        SuperHero("Wonder Woman", "Diana Prince", "DC", "https://static.wikia.nocookie.net/personajes-de-ficcion-database/images/d/dc/Wonder_Woman_render.png/revision/latest?cb=20201112215419&path-prefix=es"),
        SuperHero("Black Widow", "Natasha Romanov", "Marvel", "https://static.wikia.nocookie.net/doblaje/images/7/75/Black_widow.png/revision/latest?cb=20190131192241&path-prefix=es"),
        SuperHero("Raven", "Rachel Roth", "DC", "https://static.wikia.nocookie.net/death-battle-en-espanol/images/6/66/Raven.png/revision/latest?cb=20180215190347&path-prefix=es"),
        SuperHero("Scarlet Witch", "Wanda Maximoff", "Marvel", "https://i.pinimg.com/originals/cb/00/99/cb0099ee3077a70e368f109a1e86ad5a.png"),
        SuperHero("Flash", "Barry Allen", "DC", "https://www.nicepng.com/png/detail/2-26725_a-the-flash-character-barry-allen-flash-comics.png"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /* COMO DATO EXTRA: Al ser superheros un listado, al anotarlo como abajo indica lo siguiente:
        * [número] = indica la posición en la que me encuentro, 0 es igual a la primera posición o línea
        * .realName = es el atributo que busco en esa primera línea
        * Por lo tanto, lo que estoy buscando acá en el ejempo es en la primera línea, el nombre real
        * de ese héroe, lo que nos lleva a el dato "Peter Parker"
        superheros[0].realName*/

        //(8) la funcion initRecycler se llama en el onCreate para que apenas inicie la actividad, comienze
        //mostrando automaticamente el método de la recycler View
        initRecycler ()

    }

    //(3) En el siguiente paso creo el .xml item_superhero para hacer las vistas que voy a inflar con
    //los datos que llené anteriormente en el listado (las trajetitas básicas a llenar con info).
    //----------------------------------------------------------------------------------------------

    //(7) Esta parte es la que conecta _todo lo anterior a la recycler view en la activity_main.xml
    fun initRecycler (){
        rvSuperHero.layoutManager = LinearLayoutManager(this)
        val adapter = HeroAdapter(superheros)
        rvSuperHero.adapter = adapter
    }

    //(9) Luego de esto lo que hay que hacer es en el manifest dar los permisos para la conexión a internet
    //de otro modo, como las imagenes son direccones web, no se van a cargar si no hay permiso para ello.
}