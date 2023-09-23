package com.example.diario_aventura.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.CheckBox
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
import com.example.diario_aventura.db.entities.Background
import com.example.diario_aventura.db.entities.Race


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
    private lateinit var chkSixthSense: CheckBox
    private lateinit var chkEnhancedInitiative: CheckBox
    private lateinit var character: Character
    private lateinit var characterRace: Race
    private lateinit var characterBackground: Background

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pnt_cr_configuration)

        loadCharacter()

        txtRaceSelection = findViewById(R.id.txt_race_selection)
        txtSizeSelection = findViewById(R.id.txt_size_selection)
        txtBackgroundSelection = findViewById(R.id.txt_background_selection)
        etxtStrength = findViewById(R.id.etxt_strength)
        etxtDexterity = findViewById(R.id.etxt_dexterity)
        etxtConstitution = findViewById(R.id.etxt_constitution)
        etxtIntelligence = findViewById(R.id.etxt_intelligence)
        etxtWisdom = findViewById(R.id.etxt_wisdom)
        etxtCharisma = findViewById(R.id.etxt_charisma)
        chkSixthSense = findViewById(R.id.chk_sixthsense)
        chkEnhancedInitiative = findViewById(R.id.chk_enh_initiative)

        val characterDao = (application as AdventureJournal).db.characterDao()

        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {
                txtRaceSelection.text = "${characterRace.name}"
                txtSizeSelection.text = "${characterRace.size}"
                txtBackgroundSelection.text = "${characterBackground.name}"

                etxtStrength.setText(character.strength.toString())
                etxtDexterity.setText(character.dexterity.toString())
                etxtConstitution.setText(character.constitution.toString())
                etxtIntelligence.setText(character.intelligence.toString())
                etxtWisdom.setText(character.wisdom.toString())
                etxtCharisma.setText(character.charisma.toString())

                if (character.sixthSense) {
                    chkSixthSense.isChecked = true
                }

                if (character.enhInitiative) {
                    chkEnhancedInitiative.isChecked = true
                }
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

        chkSixthSense.setOnClickListener {
            character.sixthSense = chkSixthSense.isChecked
            CoroutineScope(Dispatchers.IO).launch {
                characterDao.updateCharacter(character)
            }
        }

        chkEnhancedInitiative.setOnClickListener {
            character.enhInitiative = chkEnhancedInitiative.isChecked
            CoroutineScope(Dispatchers.IO).launch {
                characterDao.updateCharacter(character)
            }
        }
    }

    private fun loadCharacter() {
        val characterId = AdventureJournal.selectedCharacterId
        val characterDao = (application as AdventureJournal).db.characterDao()
        val raceDao = (application as AdventureJournal).db.raceDao()
        val backgroundDAO = (application as AdventureJournal).db.backgroundDao()

        CoroutineScope(Dispatchers.IO).launch {
            character = characterDao.getCharacterById(characterId)
            characterRace = raceDao.getRaceById(character.race)
            characterBackground = backgroundDAO.getBackgroundById(character.background)
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