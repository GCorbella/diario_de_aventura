package com.example.diario_aventura.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.example.diario_aventura.R

class SheetResume : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pnt_sheet_resume)

        var i = 0

        val txtAbilitiesValArray = arrayOf<TextView>(
            findViewById(R.id.txt_val_fue),
            findViewById(R.id.txt_val_des),
            findViewById(R.id.txt_val_con),
            findViewById(R.id.txt_val_int),
            findViewById(R.id.txt_val_sab),
            findViewById(R.id.txt_val_car)
        )

        val txtAbilitiesModArray = arrayOf<TextView>(
            findViewById(R.id.txt_mod_fue),
            findViewById(R.id.txt_mod_des),
            findViewById(R.id.txt_mod_con),
            findViewById(R.id.txt_mod_int),
            findViewById(R.id.txt_mod_sab),
            findViewById(R.id.txt_mod_car)
        )


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

        //Calculations
        for (txtMod in txtAbilitiesModArray) {
            if (((txtAbilitiesValArray[i].text.toString().toInt() - 10)/2) >= 0) {
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

    override fun onResume() {
        super.onResume()

        // Show global variable in a Toast
        /*val toastMessage = "Personaje seleccionado: ${AdventureJournal.personajeSeleccionadoId}"
        Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show()
        */
    }
}