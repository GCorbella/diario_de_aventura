package com.example.diario_aventura.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.example.diario_aventura.DataStoreManager
import com.example.diario_aventura.AdventureJournal
import com.example.diario_aventura.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Obtener el personaje seleccionado desde DataStore y asignarlo a la variable global
        val context = this
        CoroutineScope(Dispatchers.IO).launch {
            val selectedCharacterId: Int? = DataStoreManager.getSelectedCharacterId(context).first()

            // Si el valor es diferente de null y de -1, lo asignamos a la variable global
            if (selectedCharacterId != null && selectedCharacterId != -1) {
                AdventureJournal.selectedCharacterId = selectedCharacterId
            }
        }

        val btnConfiguration = findViewById<ImageButton>(R.id.btn_conf)
        btnConfiguration.setOnClickListener {
            val intent = Intent(this, Configuration::class.java)
            startActivity(intent)
        }

        val btnCrSheet = findViewById<Button>(R.id.btn_sheet)
        btnCrSheet.setOnClickListener {
            val intent = Intent(this, SheetResume::class.java)
            startActivity(intent)
        }

        updateCharacterName()
    }

    override fun onResume() {
        super.onResume()
        updateCharacterName()
    }

    private fun updateCharacterName() {
        val context = this
        CoroutineScope(Dispatchers.IO).launch {
            val selectedCharacterId: Int? = DataStoreManager.getSelectedCharacterId(context).first()

            if (selectedCharacterId != null && selectedCharacterId != -1) {
                val db = (application as AdventureJournal).db
                val selectedCharacter = db.characterDao().getCharacterById(selectedCharacterId)

                runOnUiThread {
                    val txtJournalOf = findViewById<TextView>(R.id.txt_journalOf)
                    if (selectedCharacter != null) {
                        val journalText = getString(R.string.journal_of_character, selectedCharacter.name)
                        txtJournalOf.text = journalText
                    } else {
                        txtJournalOf.text = getString(R.string.journal_of_character, "xxxxxxxx")
                    }
                }
            } else {
                runOnUiThread {
                    val txtJournalOf = findViewById<TextView>(R.id.txt_journalOf)
                    val journalText = getString(R.string.journal_of_character, "xxxxxxxx")
                    txtJournalOf.text = journalText
                }
            }
        }
    }

}