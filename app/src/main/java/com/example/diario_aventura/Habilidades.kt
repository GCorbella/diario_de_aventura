package com.example.diario_aventura

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton

class Habilidades() : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pnt_habilidades)

        var editTextArray = arrayOf<EditText>(
            findViewById(R.id.etxt_u_agilidad),
            findViewById(R.id.etxt_u_artesania1),
            findViewById(R.id.etxt_u_artesania2),
            findViewById(R.id.etxt_u_atletismo),
            findViewById(R.id.etxt_u_avistar),
            findViewById(R.id.etxt_u_buscar),
            findViewById(R.id.etxt_u_concentracion),
            findViewById(R.id.etxt_u_des_manual),
            findViewById(R.id.etxt_u_empatia),
            findViewById(R.id.etxt_u_escuchar),
            findViewById(R.id.etxt_u_interpretar1),
            findViewById(R.id.etxt_u_interpretar2),
            findViewById(R.id.etxt_u_labia),
            findViewById(R.id.etxt_u_montar),
            findViewById(R.id.etxt_u_pilotar),
            findViewById(R.id.etxt_u_sanar),
            findViewById(R.id.etxt_u_sigilo),
            findViewById(R.id.etxt_u_supervivencia)
        )

        val btnEditarUsos = findViewById<ImageButton>(R.id.btn_editarusos_h)

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