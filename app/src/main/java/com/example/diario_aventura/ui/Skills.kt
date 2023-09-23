package com.example.diario_aventura.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import com.example.diario_aventura.AdventureJournal
import com.example.diario_aventura.R
import com.example.diario_aventura.db.entities.Character
import com.example.diario_aventura.db.entities.Race
import com.example.diario_aventura.enums.Skills
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Skills : AppCompatActivity() {
    private val editTextArray = mutableListOf<EditText>()
    private val moreBtnArray = mutableListOf<ImageButton>()
    private val skillValueArray = mutableListOf<TextView>()
    private lateinit var character: Character
    private lateinit var characterRace: Race

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pnt_skills)

        loadCharacter()

        // Inicializa el array con los EditText
        editTextArray.addAll(
            listOf(
                findViewById(R.id.etxt_u_agility),
                findViewById(R.id.etxt_u_crafting1),
                findViewById(R.id.etxt_u_crafting2),
                findViewById(R.id.etxt_u_athletics),
                findViewById(R.id.etxt_u_spot),
                findViewById(R.id.etxt_u_search),
                findViewById(R.id.etxt_u_concentration),
                findViewById(R.id.etxt_u_manual_dexterity),
                findViewById(R.id.etxt_u_empathy),
                findViewById(R.id.etxt_u_listen),
                findViewById(R.id.etxt_u_perform1),
                findViewById(R.id.etxt_u_perform2),
                findViewById(R.id.etxt_u_persuasion),
                findViewById(R.id.etxt_u_mount),
                findViewById(R.id.etxt_u_pilot),
                findViewById(R.id.etxt_u_heal),
                findViewById(R.id.etxt_u_stealth),
                findViewById(R.id.etxt_u_survival)
            )
        )

        moreBtnArray.addAll(
            listOf(
                findViewById(R.id.btn_more_agility),
                findViewById(R.id.btn_more_crafting1),
                findViewById(R.id.btn_more_crafting2),
                findViewById(R.id.btn_more_athletics),
                findViewById(R.id.btn_more_spot),
                findViewById(R.id.btn_more_search),
                findViewById(R.id.btn_more_concentration),
                findViewById(R.id.btn_more_manual_dexterity),
                findViewById(R.id.btn_more_empathy),
                findViewById(R.id.btn_more_listen),
                findViewById(R.id.btn_more_perform1),
                findViewById(R.id.btn_more_perform2),
                findViewById(R.id.btn_more_persuasion),
                findViewById(R.id.btn_more_mount),
                findViewById(R.id.btn_more_pilot),
                findViewById(R.id.btn_more_heal),
                findViewById(R.id.btn_more_stealth),
                findViewById(R.id.btn_more_survival)
            )
        )

        skillValueArray.addAll(
            listOf(
                findViewById(R.id.txt_val_agility),
                findViewById(R.id.txt_val_crafting1),
                findViewById(R.id.txt_val_crafting2),
                findViewById(R.id.txt_val_athletics),
                findViewById(R.id.txt_val_spot),
                findViewById(R.id.txt_val_search),
                findViewById(R.id.txt_val_concentration),
                findViewById(R.id.txt_val_manual_dexterity),
                findViewById(R.id.txt_val_empathy),
                findViewById(R.id.txt_val_listen),
                findViewById(R.id.txt_val_perform1),
                findViewById(R.id.txt_val_perform2),
                findViewById(R.id.txt_val_persuasion),
                findViewById(R.id.txt_val_mount),
                findViewById(R.id.txt_val_pilot),
                findViewById(R.id.txt_val_heal),
                findViewById(R.id.txt_val_stealth),
                findViewById(R.id.txt_val_survival)
            )
        )

        val BtnEditUses = findViewById<ImageButton>(R.id.btn_edituses_sklls)

        BtnEditUses.setOnClickListener {
            // Cambia el estado de edición del EditText
            for (eTxt in editTextArray) {
                eTxt.isEnabled = !eTxt.isEnabled
            }

            // Guarda los cambios en la base de datos si se desactivan los EditText
            if (!editTextArray[0].isEnabled) {
                val characterDao = (application as AdventureJournal).db.characterDao()

                CoroutineScope(Dispatchers.IO).launch {

                    character?.let {
                        // Actualiza las propiedades del personaje utilizando las referencias previamente obtenidas
                        it.uAgility = editTextArray[0].text.toString().toInt()
                        it.uCrafting1 = editTextArray[1].text.toString().toInt()
                        it.uCrafting2 = editTextArray[2].text.toString().toInt()
                        it.uAthletics = editTextArray[3].text.toString().toInt()
                        it.uSpot = editTextArray[4].text.toString().toInt()
                        it.uSearch = editTextArray[5].text.toString().toInt()
                        it.uConcentration = editTextArray[6].text.toString().toInt()
                        it.uManualDexterity = editTextArray[7].text.toString().toInt()
                        it.uEmpathy = editTextArray[8].text.toString().toInt()
                        it.uListen = editTextArray[9].text.toString().toInt()
                        it.uPerform1 = editTextArray[10].text.toString().toInt()
                        it.uPerform2 = editTextArray[11].text.toString().toInt()
                        it.uPersuasion = editTextArray[12].text.toString().toInt()
                        it.uMount = editTextArray[13].text.toString().toInt()
                        it.uPilot = editTextArray[14].text.toString().toInt()
                        it.uHeal = editTextArray[15].text.toString().toInt()
                        it.uStealth = editTextArray[16].text.toString().toInt()
                        it.uSurvival = editTextArray[17].text.toString().toInt()

                        characterDao.updateCharacter(it)
                    }
                }
                updateSkillValues()
            }
        }

        // Agregar listeners a los botones "More"
        for (i in 0 until moreBtnArray.size) {
            val moreButton = moreBtnArray[i]
            val editText = editTextArray[i]

            moreButton.setOnClickListener {
                val currentValue = editText.text.toString().toInt()
                val updatedValue = currentValue + 1
                editText.setText(updatedValue.toString())

                // Actualiza el valor en la base de datos
                updateCharacterProperty(i, updatedValue)

                // Actualiza los valores de habilidad basados en los usos
                updateSkillValues()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        loadCharacter()
        // Cargar y establecer los datos del personaje cada vez que se muestra la pantalla
        loadCharacterDataToEditText()
    }

    private fun loadCharacterDataToEditText() {
        CoroutineScope(Dispatchers.IO).launch {
            character?.let {
                // Actualizar los valores de los EditText con los datos del personaje
                runOnUiThread {
                    editTextArray[0].setText(it.uAgility.toString())
                    editTextArray[1].setText(it.uCrafting1.toString())
                    editTextArray[2].setText(it.uCrafting2.toString())
                    editTextArray[3].setText(it.uAthletics.toString())
                    editTextArray[4].setText(it.uSpot.toString())
                    editTextArray[5].setText(it.uSearch.toString())
                    editTextArray[6].setText(it.uConcentration.toString())
                    editTextArray[7].setText(it.uManualDexterity.toString())
                    editTextArray[8].setText(it.uEmpathy.toString())
                    editTextArray[9].setText(it.uListen.toString())
                    editTextArray[10].setText(it.uPerform1.toString())
                    editTextArray[11].setText(it.uPerform2.toString())
                    editTextArray[12].setText(it.uPersuasion.toString())
                    editTextArray[13].setText(it.uMount.toString())
                    editTextArray[14].setText(it.uPilot.toString())
                    editTextArray[15].setText(it.uHeal.toString())
                    editTextArray[16].setText(it.uStealth.toString())
                    editTextArray[17].setText(it.uSurvival.toString())

                    for (eTxt in editTextArray) {
                        eTxt.isEnabled = false // Deshabilita los EditText después de cargar los datos
                    }
                    updateSkillValues()
                }
            }
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
            val skillValueTextView = skillValueArray[i]
            val currentValue = editText.text.toString().toInt()
            var newValue = calculateValueFromUsages(currentValue)

            when (i) {
                0 -> {
                    if (characterRace.bSkill == Skills.AGILITY || character.hBSkill == Skills.AGILITY || character.hEBSkill == Skills.AGILITY) {
                        newValue += 3
                    }
                    skillValueTextView.text = (newValue + (((character.dexterity + characterRace.dexterity + character.hBDexterity) - 10) / 2)).toString() // Destreza (DEX)
                }
                1 -> {
                    if (characterRace.bSkill == Skills.CRAFTING_1 || character.hBSkill == Skills.CRAFTING_1 || character.hEBSkill == Skills.CRAFTING_1) {
                        newValue += 3
                    }
                    skillValueTextView.text = (newValue + (((character.intelligence + characterRace.intelligence + character.hBIntelligence) - 10) / 2)).toString() // Inteligencia (INT)
                }
                2 -> {
                    if (characterRace.bSkill == Skills.CRAFTING_1 || character.hBSkill == Skills.CRAFTING_1 || character.hEBSkill == Skills.CRAFTING_1) {
                        newValue += 3
                    }
                    skillValueTextView.text = (newValue + (((character.intelligence + characterRace.intelligence + character.hBIntelligence) - 10) / 2)).toString() // Inteligencia (INT)
                }
                3 -> {
                    if (characterRace.bSkill == Skills.ATHLETICS || character.hBSkill == Skills.ATHLETICS || character.hEBSkill == Skills.ATHLETICS) {
                        newValue += 3
                    }
                    skillValueTextView.text = (newValue + (((character.strength + characterRace.strength + character.hBStrength) - 10) / 2)).toString() // Fuerza (STR)
                }
                4 -> {
                    if (characterRace.bSkill == Skills.SPOT || character.hBSkill == Skills.SPOT || character.hEBSkill == Skills.SPOT) {
                        newValue += 3
                    }
                    skillValueTextView.text = (newValue + (((character.wisdom + characterRace.wisdom + character.hBWisdom) - 10) / 2)).toString() // Sabiduría (WIS)
                }
                5 -> {
                    if (characterRace.bSkill == Skills.SEARCH || character.hBSkill == Skills.SEARCH || character.hEBSkill == Skills.SEARCH) {
                        newValue += 3
                    }
                    skillValueTextView.text = (newValue + (((character.intelligence + characterRace.intelligence + character.hBIntelligence) - 10) / 2)).toString() // Inteligencia (INT)
                }
                6 -> {
                    if (characterRace.bSkill == Skills.CONCENTRATION || character.hBSkill == Skills.CONCENTRATION || character.hEBSkill == Skills.CONCENTRATION) {
                        newValue += 3
                    }
                    skillValueTextView.text = (newValue + (((character.constitution + characterRace.constitution + character.hBConstitution) - 10) / 2)).toString() // Constitución (CON)
                }
                7 -> {
                    if (characterRace.bSkill == Skills.MANUAL_DEXTERITY || character.hBSkill == Skills.MANUAL_DEXTERITY || character.hEBSkill == Skills.MANUAL_DEXTERITY) {
                        newValue += 3
                    }
                    skillValueTextView.text = (newValue + (((character.dexterity + characterRace.dexterity + character.hBDexterity) - 10) / 2)).toString() // Destreza (DEX)
                }
                8 -> {
                    if (characterRace.bSkill == Skills.EMPATHY || character.hBSkill == Skills.EMPATHY || character.hEBSkill == Skills.EMPATHY) {
                        newValue += 3
                    }
                    skillValueTextView.text = (newValue + (((character.charisma + characterRace.charisma + character.hBCharisma) - 10) / 2)).toString() // Carisma (CHA)
                }
                9 -> {
                    if (characterRace.bSkill == Skills.LISTEN || character.hBSkill == Skills.LISTEN || character.hEBSkill == Skills.LISTEN) {
                        newValue += 3
                    }
                    skillValueTextView.text = (newValue + (((character.wisdom + characterRace.wisdom + character.hBWisdom) - 10) / 2)).toString() // Sabiduría (WIS)
                }
                10 -> {
                    if (characterRace.bSkill == Skills.PERFORM_1 || character.hBSkill == Skills.PERFORM_1 || character.hEBSkill == Skills.PERFORM_1) {
                        newValue += 3
                    }
                    skillValueTextView.text = (newValue + (((character.charisma + characterRace.charisma + character.hBCharisma) - 10) / 2)).toString() // Carisma (CHA)
                }
                11 -> {
                    if (characterRace.bSkill == Skills.PERFORM_1 || character.hBSkill == Skills.PERFORM_1 || character.hEBSkill == Skills.PERFORM_1) {
                        newValue += 3
                    }
                    skillValueTextView.text = (newValue + (((character.charisma + characterRace.charisma + character.hBCharisma) - 10) / 2)).toString() // Carisma (CHA)
                }
                12 -> {
                    if (characterRace.bSkill == Skills.PERSUASION || character.hBSkill == Skills.PERSUASION || character.hEBSkill == Skills.PERSUASION) {
                        newValue += 3
                    }
                    skillValueTextView.text = (newValue + (((character.charisma + characterRace.charisma + character.hBCharisma) - 10) / 2)).toString() // Carisma (CHA)
                }
                13 -> {
                    if (characterRace.bSkill == Skills.MOUNT || character.hBSkill == Skills.MOUNT || character.hEBSkill == Skills.MOUNT) {
                        newValue += 3
                    }
                    skillValueTextView.text = (newValue + (((character.dexterity + characterRace.dexterity + character.hBDexterity) - 10) / 2)).toString() // Destreza (DEX)
                }
                14 -> {
                    if (characterRace.bSkill == Skills.PILOT || character.hBSkill == Skills.PILOT || character.hEBSkill == Skills.PILOT) {
                        newValue += 3
                    }
                    skillValueTextView.text = (newValue + (((character.wisdom + characterRace.wisdom + character.hBWisdom) - 10) / 2)).toString() // Sabiduría (WIS)
                }
                15 -> {
                    if (characterRace.bSkill == Skills.HEAL || character.hBSkill == Skills.HEAL || character.hEBSkill == Skills.HEAL) {
                        newValue += 3
                    }
                    skillValueTextView.text = (newValue + (((character.wisdom + characterRace.wisdom + character.hBWisdom) - 10) / 2)).toString() // Sabiduría (WIS)
                }
                16 -> {
                    if (characterRace.bSkill == Skills.STEALTH || character.hBSkill == Skills.STEALTH || character.hEBSkill == Skills.STEALTH) {
                        newValue += 3
                    }
                    skillValueTextView.text = (newValue + (((character.dexterity + characterRace.dexterity + character.hBDexterity) - 10) / 2)).toString() // Destreza (DEX)
                }
                17 -> {
                    if (characterRace.bSkill == Skills.SURVIVAL || character.hBSkill == Skills.SURVIVAL || character.hEBSkill == Skills.SURVIVAL) {
                        newValue += 3
                    }
                    skillValueTextView.text = (newValue + (((character.wisdom + characterRace.wisdom + character.hBWisdom) - 10) / 2)).toString() // Sabiduría (WIS)
                }
                else -> skillValueTextView.text = newValue.toString() // Valor predeterminado
            }
        }
    }

    private fun updateCharacterProperty(propertyIndex: Int, updatedValue: Int) {
        val characterDao = (application as AdventureJournal).db.characterDao()

        CoroutineScope(Dispatchers.IO).launch {

            character?.let {
                when (propertyIndex) {
                    0 -> it.uAgility = updatedValue
                    1 -> it.uCrafting1 = updatedValue
                    2 -> it.uCrafting2 = updatedValue
                    3 -> it.uAthletics = updatedValue
                    4 -> it.uSpot = updatedValue
                    5 -> it.uSearch = updatedValue
                    6 -> it.uConcentration = updatedValue
                    7 -> it.uManualDexterity = updatedValue
                    8 -> it.uEmpathy = updatedValue
                    9 -> it.uListen = updatedValue
                    10 -> it.uPerform1 = updatedValue
                    11 -> it.uPerform2 = updatedValue
                    12 -> it.uPersuasion = updatedValue
                    13 -> it.uMount = updatedValue
                    14 -> it.uPilot = updatedValue
                    15 -> it.uHeal = updatedValue
                    16 -> it.uStealth = updatedValue
                    17 -> it.uSurvival = updatedValue
                }

                characterDao.updateCharacter(it)
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
