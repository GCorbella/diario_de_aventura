package com.example.diario_aventura.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.example.diario_aventura.DataStoreManager
import com.example.diario_aventura.AdventureJournal
import com.example.diario_aventura.R
import com.example.diario_aventura.db.entities.Character
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Configuration : AppCompatActivity() {

    private lateinit var dspl_cr: Spinner
    private lateinit var characters: List<Character>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pnt_configuration)

        dspl_cr = findViewById(R.id.dspl_cr)

        CoroutineScope(Dispatchers.Main).launch {
            loadCharactersInSpinner()
        }

        dspl_cr.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedCharacter = characters[position]
                AdventureJournal.selectedCharacterId = selectedCharacter.id

                // Obtener el contexto de la actividad y guardar el personaje seleccionado en DataStore
                val context = this@Configuration
                CoroutineScope(Dispatchers.IO).launch {
                    DataStoreManager.saveSelectedPersonajeId(context, selectedCharacter.id) }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        val btnNewCharacter = findViewById<Button>(R.id.btn_nw_cr)
        btnNewCharacter.setOnClickListener {
            val dialogView = layoutInflater.inflate(R.layout.dialog_new_cr, null)
            val etxtNewCrName = dialogView.findViewById<EditText>(R.id.etxt_new_cr_name)
            val btnCreateCr = dialogView.findViewById<Button>(R.id.btn_create_cr)

            val dialogBuilder = AlertDialog.Builder(this)
                .setView(dialogView)
                .setTitle("Nuevo Personaje")

            val dialog = dialogBuilder.create()

            btnCreateCr.setOnClickListener {
                val crName = etxtNewCrName.text.toString()
                if (crName.isNotBlank()) {
                    // Aquí puedes insertar el nuevo personaje en la base de datos
                    // y actualizar la lista de personajes en el desplegable
                    // También puedes cerrar el diálogo si el proceso se completa con éxito
                    CoroutineScope(Dispatchers.IO).launch {
                        (application as AdventureJournal).db.characterDao().createNewCharacter(crName)
                    }
                    CoroutineScope(Dispatchers.Main).launch {
                        loadCharactersInSpinner()
                    }
                    Toast.makeText(this, "Personaje creado", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                } else {
                    // Muestra un mensaje de error si el nombre está en blanco
                    Toast.makeText(this, "Ingresa un nombre válido", Toast.LENGTH_SHORT).show()
                }
            }

            dialog.show()
        }
    }

    override fun onResume() {
        super.onResume()

        dspl_cr = findViewById(R.id.dspl_cr)

        CoroutineScope(Dispatchers.Main).launch {
            loadCharactersInSpinner()
        }
    }

    private suspend fun loadCharactersInSpinner() {
        // Cargar la lista de personajes desde la base de datos
        characters = withContext(Dispatchers.IO) {
            (application as AdventureJournal).db.characterDao().getCharactersOrderedByName()
        }

        val charactersNames = characters.map { it.name }

        val adapter = ArrayAdapter(this@Configuration, android.R.layout.simple_spinner_item, charactersNames)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        dspl_cr.adapter = adapter
    }
}