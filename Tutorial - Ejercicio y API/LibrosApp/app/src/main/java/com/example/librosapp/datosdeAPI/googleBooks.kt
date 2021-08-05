package com.example.librosapp.datosdeAPI

/* (1) PREGUNTAR ok--> Despues de importar permisos de internet, las libreria de Gson converter, rerofit
y picasso y otra, en el manifest y de crear la interfaz grafica de la lista del recycler view en
res/layout/item_libros.xml lo que hice fue traerme la info de la API desde el sitio
https://www.googleapis.com/books/v1/volumes?q=search+terms, lo que tengo en duda es porqué de acá, de
volumenes? no deberia de traerme otra clase de lista con más datos, porque segun esta tiene 852 items,
que es mucho menos de los libros que realmente tiene google books -- Resp: Porque por defecto entrega
10 volumenes, pero se puede aumentar hasta un maximo de 40*/


data class googleBooks(
    val items: List<Item>,
    val kind: String,
    val totalItems: Int
)