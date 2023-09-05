package com.example.diario_aventura.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import com.example.diario_aventura.R

class Skills() : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pnt_skills)

        var editTextArray = arrayOf<EditText>(
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

        val btnEditarUsos = findViewById<ImageButton>(R.id.btn_edituses_h)

        btnEditarUsos.setOnClickListener {
            // Cambia el estado de edición del EditText
            for (eTxt in editTextArray) {
                eTxt.isEnabled = !eTxt.isEnabled

                if (!eTxt.isEnabled) {
                    //Guardar en base de datos
                }
            }
        }
    }
}