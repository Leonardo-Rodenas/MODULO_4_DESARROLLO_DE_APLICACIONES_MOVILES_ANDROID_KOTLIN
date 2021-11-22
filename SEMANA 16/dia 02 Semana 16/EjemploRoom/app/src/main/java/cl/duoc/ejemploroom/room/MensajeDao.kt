package cl.duoc.ejemploroom.room

import androidx.lifecycle.LiveData
import androidx.room.*
import cl.duoc.ejemploroom.model.Mensaje

@Dao
interface MensajeDao {

    @Insert
    suspend fun agregar(mensaje: Mensaje)

    @Delete
    suspend fun eliminar(mensaje: Mensaje)

    @Update
    fun actualizar(mensaje: Mensaje)

    @Query("select id,contenido from mensaje_table order by contenido")
    fun listar(): LiveData<List<Mensaje>>

    @Query("select id,contenido from mensaje_table where id = :id")
    fun buscar(id:Int): LiveData<Mensaje>
}