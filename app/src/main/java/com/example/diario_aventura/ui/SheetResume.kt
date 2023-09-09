package com.example.diario_aventura.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.example.diario_aventura.AdventureJournal
import com.example.diario_aventura.R
import com.example.diario_aventura.db.CharactersDB
import com.example.diario_aventura.db.entities.Character
import com.example.diario_aventura.db.entities.Race
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SheetResume : AppCompatActivity() {

    private lateinit var character: Character
    private lateinit var characterRace: Race

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pnt_sheet_resume)

        loadCharacter()
        refreshAndCalculate()

        //Botones
        val btnSkills = findViewById<Button>(R.id.btn_skills)
        btnSkills.setOnClickListener {
            val intent = Intent(this, Skills::class.java)
            startActivity(intent)
        }

        val btnCombat = findViewById<Button>(R.id.btn_combat)
        btnCombat.setOnClickListener {
            val intent = Intent(this, Combat::class.java)
            startActivity(intent)
        }

        val btnInventory = findViewById<Button>(R.id.btn_inventory)
        btnInventory.setOnClickListener {
            val intent = Intent(this, Inventory::class.java)
            startActivity(intent)
        }

        val btnCrConfiguration = findViewById<ImageButton>(R.id.btn_crconf)
        btnCrConfiguration.setOnClickListener {
            val intent = Intent(this, CrConfiguration::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        loadCharacter()
        // Actualizar los valores cada vez que se llega a la pantalla
        refreshAndCalculate()
    }

    private fun refreshAndCalculate() {
        CoroutineScope(Dispatchers.IO).launch {

            // Actualizar los TextViews con los valores del personaje
            runOnUiThread {
                findViewById<TextView>(R.id.txt_val_str).text = (character.strength + characterRace.strength).toString()
                findViewById<TextView>(R.id.txt_val_dex).text = (character.dexterity + characterRace.dexterity).toString()
                findViewById<TextView>(R.id.txt_val_con).text = (character.constitution + characterRace.constitution).toString()
                findViewById<TextView>(R.id.txt_val_int).text = (character.intelligence + characterRace.intelligence).toString()
                findViewById<TextView>(R.id.txt_val_wis).text = (character.wisdom + characterRace.wisdom).toString()
                findViewById<TextView>(R.id.txt_val_cha).text = (character.charisma + characterRace.charisma).toString()

                // Realizar cálculos y actualizar los modificadores
                calculateAndSetModifiers()
            }
        }
    }

    // Función para calcular y establecer los modificadores
    private fun calculateAndSetModifiers() {
        val txtAbilitiesValArray = arrayOf<TextView>(
            findViewById(R.id.txt_val_str),
            findViewById(R.id.txt_val_dex),
            findViewById(R.id.txt_val_con),
            findViewById(R.id.txt_val_int),
            findViewById(R.id.txt_val_wis),
            findViewById(R.id.txt_val_cha)
        )

        val txtAbilitiesModArray = arrayOf<TextView>(
            findViewById(R.id.txt_mod_str),
            findViewById(R.id.txt_mod_dex),
            findViewById(R.id.txt_mod_con),
            findViewById(R.id.txt_mod_int),
            findViewById(R.id.txt_mod_wis),
            findViewById(R.id.txt_mod_cha)
        )

        var i = 0
        for (txtMod in txtAbilitiesModArray) {
            if (((txtAbilitiesValArray[i].text.toString().toInt() - 10) / 2) >= 0) {
                txtMod.text = "+" + ((txtAbilitiesValArray[i].text.toString().toInt() - 10) / 2).toString()
                i++
            } else {
                txtMod.text = ((txtAbilitiesValArray[i].text.toString().toInt() - 10) / 2).toString()
                i++
            }
            if (i == 6) {
                i = 0
            }
        }
    }

    private fun loadCharacter() {
        val characterId = AdventureJournal.selectedCharacterId
        val characterDao = (application as AdventureJournal).db.characterDao()
        val raceDao = (application as AdventureJournal).db.raceDao()

        CoroutineScope(Dispatchers.IO).launch {
            character = characterDao.getCharacterById(characterId)
            characterRace = raceDao.getRaceById(character.race)
        }
    }
}