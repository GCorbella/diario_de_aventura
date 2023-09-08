package com.example.diario_aventura.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.example.diario_aventura.DataStoreManager
import com.example.diario_aventura.AdventureJournal
import com.example.diario_aventura.R
import com.example.diario_aventura.db.entities.Background
import com.example.diario_aventura.db.entities.Character
import com.example.diario_aventura.db.entities.Race
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Configuration : AppCompatActivity() {

    private lateinit var dsplCr: Spinner
    private lateinit var spinnerRace: Spinner
    private lateinit var spinnerBackground: Spinner
    private lateinit var characters: List<Character>
    private lateinit var races: List<Race>
    private lateinit var backgrounds: List<Background>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pnt_configuration)

        dsplCr = findViewById(R.id.dspl_cr)

        CoroutineScope(Dispatchers.Main).launch {
            loadCharactersInSpinner()
        }

        dsplCr.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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
            spinnerRace = dialogView.findViewById(R.id.spinner_race)
            spinnerBackground = dialogView.findViewById(R.id.spinner_background)
            val btnCreateCr = dialogView.findViewById<Button>(R.id.btn_create_cr)

            val dialogBuilder = AlertDialog.Builder(this)
                .setView(dialogView)
                .setTitle("Nuevo Personaje")

            val dialog = dialogBuilder.create()

            CoroutineScope(Dispatchers.Main).launch {
                loadRacesInSpinner()
                loadBackgroundsInSpinner()
            }

            btnCreateCr.setOnClickListener {
                val crName = etxtNewCrName.text.toString()
                val selectedRace = races[spinnerRace.selectedItemPosition]
                val selectedBackground = backgrounds[spinnerBackground.selectedItemPosition]

                if (crName.isNotBlank()) {
                    // Aquí puedes insertar el nuevo personaje en la base de datos
                    // y actualizar la lista de personajes en el desplegable
                    // También puedes cerrar el diálogo si el proceso se completa con éxito
                    CoroutineScope(Dispatchers.IO).launch {
                        (application as AdventureJournal).db.characterDao().createNewCharacter(crName,selectedRace.id,selectedBackground.id)
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

        val btnDltCharacter = findViewById<Button>(R.id.btn_dlt_cr)
        btnDltCharacter.setOnClickListener {
            val dialogView = layoutInflater.inflate(R.layout.dialog_dlt_cr, null)
            val txtDltCr = dialogView.findViewById<TextView>(R.id.txt_dltcr)
            val btnDltCrAffirmative = dialogView.findViewById<Button>(R.id.btn_dltcr_affirmative)
            val btnDltCrNegative = dialogView.findViewById<Button>(R.id.btn_dltcr_negative)

            val dialogBuilder = AlertDialog.Builder(this)
                .setView(dialogView)
                .setTitle("Eliminar Personaje")

            val dialog = dialogBuilder.create()

            // Obtener el personaje seleccionado actualmente en el Spinner
            val selectedCharacterPosition = dsplCr.selectedItemPosition
            if (selectedCharacterPosition != AdapterView.INVALID_POSITION) {
                val selectedCharacter = characters[selectedCharacterPosition]
                txtDltCr.text = getString(R.string.delete_character, selectedCharacter.name)
            }

            btnDltCrAffirmative.setOnClickListener {
                // Obtener el personaje seleccionado actualmente en el Spinner
                val selectedCharacterPosition = dsplCr.selectedItemPosition
                if (selectedCharacterPosition != AdapterView.INVALID_POSITION) {
                    val selectedCharacter = characters[selectedCharacterPosition]

                    // Eliminar el personaje de la base de datos
                    CoroutineScope(Dispatchers.IO).launch {
                        (application as AdventureJournal).db.characterDao().deleteCharacter(selectedCharacter)
                    }

                    // Actualizar la lista de personajes en el Spinner
                    CoroutineScope(Dispatchers.Main).launch {
                        loadCharactersInSpinner()
                    }

                    // Actualizar la variable global AdventureJournal.selectedCharacterId
                    AdventureJournal.selectedCharacterId = -1
                }
                Toast.makeText(this, "Personaje eliminado", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }

            btnDltCrNegative.setOnClickListener {
                dialog.dismiss()
            }

            dialog.show()
        }
    }

    override fun onResume() {
        super.onResume()

        dsplCr = findViewById(R.id.dspl_cr)

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
        dsplCr.adapter = adapter
    }

    private suspend fun loadRacesInSpinner() {
        // Cargar la lista de razas desde la base de datos
        races = withContext(Dispatchers.IO) {
            (application as AdventureJournal).db.raceDao().getRacesOrderedById()
        }

        val racesNames = races.map { it.name }

        val adapter = ArrayAdapter(this@Configuration, android.R.layout.simple_spinner_item, racesNames)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerRace.adapter = adapter
    }

    private suspend fun loadBackgroundsInSpinner() {
        // Cargar la lista de trasfondos desde la base de datos
        backgrounds = withContext(Dispatchers.IO) {
            (application as AdventureJournal).db.backgroundDao().getBackgroundsOrderedByName()
        }

        val backgroundsNames = backgrounds.map { it.name }

        val adapter = ArrayAdapter(this@Configuration, android.R.layout.simple_spinner_item, backgroundsNames)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerBackground.adapter = adapter
    }
}