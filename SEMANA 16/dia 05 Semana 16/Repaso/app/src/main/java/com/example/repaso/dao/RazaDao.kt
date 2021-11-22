package com.example.repaso.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.repaso.model.RazaModel

@Dao
interface RazaDao {

    @Insert(onConflict = REPLACE)
    fun agregar(razaModel: RazaModel)

    @Insert(onConflict = REPLACE)
    fun agregarAll(razas: List<RazaModel>)

    @Query("select id,descripcion from raza")
    fun listar() : LiveData<List<RazaModel>>

}