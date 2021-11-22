package com.example.repaso.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorito")
class FavoritoModel(var foto:String) {

    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}