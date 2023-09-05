package com.example.diario_aventura.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import com.example.diario_aventura.AdventureJournal
import com.example.diario_aventura.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Skills : AppCompatActivity() {
    private val editTextArray = mutableListOf<EditText>() // Usar mutableListOf en lugar de arrayOf

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pnt_skills)

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

        val BtnEditUses = findViewById<ImageButton>(R.id.btn_edituses_h)

        BtnEditUses.setOnClickListener {
            // Cambia el estado de edición del EditText
            for (eTxt in editTextArray) {
                eTxt.isEnabled = !eTxt.isEnabled
            }

            // Guarda los cambios en la base de datos si se desactivan los EditText
            if (!editTextArray[0].isEnabled) {
                val characterId = AdventureJournal.selectedCharacterId
                val characterDao = (application as AdventureJournal).db.characterDao()

                CoroutineScope(Dispatchers.IO).launch {
                    val character = characterDao.getCharacterById(characterId)

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
            }
        }
    }

    override fun onResume() {
        super.onResume()

        // Cargar y establecer los datos del personaje cada vez que se muestra la pantalla
        loadCharacterDataToEditText()
    }

    private fun loadCharacterDataToEditText() {
        val characterId = AdventureJournal.selectedCharacterId
        val characterDao = (application as AdventureJournal).db.characterDao()

        CoroutineScope(Dispatchers.IO).launch {
            val character = characterDao.getCharacterById(characterId)

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
                }
            }
        }
    }
}