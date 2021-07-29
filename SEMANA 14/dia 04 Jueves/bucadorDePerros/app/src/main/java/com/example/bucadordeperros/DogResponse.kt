package com.example.bucadordeperros

import com.google.gson.annotations.SerializedName

/*(1) Despues de importar los permisos de internet en el Manifest y de crear la interfaz de usuario
en el acrivity_main.xml (con el componente searchview y el recyclerview), se crea la data class. es
importante borrar las llaves que se generan con la clase y reemplazarlas con paréntesis, ademas de
agregar la palabra data adelante de class (eso convierte la class en data class)*/

/*Notar también que acá se reciben los valores que van a venir de la API, por esta razon hay que crear
las variables que tiene el tipo de llamada a la API (https://dog.ceo/api/breed/hound/images --> en este
caso, esta API de perros por raza). Las variables en un inicio tienen que tener el mismo nombre que los
componentes de la llamada que se va a hacer, por esta razón se usa "status" y "message" en un inicio, pero
 estos si se les pone aelante el comando @SerializedName (value: "nombrellamada"), lo que va entre paretesis
 debe ser igual al nombre de la llamada, pero lo que va afuera puedo cambiarlo por algun nombre de mi
 preferencia mas fácil de usar o de recordar*/

data class DogResponse (
    @SerializedName("status") var status:String,
    @SerializedName("message") var images:List<String>
)