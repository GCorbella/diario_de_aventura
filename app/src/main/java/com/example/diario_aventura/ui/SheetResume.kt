package com.example.diario_aventura.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import com.example.diario_aventura.AdventureJournal
import com.example.diario_aventura.R
import com.example.diario_aventura.db.entities.Character
import com.example.diario_aventura.db.entities.Race
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SheetResume : AppCompatActivity() {

    private lateinit var character: Character
    private lateinit var characterRace: Race

    private val editTextArray = mutableListOf<EditText>()
    private val saveValueArray = mutableListOf<TextView>()
    private lateinit var fortitudeElements: Array<View>
    private lateinit var reflexesElements: Array<View>
    private lateinit var willElements: Array<View>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pnt_sheet_resume)

        loadCharacter()
        refreshAndCalculate()

        //Botones para controlar Vida, Mana, Suerte
        val btnMHealth = findViewById<ImageButton>(R.id.btn_mHealth)
        btnMHealth.setOnClickListener {
            character.cHealth += 1
            refreshAndCalculate()
        }
        val btnLHealth = findViewById<ImageButton>(R.id.btn_lHealth)
        btnLHealth.setOnClickListener {
            character.cHealth -= 1
            refreshAndCalculate()
        }
        val btnMMana = findViewById<ImageButton>(R.id.btn_mMana)
        btnMMana.setOnClickListener {
            character.cMana += 1
            refreshAndCalculate()
        }
        val btnLMana = findViewById<ImageButton>(R.id.btn_lMana)
        btnLMana.setOnClickListener {
            character.cMana -= 1
            refreshAndCalculate()
        }
        val btnMLuck = findViewById<ImageButton>(R.id.btn_mLuck)
        btnMLuck.setOnClickListener {
            character.cLuck += 1
            refreshAndCalculate()
        }
        val btnLLuck = findViewById<ImageButton>(R.id.btn_lLuck)
        btnLLuck.setOnClickListener {
            character.cLuck -= 1
            refreshAndCalculate()
        }

        editTextArray.addAll(
            listOf<EditText>(
                    findViewById(R.id.etxt_u_fortitude),
                    findViewById(R.id.etxt_u_reflexes),
                    findViewById(R.id.etxt_u_will)
            )
        )

        saveValueArray.addAll(
            listOf<TextView>(
                findViewById(R.id.txt_val_for),
                findViewById(R.id.txt_val_ref),
                findViewById(R.id.txt_val_will)
            )
        )
        val BtnEditUses = findViewById<ImageButton>(R.id.btn_edituses_sav)

        BtnEditUses.setOnClickListener {
            for (eTxt in editTextArray) {
                eTxt.isEnabled = !eTxt.isEnabled
            }

            if (!editTextArray[0].isEnabled) {
                val characterDao = (application as AdventureJournal).db.characterDao()

                CoroutineScope(Dispatchers.IO).launch {

                    character?.let {
                        // Actualiza las propiedades del personaje utilizando las referencias previamente obtenidas
                        it.uFortitude = editTextArray[0].text.toString().toInt()
                        it.uReflexes = editTextArray[1].text.toString().toInt()
                        it.uWill = editTextArray[2].text.toString().toInt()

                        characterDao.updateCharacter(it)
                    }
                }
                refreshAndCalculate()
            }
        }

        // Inicializar el array para fortitude
        fortitudeElements = arrayOf(
            findViewById(R.id.etxt_u_fortitude),
            findViewById(R.id.txt_fortitude),
            findViewById(R.id.txt_val_for)
        )

        // Inicializar el array para reflexes
        reflexesElements = arrayOf(
            findViewById(R.id.etxt_u_reflexes),
            findViewById(R.id.txt_reflexes),
            findViewById(R.id.txt_val_ref)
        )

        // Inicializar el array para will
        willElements = arrayOf(
            findViewById(R.id.etxt_u_will),
            findViewById(R.id.txt_will),
            findViewById(R.id.txt_val_will)
        )

        asignarOnClickListener(fortitudeElements, 1)
        asignarOnClickListener(reflexesElements, 2)
        asignarOnClickListener(willElements, 3)

        //Botones a otras pantallas
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
                findViewById<TextView>(R.id.txt_health_pts).text = character.cHealth.toString() + "/" + (character.constitution + characterRace.constitution).toString()
                findViewById<TextView>(R.id.txt_mana_pts).text = character.cMana.toString() + "/" + ((character.wisdom + characterRace.wisdom) * 3).toString()
                findViewById<TextView>(R.id.txt_luck_pts).text = character.cLuck.toString()

                findViewById<EditText>(R.id.etxt_u_fortitude).setText(character.uFortitude.toString())
                findViewById<EditText>(R.id.etxt_u_reflexes).setText(character.uReflexes.toString())
                findViewById<EditText>(R.id.etxt_u_will).setText(character.uWill.toString())

                findViewById<TextView>(R.id.txt_val_str).text = (character.strength + characterRace.strength).toString()
                findViewById<TextView>(R.id.txt_val_dex).text = (character.dexterity + characterRace.dexterity).toString()
                findViewById<TextView>(R.id.txt_val_con).text = (character.constitution + characterRace.constitution).toString()
                findViewById<TextView>(R.id.txt_val_int).text = (character.intelligence + characterRace.intelligence).toString()
                findViewById<TextView>(R.id.txt_val_wis).text = (character.wisdom + characterRace.wisdom).toString()
                findViewById<TextView>(R.id.txt_val_cha).text = (character.charisma + characterRace.charisma).toString()

                if (characterRace.size == "Pequeño") {
                    findViewById<TextView>(R.id.txt_val_speed).text = "8 m. (4 cas.)"
                    if (character.sixthSense) {
                        findViewById<TextView>(R.id.txt_val_defense).text = ((((character.dexterity - 10)/2) + ((character.wisdom - 10)/2)) +
                                                                            (((characterRace.dexterity - 10)/2) + ((characterRace.wisdom - 10)/2)) +
                                                                            12).toString()
                        findViewById<TextView>(R.id.txt_val_defense_desp).text = (((character.wisdom - 10)/2) + ((characterRace.wisdom - 10)/2) + 12).toString()
                    } else {
                        findViewById<TextView>(R.id.txt_val_defense).text = (((character.dexterity - 10)/2) + ((characterRace.dexterity - 10)/2) + 12).toString()
                        if ((character.dexterity + characterRace.dexterity)< 10) {
                            findViewById<TextView>(R.id.txt_val_defense_desp).text = (((character.dexterity - 10)/2) + ((characterRace.dexterity - 10)/2) + 12).toString()
                        } else {
                            findViewById<TextView>(R.id.txt_val_defense_desp).text = "12"
                        }
                    }
                } else {
                    findViewById<TextView>(R.id.txt_val_speed).text = "12 m. (6 cas.)"
                    if (character.sixthSense) {
                        findViewById<TextView>(R.id.txt_val_defense).text = ((((character.dexterity - 10)/2) + ((character.wisdom - 10)/2)) +
                                                                            (((characterRace.dexterity - 10)/2) + ((characterRace.wisdom - 10)/2)) +
                                                                            10).toString()
                        findViewById<TextView>(R.id.txt_val_defense_desp).text = (((character.wisdom - 10)/2) + ((characterRace.wisdom - 10)/2) + 10).toString()
                    } else {
                        findViewById<TextView>(R.id.txt_val_defense).text = (((character.dexterity - 10)/2) + ((characterRace.dexterity - 10)/2) + 10).toString()
                        if ((character.dexterity + characterRace.dexterity)< 10) {
                            findViewById<TextView>(R.id.txt_val_defense_desp).text = (((character.dexterity - 10)/2) + ((characterRace.dexterity - 10)/2) + 10).toString()
                        } else {
                            findViewById<TextView>(R.id.txt_val_defense_desp).text = "10"
                        }
                    }
                }

                if (character.enhInitiative) {
                    findViewById<TextView>(R.id.txt_val_initiative).text = (((character.dexterity - 10)/2) + ((characterRace.dexterity - 10)/2) + 4).toString()
                } else {
                    findViewById<TextView>(R.id.txt_val_initiative).text = (((character.dexterity - 10)/2) + ((characterRace.dexterity - 10)/2)).toString()
                }
                // Realizar cálculos y actualizar los modificadores
                calculateAndSetModifiers()
                updateSkillValues()
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

    private fun calculateValueFromUsages(usages: Int): Int {
        return when (usages) {
            in 0..29 -> 0
            in 30..49 -> 1
            in 50..74 -> 2
            in 75..104 -> 3
            in 105..139 -> 4
            in 140..179 -> 5
            in 180..224 -> 6
            in 225..274 -> 7
            in 275..329 -> 8
            in 330..389 -> 9
            in 390..454 -> 10
            in 455..524 -> 11
            in 525..604 -> 12
            in 605..689 -> 13
            in 690..779 -> 14
            in 780..874 -> 15
            in 875..974 -> 16
            in 975..1079 -> 17
            in 1080..1189 -> 18
            in 1190..1499 -> 19
            in 1500..5000 -> 20
            else -> 0 // Valor predeterminado si no se encuentra en ningún rango
        }
    }
    private fun updateSkillValues() {
        // Itera a través de los EditText y TextView para actualizar los valores de habilidad basados en los usos
        for (i in 0 until editTextArray.size) {
            val editText = editTextArray[i]
            val saveValueTextView = saveValueArray[i]
            val currentValue = editText.text.toString().toInt()
            var newValue = calculateValueFromUsages(currentValue)

            when (i) {
                0 -> {
                    saveValueTextView.text = (newValue + (((character.constitution + characterRace.constitution) - 10) / 2)).toString() // Fortaleza (CON)
                }
                1 -> {
                    saveValueTextView.text = (newValue + (((character.dexterity + characterRace.dexterity) - 10) / 2)).toString() // Reflejos (DES)
                }
                2 -> {
                    saveValueTextView.text = (newValue + (((character.wisdom + characterRace.wisdom) - 10) / 2)).toString() // Voluntad (SAB)
                }
                else -> saveValueTextView.text = newValue.toString() // Valor predeterminado
            }
        }
    }

    private fun asignarOnClickListener(elements: Array<View>, save: Int) {
        for (element in elements) {
            element.setOnClickListener {
                if (!editTextArray[0].isEnabled) {
                    when (save) {
                        1 -> character.uFortitude += 1
                        2 -> character.uReflexes += 1
                        3 -> character.uWill += 1
                    }
                    val characterDao = (application as AdventureJournal).db.characterDao()

                    CoroutineScope(Dispatchers.IO).launch {
                        characterDao.updateCharacter(character)
                    }
                    refreshAndCalculate()
                }
            }
        }
    }
}