package cl.duoc.ejemploroom.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import cl.duoc.ejemploroom.model.Mensaje

@Database(entities = [Mensaje::class], version = 1)
abstract class MensajeDatabase:RoomDatabase() {

    abstract fun mensajeDao():MensajeDao

    companion object{
        @Volatile
        private var INSTANCE: MensajeDatabase? = null

        fun getDatabase(context: Context): MensajeDatabase
        {
            if(INSTANCE == null)
            {
                synchronized(this)
                {
                    INSTANCE = Room.databaseBuilder(context,
                        MensajeDatabase::class.java,
                        "mensaje_db").build()
                }
            }
            return INSTANCE!!
        }
    }

}