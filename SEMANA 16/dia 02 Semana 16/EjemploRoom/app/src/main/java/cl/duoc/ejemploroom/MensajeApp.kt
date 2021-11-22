package cl.duoc.ejemploroom

import android.app.Application
import cl.duoc.ejemploroom.repository.MensajeRepository

class MensajeApp: Application() {

    companion object{
        lateinit var repo: MensajeRepository
    }

    override fun onCreate() {
        super.onCreate()
        repo = MensajeRepository(this.applicationContext)
    }
}