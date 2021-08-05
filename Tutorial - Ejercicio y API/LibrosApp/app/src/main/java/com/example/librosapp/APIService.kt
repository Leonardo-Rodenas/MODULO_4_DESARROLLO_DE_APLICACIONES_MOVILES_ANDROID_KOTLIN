package com.example.librosapp

import com.example.librosapp.datosdeAPI.googleBooks
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface APIService {

/*(2) Siguiendo el orden del buscador de perritos, despues de las data class y los .xml de las
card view para implementar en la recycler, lo que se hace es crear una Interfaz (logo verde con
una I en su interior, arriba en la barra de pestañas del android studio.

La funcion de esta nueva interfaz es crear el método con el que vamos a acceder al servicio de la
API de google*/
/*(3) Se parte con el método de llamada a la API (en este caso usamos @GET porque trabajamos con una
* Url*/
/*(4) Luego se crea la funcion que recibe como parámetros @Url url:String (esto es la parte cambiante
de la llamada que se hace a la API, esto sería en el caso nuestro, partiendo con la URL original de
la API --> https://www.googleapis.com/books/v1/volumes?q=search+terms, solamente desde la parte de /volumes?
hacia adelante, ya que eso cambia a medida que hago las consultas, _todo lo demas siempre es fijoy no
cambia a medida que me muevo en la API.   Luego se le indica al crear la función que va a recibir una
respuesta... pero, ¿que tipo de respuesta?, una de la clase googleBooks creada antes. Cuando se haga
esto, siempre fijarse de ir importando lo que diga retrofit 2, porque es la libreria con la que se
trabaja actualmente

*OJO --> como esta funcion va a ser trabajada en segundo plano, es decir asincrona, se le pone el
suspend (que es parte de la corrutinas, importadas en las librerias al principio del proyecto) para
que no interfiera con el hilo en primer plano*/
/* (4.1) el get recibe volumes que es volumes de la direccion web, la "q" es la misma direccion (sin el
el =, lo agrega retrofit, y hace referencia a query) y encoded = true, da el formato de enlace web (sin
espacio entre las palbaras (lo llena con un +)*/
/*(4.2) lo buscado = lo que ponga el usuario en el searchView*/

    @GET("volumes")
    suspend fun buscarLibroPorTitulo (@Query ("q", encoded = true) lobuscado:String): Response<googleBooks>
//COMPARAR URL QUE ESTOY FORMANDO CON --> https://www.googleapis.com/books/v1/volumes?q="el+hobbit" (el hobbit = lobuscado:String)

}