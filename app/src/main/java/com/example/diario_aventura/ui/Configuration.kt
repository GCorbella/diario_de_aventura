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
import com.example.diario_aventura.enums.Proficiencies
import com.example.diario_aventura.enums.Skills
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
                    DataStoreManager.saveSelectedCharacterId(context, selectedCharacter.id) }
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
                loadSkillsInSpinners(dialogView)
                loadHumanBonusInSpinners(dialogView)
            }

            spinnerRace.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                val humanBonusContainer = dialogView.findViewById<LinearLayout>(R.id.ll_humanbonus_container)
                val halfElfBonusSkillSpinner = dialogView.findViewById<Spinner>(R.id.spinner_hebskill)
                val halfOrcBonusProficiencySpinner = dialogView.findViewById<Spinner>(R.id.spinner_hobproficiency)
                override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View?, position: Int, id: Long) {
                    when (position) {
                        11, 17, 18 -> humanBonusContainer.visibility = View.VISIBLE
                        else -> humanBonusContainer.visibility = View.GONE
                    }
                    if (position == 11) {
                        halfElfBonusSkillSpinner.visibility = View.GONE
                        halfOrcBonusProficiencySpinner.visibility = View.GONE
                    }
                    if (position == 17) {
                        halfElfBonusSkillSpinner.visibility = View.VISIBLE
                        halfOrcBonusProficiencySpinner.visibility = View.GONE
                    }
                    if (position == 18) {
                        halfElfBonusSkillSpinner.visibility = View.GONE
                        halfOrcBonusProficiencySpinner.visibility = View.VISIBLE
                    }
                }

                override fun onNothingSelected(parentView: AdapterView<*>?) {
                    // No es necesario hacer nada aquí
                }
            }

            spinnerBackground.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                val aprSkillsContainer = dialogView.findViewById<LinearLayout>(R.id.ll_aprskills_container)
                override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View?, position: Int, id: Long) {
                    if (position == 3) {
                        // Si la posición seleccionada en spinnerBackground es igual a 3 (Aprendiz),
                        // muestra el contenido oculto
                        aprSkillsContainer.visibility = View.VISIBLE
                    } else {
                        // En caso contrario, oculta el contenido
                        aprSkillsContainer.visibility = View.GONE
                    }
                }

                override fun onNothingSelected(parentView: AdapterView<*>?) {
                    // No es necesario hacer nada aquí
                }
            }

            btnCreateCr.setOnClickListener {
                val crName = etxtNewCrName.text.toString()
                val selectedRace = races[spinnerRace.selectedItemPosition]
                val selectedBackground = backgrounds[spinnerBackground.selectedItemPosition]

                if (crName.isNotBlank()) {
                    // Aquí puedes insertar el nuevo personaje en la base de datos
                    // y actualizar la lista de personajes en el desplegable
                    CoroutineScope(Dispatchers.IO).launch {
                        val characterDao = (application as AdventureJournal).db.characterDao()
                        val newCharacterId = characterDao.createNewCharacter(crName, selectedRace.id, selectedBackground.id)
                        val newCharacter = characterDao.getCharacterById(newCharacterId)

                        val allSkills = Skills.values().toList()
                        val filteredSkills = allSkills.filter { it != Skills.CRAFTING_2 && it != Skills.PERFORM_2 && it != Skills.CHOOSE && it != Skills.NONE }
                        val allProficiencies = Proficiencies.values().toList()
                        val filteredProficiencies = allProficiencies.filter { it != Proficiencies.CHOOSE && it != Proficiencies.NONE }

                        if (selectedRace.id == 12L || selectedRace.id == 18L || selectedRace.id == 19L) {
                            val humanBonusAttributeSpinner = dialogView.findViewById<Spinner>(R.id.spinner_hbattribute)
                            val humanBonusSkillsSpinner = dialogView.findViewById<Spinner>(R.id.spinner_hbskill)
                            val humanBonusProficienciesSpinner = dialogView.findViewById<Spinner>(R.id.spinner_hbproficiency)

                            val selectedHumanBonusAttributePosition = humanBonusAttributeSpinner.selectedItemPosition
                            val selectedHumanBonusSkillPosition = humanBonusSkillsSpinner.selectedItemPosition
                            val selectedHumanBonusProficiencyPosition = humanBonusProficienciesSpinner.selectedItemPosition

                            when (selectedHumanBonusAttributePosition) {
                                0 -> newCharacter.hBAttribute = 1
                                1 -> newCharacter.hBAttribute = 2
                                2 -> newCharacter.hBAttribute = 3
                                3 -> newCharacter.hBAttribute = 4
                                4 -> newCharacter.hBAttribute = 5
                                5 -> newCharacter.hBAttribute = 6
                            }

                            val selectedHumanBonusSkill = filteredSkills[selectedHumanBonusSkillPosition]
                            when (selectedHumanBonusSkill.label) {
                                "Agilidad" -> newCharacter.hBSkill = Skills.AGILITY
                                "Artesanía" -> newCharacter.hBSkill = Skills.CRAFTING_1
                                "Atletismo" -> newCharacter.hBSkill = Skills.ATHLETICS
                                "Avistar" -> newCharacter.hBSkill = Skills.SPOT
                                "Buscar" -> newCharacter.hBSkill = Skills.SEARCH
                                "Concentración" -> newCharacter.hBSkill = Skills.CONCENTRATION
                                "Destreza Manual" -> newCharacter.hBSkill = Skills.MANUAL_DEXTERITY
                                "Empatía" -> newCharacter.hBSkill = Skills.EMPATHY
                                "Escuchar" -> newCharacter.hBSkill = Skills.LISTEN
                                "Interpretar" -> newCharacter.hBSkill = Skills.PERFORM_1
                                "Labia" -> newCharacter.hBSkill = Skills.PERSUASION
                                "Montar" -> newCharacter.hBSkill = Skills.MOUNT
                                "Pilotar" -> newCharacter.hBSkill = Skills.PILOT
                                "Sanar" -> newCharacter.hBSkill = Skills.HEAL
                                "Sigilo" -> newCharacter.hBSkill = Skills.STEALTH
                                "Supervivencia" -> newCharacter.hBSkill = Skills.SURVIVAL
                            }

                            val selectedHumanBonusProficiency = filteredProficiencies[selectedHumanBonusProficiencyPosition]
                            when (selectedHumanBonusProficiency.label) {
                                "Desarmado" -> newCharacter.hBProficiency = Proficiencies.UNARMED
                                "Dagas" -> newCharacter.hBProficiency = Proficiencies.DAGGERS
                                "Mazas" -> newCharacter.hBProficiency = Proficiencies.MACES
                                "Hachas" -> newCharacter.hBProficiency = Proficiencies.AXES
                                "Espadas" -> newCharacter.hBProficiency = Proficiencies.SWORDS
                                "Estoques" -> newCharacter.hBProficiency = Proficiencies.RAPIERS
                                "Látigos" -> newCharacter.hBProficiency = Proficiencies.WHIPS
                                "Lanzas" -> newCharacter.hBProficiency = Proficiencies.SPEARS
                                "Mazas 2M" -> newCharacter.hBProficiency = Proficiencies._2HMACES
                                "Hachas 2M" -> newCharacter.hBProficiency = Proficiencies._2HAXES
                                "Espadas 2M" -> newCharacter.hBProficiency = Proficiencies._2HSWORDS
                                "Armas Dobles" -> newCharacter.hBProficiency = Proficiencies.DOUBLE_WEAPONS
                                "Armas Arrojadizas" -> newCharacter.hBProficiency = Proficiencies.THROWING_WEAPONS
                                "Arcos" -> newCharacter.hBProficiency = Proficiencies.BOWS
                                "Ballestas" -> newCharacter.hBProficiency = Proficiencies.CROSSBOWS
                                "Armas de Fuego C." -> newCharacter.hBProficiency = Proficiencies.SHORT_FIREARMS
                                "Armas de Fuego L." -> newCharacter.hBProficiency = Proficiencies.LONG_FIREARMS
                                "Hechizos Proyectil" -> newCharacter.hBProficiency = Proficiencies.PROJECTILE_SPELLS
                                "Hechizos Rayo" -> newCharacter.hBProficiency = Proficiencies.RAY_SPELLS
                            }

                            if (selectedRace.id == 18L) {
                                val halfElfBonusSkillsSpinner = dialogView.findViewById<Spinner>(R.id.spinner_hebskill)
                                val selectedHalfElfBonusSkillPosition = halfElfBonusSkillsSpinner.selectedItemPosition
                                val selectedHalfElfBonusSkill = filteredSkills[selectedHalfElfBonusSkillPosition]
                                when (selectedHalfElfBonusSkill.label) {
                                    "Agilidad" -> newCharacter.hEBSkill = Skills.AGILITY
                                    "Artesanía" -> newCharacter.hEBSkill = Skills.CRAFTING_1
                                    "Atletismo" -> newCharacter.hEBSkill = Skills.ATHLETICS
                                    "Avistar" -> newCharacter.hEBSkill = Skills.SPOT
                                    "Buscar" -> newCharacter.hEBSkill = Skills.SEARCH
                                    "Concentración" -> newCharacter.hEBSkill = Skills.CONCENTRATION
                                    "Destreza Manual" -> newCharacter.hEBSkill = Skills.MANUAL_DEXTERITY
                                    "Empatía" -> newCharacter.hEBSkill = Skills.EMPATHY
                                    "Escuchar" -> newCharacter.hEBSkill = Skills.LISTEN
                                    "Interpretar" -> newCharacter.hEBSkill = Skills.PERFORM_1
                                    "Labia" -> newCharacter.hEBSkill = Skills.PERSUASION
                                    "Montar" -> newCharacter.hEBSkill = Skills.MOUNT
                                    "Pilotar" -> newCharacter.hEBSkill = Skills.PILOT
                                    "Sanar" -> newCharacter.hEBSkill = Skills.HEAL
                                    "Sigilo" -> newCharacter.hEBSkill = Skills.STEALTH
                                    "Supervivencia" -> newCharacter.hEBSkill = Skills.SURVIVAL
                                }
                            }
                            if (selectedRace.id == 19L) {
                                val halfOrcBonusProficienciesSpinner = dialogView.findViewById<Spinner>(R.id.spinner_hobproficiency)
                                val selectedHalfOrcBonusProficiencyPosition = halfOrcBonusProficienciesSpinner.selectedItemPosition
                                val selectedHalfOrcBonusProficiency = filteredProficiencies[selectedHalfOrcBonusProficiencyPosition]
                                when (selectedHalfOrcBonusProficiency.label) {
                                    "Desarmado" -> newCharacter.hOBProficiency = Proficiencies.UNARMED
                                    "Dagas" -> newCharacter.hOBProficiency = Proficiencies.DAGGERS
                                    "Mazas" -> newCharacter.hOBProficiency = Proficiencies.MACES
                                    "Hachas" -> newCharacter.hOBProficiency = Proficiencies.AXES
                                    "Espadas" -> newCharacter.hOBProficiency = Proficiencies.SWORDS
                                    "Estoques" -> newCharacter.hOBProficiency = Proficiencies.RAPIERS
                                    "Látigos" -> newCharacter.hOBProficiency = Proficiencies.WHIPS
                                    "Lanzas" -> newCharacter.hOBProficiency = Proficiencies.SPEARS
                                    "Mazas 2M" -> newCharacter.hOBProficiency = Proficiencies._2HMACES
                                    "Hachas 2M" -> newCharacter.hOBProficiency = Proficiencies._2HAXES
                                    "Espadas 2M" -> newCharacter.hOBProficiency = Proficiencies._2HSWORDS
                                    "Armas Dobles" -> newCharacter.hOBProficiency = Proficiencies.DOUBLE_WEAPONS
                                    "Armas Arrojadizas" -> newCharacter.hOBProficiency = Proficiencies.THROWING_WEAPONS
                                    "Arcos" -> newCharacter.hOBProficiency = Proficiencies.BOWS
                                    "Ballestas" -> newCharacter.hOBProficiency = Proficiencies.CROSSBOWS
                                    "Armas de Fuego C." -> newCharacter.hOBProficiency = Proficiencies.SHORT_FIREARMS
                                    "Armas de Fuego L." -> newCharacter.hOBProficiency = Proficiencies.LONG_FIREARMS
                                    "Hechizos Proyectil" -> newCharacter.hOBProficiency = Proficiencies.PROJECTILE_SPELLS
                                    "Hechizos Rayo" -> newCharacter.hOBProficiency = Proficiencies.RAY_SPELLS
                                }
                            }
                        }

                        if (selectedBackground.id != 4L) {

                            newCharacter.uAgility += selectedBackground.uAgility
                            newCharacter.uCrafting1 += selectedBackground.uCrafting1
                            newCharacter.uCrafting2 += selectedBackground.uCrafting2
                            newCharacter.uAthletics += selectedBackground.uAthletics
                            newCharacter.uSpot += selectedBackground.uSpot
                            newCharacter.uSearch += selectedBackground.uSearch
                            newCharacter.uConcentration += selectedBackground.uConcentration
                            newCharacter.uManualDexterity += selectedBackground.uManualDexterity
                            newCharacter.uEmpathy += selectedBackground.uEmpathy
                            newCharacter.uListen += selectedBackground.uListen
                            newCharacter.uPerform1 += selectedBackground.uPerform1
                            newCharacter.uPerform2 += selectedBackground.uPerform2
                            newCharacter.uPersuasion += selectedBackground.uPersuasion
                            newCharacter.uMount += selectedBackground.uMount
                            newCharacter.uPilot += selectedBackground.uPilot
                            newCharacter.uHeal += selectedBackground.uHeal
                            newCharacter.uStealth += selectedBackground.uStealth
                            newCharacter.uSurvival += selectedBackground.uSurvival

                            if (selectedBackground.meleeProficiency) {
                                newCharacter.uUnarmed += 14
                                newCharacter.uDaggers += 14
                                newCharacter.uMaces += 14
                                newCharacter.uAxes += 14
                                newCharacter.uSwords += 14
                                newCharacter.uRapiers += 14
                                newCharacter.uWhips += 14
                                newCharacter.uSpears += 14
                                newCharacter.u2hMaces += 14
                                newCharacter.u2hAxes += 14
                                newCharacter.u2hSwords += 14
                                newCharacter.uDoubleWeapons += 14
                            }

                            if (selectedBackground.rangedProficiency) {
                                newCharacter.uThrowingWeapons += 14
                                newCharacter.uBows += 14
                                newCharacter.uCrossbows += 14
                                newCharacter.uShortFirearms += 14
                                newCharacter.uLongFirearms += 14
                                newCharacter.uProjectileSpells += 14
                                newCharacter.uRaySpells += 14
                            }

                            characterDao.updateCharacter(newCharacter)
                        } else {

                            val spinnerSkill2 = listOf(
                                R.id.spinner_aprskill1,
                                R.id.spinner_aprskill2,
                                R.id.spinner_aprskill3
                            )
                            val spinnerSkill1 = listOf(
                                R.id.spinner_aprskill4,
                                R.id.spinner_aprskill5,
                                R.id.spinner_aprskill6
                            )

                            for (spinnerId in spinnerSkill2) {
                                val spinner = dialogView.findViewById<Spinner>(spinnerId)
                                val selectedSkillPosition = spinner.selectedItemPosition
                                val selectedSkill = filteredSkills[selectedSkillPosition] // Obtén la habilidad seleccionada
                                when (selectedSkill.label) {
                                    "Agilidad" -> {
                                        newCharacter.uAgility += 36
                                    }
                                    "Artesanía" -> {
                                        newCharacter.uCrafting1 += 36
                                        newCharacter.uCrafting2 += 36
                                    }
                                    "Atletismo" -> {
                                        newCharacter.uAthletics += 36
                                    }
                                    "Avistar" -> {
                                        newCharacter.uSpot += 36
                                    }
                                    "Buscar" -> {
                                        newCharacter.uSearch += 36
                                    }
                                    "Concentración" -> {
                                        newCharacter.uConcentration += 36
                                    }
                                    "Destreza Manual" -> {
                                        newCharacter.uManualDexterity += 36
                                    }
                                    "Empatía" -> {
                                        newCharacter.uEmpathy += 36
                                    }
                                    "Escuchar" -> {
                                        newCharacter.uListen += 36
                                    }
                                    "Interpretar" -> {
                                        newCharacter.uPerform1 += 36
                                        newCharacter.uPerform2 += 36
                                    }
                                    "Labia" -> {
                                        newCharacter.uPersuasion += 36
                                    }
                                    "Montar" -> {
                                        newCharacter.uMount += 36
                                    }
                                    "Pilotar" -> {
                                        newCharacter.uPilot += 36
                                    }
                                    "Sanar" -> {
                                        newCharacter.uHeal += 36
                                    }
                                    "Sigilo" -> {
                                        newCharacter.uStealth += 36
                                    }
                                    "Supervivencia" -> {
                                        newCharacter.uSurvival += 36
                                    }
                                }
                            }

                            for (spinnerId in spinnerSkill1) {
                                val spinner = dialogView.findViewById<Spinner>(spinnerId)
                                val selectedSkillPosition = spinner.selectedItemPosition
                                val selectedSkill = filteredSkills[selectedSkillPosition] // Obtén la habilidad seleccionada
                                when (selectedSkill.label) {
                                    "Agilidad" -> {
                                        newCharacter.uAgility += 16
                                    }
                                    "Artesanía" -> {
                                        newCharacter.uCrafting1 += 16
                                        newCharacter.uCrafting2 += 16
                                    }
                                    "Atletismo" -> {
                                        newCharacter.uAthletics += 16
                                    }
                                    "Avistar" -> {
                                        newCharacter.uSpot += 16
                                    }
                                    "Buscar" -> {
                                        newCharacter.uSearch += 16
                                    }
                                    "Concentración" -> {
                                        newCharacter.uConcentration += 16
                                    }
                                    "Destreza Manual" -> {
                                        newCharacter.uManualDexterity += 16
                                    }
                                    "Empatía" -> {
                                        newCharacter.uEmpathy += 16
                                    }
                                    "Escuchar" -> {
                                        newCharacter.uListen += 16
                                    }
                                    "Interpretar" -> {
                                        newCharacter.uPerform1 += 16
                                        newCharacter.uPerform2 += 16
                                    }
                                    "Labia" -> {
                                        newCharacter.uPersuasion += 16
                                    }
                                    "Montar" -> {
                                        newCharacter.uMount += 16
                                    }
                                    "Pilotar" -> {
                                        newCharacter.uPilot += 16
                                    }
                                    "Sanar" -> {
                                        newCharacter.uHeal += 16
                                    }
                                    "Sigilo" -> {
                                        newCharacter.uStealth += 16
                                    }
                                    "Supervivencia" -> {
                                        newCharacter.uSurvival += 16
                                    }
                                }
                            }

                            characterDao.updateCharacter(newCharacter)
                        }

                        CoroutineScope(Dispatchers.Main).launch {
                            loadCharactersInSpinner()
                            Toast.makeText(this@Configuration, "Personaje creado", Toast.LENGTH_SHORT).show()
                            dialog.dismiss()
                        }
                    }
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
                    AdventureJournal.selectedCharacterId = -1L
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

    private fun loadSkillsInSpinners(dialogView: View) {
        // Obtener las referencias de los spinners de aprskill
        val aprSkillSpinner1 = dialogView.findViewById<Spinner>(R.id.spinner_aprskill1)
        val aprSkillSpinner2 = dialogView.findViewById<Spinner>(R.id.spinner_aprskill2)
        val aprSkillSpinner3 = dialogView.findViewById<Spinner>(R.id.spinner_aprskill3)
        val aprSkillSpinner4 = dialogView.findViewById<Spinner>(R.id.spinner_aprskill4)
        val aprSkillSpinner5 = dialogView.findViewById<Spinner>(R.id.spinner_aprskill5)
        val aprSkillSpinner6 = dialogView.findViewById<Spinner>(R.id.spinner_aprskill6)

        // Obtener todas las habilidades
        val allSkills = Skills.values().toList()

        // Filtrar las habilidades que no deseas incluir
        val filteredSkills = allSkills.filter { it != Skills.CRAFTING_2 && it != Skills.PERFORM_2 && it != Skills.CHOOSE && it != Skills.NONE }

        // Mapear las habilidades filtradas a sus etiquetas en español
        val skillLabels = filteredSkills.map { getSkillLabel(it) }

        val adapter = ArrayAdapter(this@Configuration, android.R.layout.simple_spinner_item, skillLabels)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        aprSkillSpinner1.adapter = adapter
        aprSkillSpinner2.adapter = adapter
        aprSkillSpinner3.adapter = adapter
        aprSkillSpinner4.adapter = adapter
        aprSkillSpinner5.adapter = adapter
        aprSkillSpinner6.adapter = adapter
    }

    private fun loadHumanBonusInSpinners(dialogView: View) {
        val humanBonusAttributeSpinner = dialogView.findViewById<Spinner>(R.id.spinner_hbattribute)
        val humanBonusSkillSpinner = dialogView.findViewById<Spinner>(R.id.spinner_hbskill)
        val humanBonusProficiencySpinner = dialogView.findViewById<Spinner>(R.id.spinner_hbproficiency)
        val halfElfBonusSkillSpinner = dialogView.findViewById<Spinner>(R.id.spinner_hebskill)
        val halfOrcBonusProficiencySpinner = dialogView.findViewById<Spinner>(R.id.spinner_hobproficiency)

        val attributes = arrayOf("Fuerza", "Destreza", "Constitución", "Inteligencia", "Sabiduría", "Carisma")

        // Obtener todas las habilidades
        val allSkills = Skills.values().toList()

        // Filtrar las habilidades que no deseas incluir
        val filteredSkills = allSkills.filter { it != Skills.CRAFTING_2 && it != Skills.PERFORM_2 && it != Skills.CHOOSE && it != Skills.NONE }

        // Mapear las habilidades filtradas a sus etiquetas en español
        val skillLabels = filteredSkills.map { getSkillLabel(it) }

        // Obtener todas las competencias
        val allProficiencies = Proficiencies.values().toList()

        // Filtrar las habilidades que no deseas incluir
        val filteredProficiencies = allProficiencies.filter { it != Proficiencies.CHOOSE && it != Proficiencies.NONE }

        // Mapear las competencias filtradas a sus etiquetas en español
        val proficienciesLabels = filteredProficiencies.map { getProficiencyLabel(it) }

        val attributeAdapter = ArrayAdapter(this@Configuration, android.R.layout.simple_spinner_item, attributes)
        attributeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val skillAdapter = ArrayAdapter(this@Configuration, android.R.layout.simple_spinner_item, skillLabels)
        skillAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val proficiencyAdapter = ArrayAdapter(this@Configuration, android.R.layout.simple_spinner_item, proficienciesLabels)
        proficiencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        humanBonusAttributeSpinner.adapter = attributeAdapter
        humanBonusSkillSpinner.adapter = skillAdapter
        halfElfBonusSkillSpinner.adapter = skillAdapter
        humanBonusProficiencySpinner.adapter = proficiencyAdapter
        halfOrcBonusProficiencySpinner.adapter = proficiencyAdapter
    }

    private fun getSkillLabel(skill: Skills): String {
        return when (skill) {
            Skills.AGILITY -> "Agilidad"
            Skills.CRAFTING_1 -> "Artesanía"
            Skills.CRAFTING_2 -> "Artesanía 2"
            Skills.ATHLETICS -> "Atletismo"
            Skills.SPOT -> "Avistar"
            Skills.SEARCH -> "Buscar"
            Skills.CONCENTRATION -> "Concentración"
            Skills.MANUAL_DEXTERITY -> "Destreza Manual"
            Skills.EMPATHY -> "Empatía"
            Skills.LISTEN -> "Escuchar"
            Skills.PERFORM_1 -> "Interpretar"
            Skills.PERFORM_2 -> "Interpretar 2"
            Skills.PERSUASION -> "Labia"
            Skills.MOUNT -> "Montar"
            Skills.PILOT -> "Pilotar"
            Skills.HEAL -> "Sanar"
            Skills.STEALTH -> "Sigilo"
            Skills.SURVIVAL -> "Supervivencia"
            Skills.CHOOSE -> "Elegir"
            Skills.NONE -> "Ninguna"
        }
    }

    private fun getProficiencyLabel(proficiencies: Proficiencies): String {
        return when (proficiencies) {
            Proficiencies.UNARMED -> "Desarmado"
            Proficiencies.DAGGERS -> "Dagas"
            Proficiencies.MACES -> "Mazas"
            Proficiencies.AXES -> "Hachas"
            Proficiencies.SWORDS -> "Espadas"
            Proficiencies.RAPIERS -> "Estoques"
            Proficiencies.WHIPS -> "Látigos"
            Proficiencies.SPEARS -> "Lanzas"
            Proficiencies._2HMACES -> "Mazas 2M"
            Proficiencies._2HAXES -> "Hachas 2M"
            Proficiencies._2HSWORDS -> "Espadas 2M"
            Proficiencies.DOUBLE_WEAPONS -> "Armas Dobles"
            Proficiencies.THROWING_WEAPONS -> "Armas Arrojadizas"
            Proficiencies.BOWS -> "Arcos"
            Proficiencies.CROSSBOWS -> "Ballestas"
            Proficiencies.SHORT_FIREARMS -> "Armas de Fuego C."
            Proficiencies.LONG_FIREARMS -> "Armas de Fuego L."
            Proficiencies.PROJECTILE_SPELLS -> "Hechizos Proyectil"
            Proficiencies.RAY_SPELLS -> "Hechizos Rayo"
            Proficiencies.CHOOSE -> "Choose"
            Proficiencies.NONE -> "None"
        }
    }
}