package com.example.repaso.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.repaso.model.FavoritoModel

@Dao
interface FavoritoDao {

    @Insert
    fun agregar(favoritoModel: FavoritoModel)

    @Delete
    fun eliminar(favoritoModel: FavoritoModel)

    @Query("select id, foto from favorito")
    fun listar(): LiveData<List<FavoritoModel>>
}