package com.example.diario_aventura.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import com.example.diario_aventura.R

class Combate : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pnt_combate)

        var editTextArray = arrayOf<EditText>(
            findViewById(R.id.etxt_u_desarmado),
            findViewById(R.id.etxt_u_dagas),
            findViewById(R.id.etxt_u_estoques),
            findViewById(R.id.etxt_u_latigos),
            findViewById(R.id.etxt_u_mazas),
            findViewById(R.id.etxt_u_hachas),
            findViewById(R.id.etxt_u_espadas),
            findViewById(R.id.etxt_u_lanzas),
            findViewById(R.id.etxt_u_mazas2M),
            findViewById(R.id.etxt_u_hachas2M),
            findViewById(R.id.etxt_u_espadas2M),
            findViewById(R.id.etxt_u_armasdobles),
            findViewById(R.id.etxt_u_arcos),
            findViewById(R.id.etxt_u_ballestas),
            findViewById(R.id.etxt_u_arrojadizas),
            findViewById(R.id.etxt_u_arfuegocortas),
            findViewById(R.id.etxt_u_arfuegolargas),
            findViewById(R.id.etxt_u_hproyectil),
            findViewById(R.id.etxt_u_hrayo)
        )

        val btnEditarUsos = findViewById<ImageButton>(R.id.btn_editarusos_c)

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