package cl.duoc.ejemploroom.repository

import android.content.Context
import androidx.lifecycle.LiveData
import cl.duoc.ejemploroom.model.Mensaje
import cl.duoc.ejemploroom.room.MensajeDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MensajeRepository(var context: Context) {

    var mDB = MensajeDatabase.getDatabase(context)

    fun agregar(mensaje:Mensaje)
    {
        CoroutineScope(IO).launch {
            mDB.mensajeDao().agregar(mensaje)
        }

    }

    fun eliminar(mensaje: Mensaje)
    {
        CoroutineScope(IO).launch {
            mDB.mensajeDao().eliminar(mensaje)
        }
    }

    fun listar() : LiveData<List<Mensaje>>
    {
        return mDB.mensajeDao().listar()
    }
}