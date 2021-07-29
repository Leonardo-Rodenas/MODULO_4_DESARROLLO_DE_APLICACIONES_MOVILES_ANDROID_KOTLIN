package com.example.bucadordeperros

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

/*(2) Despues de crear la data class se crea la Interfaz APIService, cuya función es crear el método
por el cual accedemos a nuestro servcio (es decir la API) */

interface APIService {

    //(3) Primero se pone el tipo de llamada (en este caso vamos a usar @GET porque es de una URL)
    @GET
    /*(4) Luego se crea la funcion que recibe como parámetros @Url url:String (esto es la parte cambiante
    de la llamada que se hace a la API, esto sería en el caso nuestro, partiendo con la URL original de
     la API --> https://dog.ceo/api/breed/hound/images, solamente desde la parte de /hound hacia adelante
     (/hound/images), ya que eso cambia a medida que hago las consultas, _todo lo demas siempre es fijo
     y no cambia a medida que me muevo en la API.   Luego se le indica al crear la función que va a
     recibir una respuesta... pero, ¿que tipo de respuesta?, una de la clase DogResponse creada antes.
     Cuando se haga esto, siempre fijarse de ir importando lo que diga retrofit 2, porque es la libreria
     con la que se trabaja actualmente*/

    /*OJO --> como esta funcion va a ser trabajada en segundo plano, es decir asincrona, se le pone el
    * suspend para que no interfiera con el hilo en primer plano*/
    suspend fun getDOgsByBreeds(@Url url:String):Response<DogResponse>
}