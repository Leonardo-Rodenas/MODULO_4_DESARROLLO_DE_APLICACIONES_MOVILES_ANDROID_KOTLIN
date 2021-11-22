package com.example.repaso.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.repaso.dao.FavoritoDao
import com.example.repaso.dao.RazaDao
import com.example.repaso.model.FavoritoModel
import com.example.repaso.model.RazaModel

@Database(entities = [RazaModel::class, FavoritoModel::class], version = 1)
abstract class ProyectoDatabase: RoomDatabase() {

    abstract fun razaDao() : RazaDao
    abstract fun favoritoDao(): FavoritoDao

    companion object{
        @Volatile
        private var instance : ProyectoDatabase? = null

        fun getDatabase(context:Context): ProyectoDatabase
        {
            if(instance == null)
            {
                synchronized(this)
                {
                    instance = Room.databaseBuilder(context,ProyectoDatabase::class.java,"proyecto_db").build()
                }
            }
            return instance!!
        }
    }

}