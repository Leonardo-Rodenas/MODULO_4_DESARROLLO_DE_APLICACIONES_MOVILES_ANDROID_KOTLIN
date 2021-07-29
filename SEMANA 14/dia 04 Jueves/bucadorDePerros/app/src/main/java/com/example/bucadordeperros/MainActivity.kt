package com.example.bucadordeperros

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bucadordeperros.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

/*(18) Desde abajo del archivo, al inicio del mismo :D. Lo que se hace para establecer el searchView
es al lado de AppComparActivity(), agregar esto ", SearchView.OnQueryTextListener" e implementar los dos
métodos que va pedir al salir error (los métodos salen abajo del _todo, así va a tocar volver al fondo
del archivo jajajajaja)*/

class MainActivity : AppCompatActivity() , SearchView.OnQueryTextListener{

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: DogAdapter
    private val dogImages = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main) --> se elimina al trabajar con viewBinding
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)
        /*(20) Aca se hace le binding del searchView*/
       binding.svDogs.setOnQueryTextListener(this)
        /*(12) Se hace una funcion para llamar al recyclerview, la cual se va a iniciar en el onCreate,
        llamada initRecyclerView()*/
        initRecyclerView()
    }
    /*(12.1) Esto viene de la función en onCreate, pero primero se le crea un adapter como a _todo
    recyclerview [sigue en 12.2 - DogAdapter.kt] y se deja inconcluso por ahora*/
    private fun initRecyclerView() {
    /*(15) Acá se retorna a terminar esta función despues de varios pasos anteriores. Se hace el bindind
    con el recycler view (rvDogs) y se le da el LinearLayoutManager (clase del sistema no creada por mi)
    con el contexto de ser usada en esta actividad. Ahora hay que pasarle el adapter al rvDogs, por eso en la
    línea 18 se crea la variable del mismo     private lateinit var adapter: DogAdapter, pero al no
    poder inicializarlo altiro porque no tenemos las imagenes que vamos a usar, lo que se hace es crear
    otra constante en la linea 19 val dogImage, del tipo Lista mutable de String (mutableListOf<String>,
    esto es mutable, porque cada vez que le estamos poniendo algo al buscador, la lista va a cambiar
    (mutar), y con ello requiere que se adapte a cada busqueda esa es la razon de que se haga mutable,
    así _todo esto queda como en la línea 52 a 54*/

        binding.rvDogs.layoutManager = LinearLayoutManager(this)
        adapter = DogAdapter(dogImages)
        binding.rvDogs.adapter = adapter

        /*(16) Despues de esto lo que se hace es empezar a meterle imagenes, lo cual se hace en el punto
        16.1 que esta dentro de la funcion SearchByName, especificamenet dentro de las corrutinas*/
    }

    /*(5) 13:16 del video de CursoKotlin.com (de Aris) = Después de crear lo de antes. se crea una
    instancia de un objeto retrofit. Esta instancia es la que va a contener la URL original, el
    conversor de Json a el modelo de datos y toda la configuración para hacer las llamadas a internet*/

    /*(6) Primero se crea un método privado con su nombre, el cual devuelve un tipo de variable
    Retrofit*/
    private fun getRetrofit():Retrofit{
    /*(7) El objeto retrofit anterior se crea dándole a Retrofit un constructor que lo cree (de hay
    viene el Builder), luego una URL base (baseUrl) la cual es la parte que no cambia de la llamada
    que hacemos a la API (en la interfaz anterior seleccionabamos al parte que cambia, pues ahora
    la que no lo hace. OJO = la baseUrl siempre tierne que terminar con una barra diagonal " / "). Despues
    se la añade (.add) un convetidor del modelo de datos, el cual es JsonConverterFactory.create, que
    resulta ser una de las librerias que se añaden al principio del proyecto. Finalmente se le dice
    que con _todo lo que le pasó, construya (.build()) el retrofit*/
        return Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/breed/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /*(8) Como la llamada anterior puede a veces ser un poco lenta en mandar su respuesta, esto puede
    llegar a bloquear el hilo (thread) principal de la app, lo que se hace para solucionar esto es
    trabajar asíncrono (síncrono seria en el hilo principal), para esto se utilizan la corrutinas
    (otra de las librerías implementadas al inicio del proyecto). De este modo se crea una funcion
     la cual se llama searchByName y se le da de parámetro la consulta (query, del tipo String) que el
     usuario va a realizar (notar que aún el usuario no hace la consulta, pero esta funcion se crea
     antes, para atrapar de antemano esa consulta que creará el usuario), dicha consulta va a ser respondida
     en una corrutina para no saturar el hilo principl de la app*/

    private fun searchByName(query:String){

    /*(9) Acá se crea la corrutina de la siguiente manera: se le da una funcion CoroutineScope, la cual
    lleva como parámetros un Dispatcher.IO (Dispatcher = Las corrutinas de Kotlin utilizan despachadores
    para determinar qué subprocesos se utilizan para la ejecución de corrutinas. Dispatchers.IO: es un
    despachador que está optimizado para realizar operaciones fuera del subproceso principal. Algunos
    ejemplos incluyen usar el componente Room, leer desde archivos o escribir en ellos (el Json de la API)
    y ejecutar operaciones de red (la API tambien supongo). Al final se le da la orden que se lanze (.launch)
    con _todo lo que esa funcion va a contener en su interior*/

        CoroutineScope(Dispatchers.IO).launch {
            /* ****************************************************************************************** */
            /*(10) Dentro de la corrutina, _todo se va a desarrollar en un hilo secundario, de esta manera
            la funcion implementada queda de la siguiente manera: constante que realiza un llamada (call)
            al objeto Retrofti del punto (7), y que le dice que sea igual a el retrofit que llamó (getRetrofit),
            creandole (create) ademas una interfaz (que la hicimos nosotros antes, la APIService, pero ni idea por
            que lleva la parte de "::class.java"?) y que con eso obtenga los perros por raza en la query
            (notar que al pasar el Retofit del punto 7 le doy la mitad de la Url, y acá se temina de
            completar para realizar la consulta. Esta misma leva un $query en la Url, para indicar que es
            variable y que el usuario puede escribir cualquier raza que se le ocurra)

            ACA DETECTO UN CAMBIO EN LAS LINEAS DEL VIDEO Y LAS MIAS --> min 17:48

            *********************************************************************************************** */

            val call = getRetrofit().create(APIService::class.java).getDOgsByBreeds("$query/images")
            /*Esto no termine de entenderlo, creo que body es donde esta la dogResponse anterior, pero que
            a mi no me sale en el código*/
            val puppies = call.body()
            /*(16.1) Como estamos dentro de una corrutina, estas tienen por propiedad que _todo lo
            que ve e interactua con el usuario debe estar en el hilo principal, por esa razon al estar
            en segundo plano con la corrutina, hay que salirse al primer plano y mostrar en esa parte de
            las imagenes de los perritos, esto se hace con con el runOnUiThread (eso hace que _todo lo que este
            dentro de sus llaves corra en el hilo principal)*/
            runOnUiThread {
                /*(16.2) Este if es el encargado de pinta en el hilo principal, ya sea las imagenes
                o un Toast de error*/
                if(call.isSuccessful){
                    //(16.2.1) Si la peticion funciona, hacer lo del if
                    /*Crear constante images, que trae las imagenes desde puppies. Esto puede traer
                    como problema que puppies puede ser nulo (buscar una raza que no existe o cualquier
                    tontera no relacionada a la app), por eso para solucionarlo lo que se hace es ponerle
                    un signo ? a puppies (puppies?), con eso digo que puppies puede recibir un listado
                    de Strings o nada y seguirlo con el operador "Elvis" (?: = el tupé de Elvis, como un
                    emoji XD) el cual indica que si puppies? es nulo (null) lo que se va a obtener es
                    una lista vacia (emptyList()) --> a kotlin parece no gustarle las cosas nulas*/
                    val images = puppies?.images ?: emptyList()
                    /*(16.2.2) Ahora se le da a la lista los parámetros para que se actualize tras las busquedas
                    lo que lleva a la constante dogIMages (la lista mutable) se limpie (.clear), añada
                    todas las imagenes (.addAll) y notifique al adaptador que han existido cambios en
                    la lista de imagenes*/
                    dogImages.clear()
                    dogImages.addAll(images)
                    adapter.notifyDataSetChanged()
                }else{
                    //(16.2.3) Si falla la petición (no internet, servidor caído, etc) mostrar la funcion showError con el Toast
                    showError()
                    /*(17) Ahora se pasa a hacer que el searchView (el buscador en la main_activity
                    sea funcional, para esto saltar en este mismo archivo al inicio y buscar el punto (18)*/
                }
            }
            /*(11) Acá se termina lo de las Api y corrutinas, dicho así ahora se pasa a lo del recyclerview*/
        }
    }

    private fun showError() {
        Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }

    /*(19) Estos métodos salen por generar el SearchView.OnQueryTextListener de la linea 20, lo que hace
    cada uno de ellos es lo siguiente:

    override fun onQueryTextSubmit --> cuando se ingrese la palabra, y le de a buscar, se llamará este
    método (por eso recibe como atributo una String, es decir una palabra). Como este es el importante
    y no la otra función, se va a explicar dentro de la misma (no va a tener número, solo ##).

    override fun onQueryTextChange --> cada vez que el usuario meta una letra o genera algun cambio
    en el buscador, va a llamar este método, pero ahora no nos interesa, pero es obligacion meterlo.
    Así que para que no estorbe al ser un Boolean, solo se soluciona dandole un retorno true

    */
    override fun onQueryTextSubmit(query: String?): Boolean {
        /*## acá hay un if que indica que si la consulta del usuario no es nula o esta vacia (por eso
        el " ! " al inicio y el metodo isNullOrEmpty), llame a la funcion searchByName, la que recibe
        como parámetros la consulta (query) en minusculas (.lowercase(Locale.getDefault()))), esto se debe
        a que no sabemos como quiere la API que realizemos las consultas, por eso para asegurarnos, le
        pasamos _todo en minusculas --> en esta parte del video min 28:57 usa una función distinta llamada
        .toLowerCase que al dia de hoy (28 de Julio de 2021) está deprecada*/
        if (!query.isNullOrEmpty()) {
            searchByName(query.lowercase(Locale.getDefault()))
        }
        //## Como es booleano y con parametros, debe retornar un valor, en este caso true.
        //Ahora subir a línea 33 para hacer el bindind del searchView, punto (20)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }
}
