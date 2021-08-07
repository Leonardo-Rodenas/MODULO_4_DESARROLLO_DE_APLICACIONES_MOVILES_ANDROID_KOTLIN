package com.example.librosapp

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.librosapp.databinding.ActivityCorreoBinding
import kotlinx.android.synthetic.main.activity_correo.*

class CorreoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCorreoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCorreoBinding.inflate(layoutInflater)
        setContentView(binding.root)

//fijarse que sea el setnClickListner con las llaves, porque hay dos, el de parentesis no sirve acá
//https://www.youtube.com/watch?v=Y_nR4kZzw-w

        binding.botonenviar.setOnClickListener {

            val email = binding.introducircorreo.text.toString()
            val asunto = binding.introducirasunto.text.toString()
            val mensaje = binding.introducirmensaje.text.toString()

//si va a mandar el correo a mas de una persona se usa esto
            val direcciones =  email.split(",".toRegex()).toTypedArray()

/*Se crea el metodo que envia el correo:

En esta parte hay distintos ACTION_SEND, se diferencian en:

ACTION_SENDTO --> Para enviarlo a personas
ACTION_SEND --> Para enviarlo a personas + un archivo adjunto
ACTION_SEND_MULTIPLE -->  Para enviarlo a personas + multiples archivos adjuntos

* */

            val intent = Intent (Intent.ACTION_SENDTO).apply {
//escoje quien manda si hay mas de una app que pueda hacer esto
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL,direcciones)
                putExtra(Intent.EXTRA_SUBJECT,asunto)
                putExtra(Intent.EXTRA_TEXT,mensaje)
                //setType("message/rfc822")
            }
/*need this to prompts email client only --> It is frequently desirable, in sending
mail, to encapsulate another mail message. For this common operation, a special
Content-Type, "message", is defined. The primary subtype, message/rfc822, has no
required parameters in the Content- Type field.*
https://www.youtube.com/watch?v=kGkd1hrbnWY
https://docs.microsoft.com/en-us/previous-versions/office/developer/exchange-server-2010/aa493918(v=exchg.140)/ */

            try {
                startActivity(Intent.createChooser(intent,"Elije un cliente de Correo:"))
                //startActivity(intent)
            }catch (e : ActivityNotFoundException ){
                Toast.makeText(this, "Ha ocurrido un error o no tienes una app de correo instalada", Toast.LENGTH_SHORT).show()
            }

            /*if (intent.resolveActivity(packageManager)!=null){
                //startActivity(Intent.createChooser(intent,"Elije un cliente de Correo:"))
                startActivity(intent)
            }else{
                Toast.makeText(this, "No tienes una app instalada que pueda hacer eso", Toast.LENGTH_SHORT).show()
            }*/
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?):Boolean{
        menuInflater.inflate(R.menu.menuvolver, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id:Int = item.itemId

        if (id == R.id.volver) {
            Toast.makeText(this, "Volver a libros", Toast.LENGTH_SHORT).show()
            val i : Intent = Intent(this, MainActivity::class.java)
            startActivity(i)
        }
        return super.onOptionsItemSelected(item)
    }

}