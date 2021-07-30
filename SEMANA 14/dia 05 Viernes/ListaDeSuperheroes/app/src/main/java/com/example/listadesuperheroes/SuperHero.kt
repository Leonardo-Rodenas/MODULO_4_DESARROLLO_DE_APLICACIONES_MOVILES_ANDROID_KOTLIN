package com.example.listadesuperheroes
//(1) Acá, despues de crear la MainActivity con la recyclew view, creo la data class que va a tener
// la info que quiero mostrar en la lista de superheroes (a esta clase borrarle las llaves y agregarle
//entre paréntesis los atribitos que voy a mostrar)

data class SuperHero (
    val superHeroName:String,
    val realName:String,
    val publisher:String,
    val image:String,
    )
