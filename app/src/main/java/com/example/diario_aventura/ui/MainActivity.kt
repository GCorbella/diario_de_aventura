package com.example.diario_aventura.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import androidx.room.Room
import com.example.diario_aventura.DiarioDeAventuras
import com.example.diario_aventura.R
import com.example.diario_aventura.db.PersonajesDB
import com.example.diario_aventura.db.entities.Personaje
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnConfiguracion = findViewById<ImageButton>(R.id.btn_conf)
        btnConfiguracion.setOnClickListener {
            val intent = Intent(this, Configuracion::class.java)
            startActivity(intent)
        }

        val btnFicha = findViewById<Button>(R.id.btn_ficha)
        btnFicha.setOnClickListener {
            val intent = Intent(this, ResumenFicha::class.java)
            startActivity(intent)
        }

        // Iniciar una corutina para obtener los personajes en un hilo de fondo
        CoroutineScope(Dispatchers.Main).launch {
            val personajes: List<Personaje> = withContext(Dispatchers.IO) {
                (application as DiarioDeAventuras).db.personajeDao().getPersonajesOrderedByNombre()
            }
            // Ahora puedes trabajar con la lista de personajes en el hilo principal
        }
    }
}