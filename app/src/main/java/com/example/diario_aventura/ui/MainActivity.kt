package com.example.diario_aventura.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.example.diario_aventura.DataStoreManager
import com.example.diario_aventura.DiarioDeAventuras
import com.example.diario_aventura.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Obtener el personaje seleccionado desde DataStore y asignarlo a la variable global
        val context = this
        CoroutineScope(Dispatchers.IO).launch {
            val selectedPersonajeId: Int? = DataStoreManager.getSelectedPersonajeId(context).first()

            // Si el valor es diferente de null y de -1, lo asignamos a la variable global
            if (selectedPersonajeId != null && selectedPersonajeId != -1) {
                DiarioDeAventuras.personajeSeleccionadoId = selectedPersonajeId
            }
        }

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

        updatePersonajeName()
    }

    override fun onResume() {
        super.onResume()
        updatePersonajeName()
    }

    private fun updatePersonajeName() {
        val context = this
        CoroutineScope(Dispatchers.IO).launch {
            val selectedPersonajeId: Int? = DataStoreManager.getSelectedPersonajeId(context).first()

            if (selectedPersonajeId != null && selectedPersonajeId != -1) {
                val db = (application as DiarioDeAventuras).db
                val personajeSeleccionado = db.personajeDao().getPersonajeById(selectedPersonajeId)

                runOnUiThread {
                    val txtDiarioDe = findViewById<TextView>(R.id.txt_diarioDe)
                    val textoDiario = getString(R.string.diario_de_personaje, personajeSeleccionado.nombre)
                    txtDiarioDe.text = textoDiario
                }
            } else {
                runOnUiThread {
                    val txtDiarioDe = findViewById<TextView>(R.id.txt_diarioDe)
                    val textoDiario = getString(R.string.diario_de_personaje, "xxxxxxxx")
                    txtDiarioDe.text = textoDiario
                }
            }
        }
    }
}