package com.example.diario_aventura.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.diario_aventura.AdventureJournal
import com.example.diario_aventura.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CrConfiguration : AppCompatActivity() {
    private lateinit var txtRazaSelection: TextView
    private lateinit var txtTamanoSelection: TextView
    private lateinit var txtTrasfondoSelection: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pnt_cr_configuration)

        txtRazaSelection = findViewById(R.id.txt_race_selection)
        txtTamanoSelection = findViewById(R.id.txt_size_selection)
        txtTrasfondoSelection = findViewById(R.id.txt_background_selection)

        val selectedCharacterId = AdventureJournal.selectedCharacterId
        val charactersDB = (application as AdventureJournal).db             // Obtén una instancia de la base de datos
        val characterDao = charactersDB.characterDao()                      // Obtén una instancia del DAO de personajes
        val raceDao = charactersDB.raceDao()                                // Obtén una instancia del DAO de razas
        val backgroundDAO = charactersDB.backgroundDao()                    // Obtén una instancia del DAO de trasfondos

        // Usa un bloque de corrutina para realizar la operación de acceso a la base de datos
        CoroutineScope(Dispatchers.IO).launch {

            val character = characterDao.getCharacterById(selectedCharacterId)
            val race = raceDao.getRaceById(character.race)
            val background = backgroundDAO.getBackgroundById(character.background)

            withContext(Dispatchers.Main) {
                if (character != null) {
                    // Aquí puedes actualizar tus TextViews con la información del personaje
                    txtRazaSelection.text = "${race.name}" // Reemplaza "raza" con el nombre del campo correspondiente
                    txtTamanoSelection.text = "${race.size}" // Reemplaza "tamano" con el nombre del campo correspondiente
                    txtTrasfondoSelection.text = "${background.name}" // Reemplaza "trasfondo" con el nombre del campo correspondiente
                }
            }
        }
    }
}