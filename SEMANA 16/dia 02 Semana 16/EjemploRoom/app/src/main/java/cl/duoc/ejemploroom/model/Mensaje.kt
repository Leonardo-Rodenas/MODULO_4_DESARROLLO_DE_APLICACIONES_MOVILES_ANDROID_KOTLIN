package cl.duoc.ejemploroom.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mensaje_table")
class Mensaje(var contenido:String) {

    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
    override fun toString(): String {
        return "Mensaje(contenido='$contenido', id=$id)"
    }


}