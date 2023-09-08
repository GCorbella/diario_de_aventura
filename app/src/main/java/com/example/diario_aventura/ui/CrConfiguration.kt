package com.example.diario_aventura.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import com.example.diario_aventura.AdventureJournal
import com.example.diario_aventura.R
import com.example.diario_aventura.db.dao.CharacterDAO
import com.example.diario_aventura.db.entities.Character
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.example.diario_aventura.db.CharactersDB


class CrConfiguration : AppCompatActivity() {
    private lateinit var txtRaceSelection: TextView
    private lateinit var txtSizeSelection: TextView
    private lateinit var txtBackgroundSelection: TextView
    private lateinit var etxtStrength: EditText
    private lateinit var etxtDexterity: EditText
    private lateinit var etxtConstitution: EditText
    private lateinit var etxtIntelligence: EditText
    private lateinit var etxtWisdom: EditText
    private lateinit var etxtCharisma: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pnt_cr_configuration)

        txtRaceSelection = findViewById(R.id.txt_race_selection)
        txtSizeSelection = findViewById(R.id.txt_size_selection)
        txtBackgroundSelection = findViewById(R.id.txt_background_selection)
        etxtStrength = findViewById(R.id.etxt_strength)
        etxtDexterity = findViewById(R.id.etxt_dexterity)
        etxtConstitution = findViewById(R.id.etxt_constitution)
        etxtIntelligence = findViewById(R.id.etxt_intelligence)
        etxtWisdom = findViewById(R.id.etxt_wisdom)
        etxtCharisma = findViewById(R.id.etxt_charisma)

        val selectedCharacterId = AdventureJournal.selectedCharacterId
        val charactersDB = CharactersDB.getInstance(applicationContext)
        val characterDao = charactersDB.characterDao()
        val raceDao = charactersDB.raceDao()
        val backgroundDAO = charactersDB.backgroundDao()

        CoroutineScope(Dispatchers.IO).launch {
            val character = characterDao.getCharacterById(selectedCharacterId)
            val race = raceDao.getRaceById(character.race)
            val background = backgroundDAO.getBackgroundById(character.background)

            withContext(Dispatchers.Main) {
                txtRaceSelection.text = "${race.name}"
                txtSizeSelection.text = "${race.size}"
                txtBackgroundSelection.text = "${background.name}"

                etxtStrength.setText(character.strength.toString())
                etxtDexterity.setText(character.dexterity.toString())
                etxtConstitution.setText(character.constitution.toString())
                etxtIntelligence.setText(character.intelligence.toString())
                etxtWisdom.setText(character.wisdom.toString())
                etxtCharisma.setText(character.charisma.toString())
            }

            etxtStrength.addTextChangedListener(createTextWatcher(characterDao, character) { newValue ->
                character.strength = newValue
                CoroutineScope(Dispatchers.IO).launch {
                    characterDao.updateCharacter(character)
                }
            })

            etxtDexterity.addTextChangedListener(createTextWatcher(characterDao, character) { newValue ->
                character.dexterity = newValue
                CoroutineScope(Dispatchers.IO).launch {
                    characterDao.updateCharacter(character)
                }
            })

            etxtConstitution.addTextChangedListener(createTextWatcher(characterDao, character) { newValue ->
                character.constitution = newValue
                CoroutineScope(Dispatchers.IO).launch {
                    characterDao.updateCharacter(character)
                }
            })

            etxtIntelligence.addTextChangedListener(createTextWatcher(characterDao, character) { newValue ->
                character.intelligence = newValue
                CoroutineScope(Dispatchers.IO).launch {
                    characterDao.updateCharacter(character)
                }
            })

            etxtWisdom.addTextChangedListener(createTextWatcher(characterDao, character) { newValue ->
                character.wisdom = newValue
                CoroutineScope(Dispatchers.IO).launch {
                    characterDao.updateCharacter(character)
                }
            })

            etxtCharisma.addTextChangedListener(createTextWatcher(characterDao, character) { newValue ->
                character.charisma = newValue
                CoroutineScope(Dispatchers.IO).launch {
                    characterDao.updateCharacter(character)
                }
            })
        }
    }

    private fun createTextWatcher(characterDao: CharacterDAO, character: Character, onUpdate: (Int) -> Unit): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val newValue = s.toString().toIntOrNull()
                if (newValue != null) {
                    onUpdate(newValue)
                    // La actualización de la base de datos debe realizarse en un hilo secundario
                    CoroutineScope(Dispatchers.IO).launch {
                        characterDao.updateCharacter(character)
                    }
                }
            }
        }
    }
}