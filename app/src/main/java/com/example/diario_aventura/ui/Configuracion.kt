package com.example.diario_aventura.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.example.diario_aventura.DataStoreManager
import com.example.diario_aventura.DiarioDeAventuras
import com.example.diario_aventura.R
import com.example.diario_aventura.db.entities.Personaje
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Configuracion : AppCompatActivity() {

    private lateinit var dspl_pj: Spinner
    private lateinit var personajes: List<Personaje>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pnt_configuracion)

        dspl_pj = findViewById(R.id.dspl_pj)

        CoroutineScope(Dispatchers.Main).launch {
            cargarPersonajesEnDesplegable()
        }

        dspl_pj.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val personajeSeleccionado = personajes[position]
                DiarioDeAventuras.personajeSeleccionadoId = personajeSeleccionado.id

                // Obtener el contexto de la actividad y guardar el personaje seleccionado en DataStore
                val context = this@Configuracion
                CoroutineScope(Dispatchers.IO).launch {
                    DataStoreManager.saveSelectedPersonajeId(context, personajeSeleccionado.id) }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
    }

    private suspend fun cargarPersonajesEnDesplegable() {
        // Cargar la lista de personajes desde la base de datos
        personajes = withContext(Dispatchers.IO) {
            (application as DiarioDeAventuras).db.personajeDao().getPersonajesOrderedByNombre()
        }

        val personajesNombres = personajes.map { it.nombre }

        val adapter = ArrayAdapter(this@Configuracion, android.R.layout.simple_spinner_item, personajesNombres)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        dspl_pj.adapter = adapter
    }
}