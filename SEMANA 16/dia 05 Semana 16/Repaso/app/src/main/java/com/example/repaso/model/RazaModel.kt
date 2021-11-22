package com.example.repaso.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "raza")
class RazaModel(id:Int,var descripcion:String) {

    @PrimaryKey(autoGenerate = false)
    var id:Int = id
}