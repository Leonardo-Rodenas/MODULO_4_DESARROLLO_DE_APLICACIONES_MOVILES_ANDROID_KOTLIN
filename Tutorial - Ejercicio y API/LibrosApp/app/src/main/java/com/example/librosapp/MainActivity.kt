package com.example.librosapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.SearchEvent
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.librosapp.databinding.ActivityMainBinding
import com.example.librosapp.datosdeAPI.googleBooks
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Query
import java.util.*

class MainActivity : AppCompatActivity() , SearchView.OnQueryTextListener { //o será la de androidx?

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.svlibros.setOnQueryTextListener(this) //this?, video object : SearchView.OnQueryTextListener

/*(10.1) Esta consulta con "el hobbit" se hace como prueba mientras se desarrolla la app*/
        consultaDelUsuario("el hobbit")
/*(12) Se hace una función para llamar al recyclerview, la cual se va a iniciar en el onCreate, llamada
initRecyclerview()*/

    }
/*(12.1) Esto viene de la función en onCreate, pero primero se le crea un adapter como a _todo
recyclerview [sigue en 12.2 - LibrosAdapter.kt] y se deja inconcluso por ahora*/
    private fun initRecyclerView(libros:googleBooks) {

/*(15) Acá se retorna a terminar esta función despues de varios pasos anteriores. Se hace el bindind
con el recycler view (rvLibros) y se le da el LinearLayoutManager (clase del sistema no creada por mi)
con el contexto de ser usada en esta actividad. Ahora hay que pasarle el adapter al rvLibros, por eso en la
línea 19 se crea la variable del mismo private lateinit var adapter: LibrosAdapter, pero al no
poder inicializarlo altiro porque no tenemos los  datos que vamos a usar, lo que se hace es crear
otra constante en la linea 20 val librosImage, del tipo Lista mutable de String (mutableListOf<String>,
esto es mutable, porque cada vez que le estamos poniendo algo al buscador, la lista va a cambiar
(mutar), y con ello requiere que se adapte a cada busqueda esa es la razon de que se haga mutable*/

    val librosAdapter = LibrosAdapter(libros)
    //adapter = librosAdapter
    binding.rvlibros.adapter = librosAdapter
    binding.rvlibros.layoutManager = LinearLayoutManager(this)
}
/*(11) que esta dentro de la funcion SearchByName, especificamente dentro de las corrutinas*/
/*(5) Acá hay una diferencia grande, por que el Nacho creo la instancia de retrofit en una clase aparte y
construyo como un object RetrofitInstance {bla bla bla}, pero segun el tutorial de los perritos, se puede
hacer dentro de la MainActivity, por mi parte no voy a intentar hacerla aparte en una instancia separada
y con el object, voy a intentar algo asi no como la hice en lo de buscador de perros, que lo entiendo
un poco mejor y vere si resulta.*/
/*(6)  Después de crear lo de antes (las datas class, el .xml, la interface APIService y googleBooks,tk)
se crea una instancia de un objeto retrofit. Esta instan
cia es la que va a contener la URL original, el
conversor de Json a el modelo de datos y toda la configuración para hacer las llamadas a internet. Para
esto se inicia creando un método privado con su nombre, el cual devuelve un tipo de variable Retrofit*/
/*(6.1) la instancia de retrofit es el telefono con el que nos comunicamos a la API*/

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.googleapis.com/books/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
/*(6.2) ACA PODRIA IR LO DE LO getRetrofit().create(APIService::class.java), PERO ESTA MAS ABAJO*/
/*(7) El objeto (creado arriba de estas lineas) crea dándole a Retrofit un constructor que lo cree (de hay
viene el Builder), luego una URL base (baseUrl) la cual es la parte que NO cambia de la llamada
que hacemos a la API (en la interfaz anterior seleccionabamos al parte que cambia, pues ahora
la que no lo hace. OJO = la baseUrl siempre tierne que terminar con una barra diagonal " / "). Despues
se la añade (.add) un convetidor del modelo de datos, el cual es GsonConverterFactory.create, que
resulta ser una de las librerias que se añaden al principio del proyecto. Finalmente se le dice
que con _todo lo que le pasó, construya (.build()) el retrofit*/
/*(8) Como la llamada anterior (del punto (7)) puede a veces ser un poco lenta en mandar su respuesta, esto puede
llegar a bloquear el hilo (thread) principal de la app, lo que se hace para solucionar esto es
trabajar asíncrono (síncrono seria en el hilo principal), para esto se utilizan la corrutinas
(otra de las librerías implementadas al inicio del proyecto). De este modo se crea una funcion
la cual se llama consultaDelUsuario y se le da de parámetro la consulta (query, del tipo String) que el
usuario va a realizar (notar que aún el usuario no hace la consulta, pero esta funcion se crea
antes, para atrapar de antemano esa consulta que creará el usuario), dicha consulta va a ser respondida
en una corrutina para no saturar el hilo principl de la app*/

    private fun consultaDelUsuario(query: String) {
/*(9) Acá se crea la corrutina de la siguiente manera: se le da una funcion CoroutineScope, la cual
lleva como parámetros un Dispatcher.IO (Dispatcher = Las corrutinas de Kotlin utilizan despachadores
para determinar qué subprocesos se utilizan para la ejecución de corrutinas. Dispatchers.IO: es un
despachador que está optimizado para realizar operaciones fuera del subproceso principal. Algunos
ejemplos incluyen usar el componente Room, leer desde archivos o escribir en ellos (el Json de la API)
y ejecutar operaciones de red (la API tambien supongo). Al final se le da la orden que se lanze (.launch)
con _todo lo que esa funcion va a contener en su interior*/
        CoroutineScope(Dispatchers.IO).launch {
/*(10) Dentro de la corrutina, _todo se va a desarrollar en un hilo secundario, de esta manera
la funcion implementada queda de la siguiente manera: constante que realiza un llamada (call)
al objeto Retrofti del punto (7), y que le dice que sea igual a el retrofit que llamó (getRetrofit),
creandole (create) ademas una interfaz (que la hicimos nosotros antes, la APIService, pero ni idea por
que lleva la parte de "::class.java"?) y que con eso obtenga los libros buscados por titulo en la query
(query = parámetro que va a consultar el usuario, atributo al inicio de la funcion).

Indicar que la forma en que se hace las busquedas la saqué de una mezcla entre esto:

https://developers.google.com/books/docs/v1/using --> Apartado Performing a search

y esto que escribio el Nacho:

interface RetroService {
    @GET("volumes")
    suspend fun buscarLibroPorTitulo(@Query ("q", encoded = true) lobuscado:String) : Response<googleBooks>
}
 */

            val call = getRetrofit().create(APIService::class.java).buscarLibroPorTitulo(query)
            val libro = call.body()!!
/*(11) Como estamos dentro de una corrutina, estas tienen por propiedad que _todo lo  que ve e interactua
con el usuario debe estar en el hilo principal, por esa razon al estar en segundo plano con la corrutina,
hay que salirse al primer plano y mostrar hay las imagenes y textos de los libros, esto se hace con
el runOnUiThread (eso hace que _todo lo que este dentro de sus llaves corra en el hilo principal)*/

            runOnUiThread {

                if (call.isSuccessful){
/*(11.1) Hasta acá esta completa la conexion con la API, el Log v de más abajo (que entrega la palabra
hola) tiene por funcionalidad ser un mensaje que se recibe al hacer la llamada, por lo que se puede
buscar en el Logcat (abajo en android studio) y ver el estado de la conexion al ejecutar la aplicacion*/

                Log.v("hola", call.body()!!.toString())
                initRecyclerView(libro)

                }else{
                    Toast.makeText(applicationContext, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
                }
                hideKeyboard()
                }
            }
/*(11.2) Acá se deja por ahora a medio hacer esta parte, y se salta a configurar el recyclerview, pues
se necesita implementar para poder continuar este apartado (busca (12) y contiua acá mismo desde el inicio del archivo*/
        }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()) {
            consultaDelUsuario(query.lowercase(Locale.getDefault()))
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.root.windowToken, 0)
    }


}
