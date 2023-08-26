package com.example.diario_aventura.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import com.example.diario_aventura.R

class Combat : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pnt_combat)

        var editTextArray = arrayOf<EditText>(
            findViewById(R.id.etxt_u_unarmed),
            findViewById(R.id.etxt_u_daggers),
            findViewById(R.id.etxt_u_rapiers),
            findViewById(R.id.etxt_u_whips),
            findViewById(R.id.etxt_u_maces),
            findViewById(R.id.etxt_u_axes),
            findViewById(R.id.etxt_u_swords),
            findViewById(R.id.etxt_u_spears),
            findViewById(R.id.etxt_u_2hMaces),
            findViewById(R.id.etxt_u_2hAxes),
            findViewById(R.id.etxt_u_2hSwords),
            findViewById(R.id.etxt_u_doubleWeapons),
            findViewById(R.id.etxt_u_bows),
            findViewById(R.id.etxt_u_crossbows),
            findViewById(R.id.etxt_u_throwingW),
            findViewById(R.id.etxt_u_shortFirearms),
            findViewById(R.id.etxt_u_longFirearms),
            findViewById(R.id.etxt_u_projectileS),
            findViewById(R.id.etxt_u_rayS)
        )

        val btnEditarUsos = findViewById<ImageButton>(R.id.btn_edituses_c)

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