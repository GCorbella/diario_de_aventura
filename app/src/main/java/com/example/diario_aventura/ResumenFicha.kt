package com.example.diario_aventura

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.example.diario_aventura.R

class ResumenFicha : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pnt_resumen_ficha)

        val btnHabilidades = findViewById<Button>(R.id.btn_habilidades)
        btnHabilidades.setOnClickListener {
            val intent = Intent(this, Habilidades::class.java)
            startActivity(intent)
        }

        val btnCombate = findViewById<Button>(R.id.btn_combate)
        btnCombate.setOnClickListener {
            val intent = Intent(this, Combate::class.java)
            startActivity(intent)
        }

        val btnInventario = findViewById<Button>(R.id.btn_inventario)
        btnInventario.setOnClickListener {
            val intent = Intent(this, Inventario::class.java)
            startActivity(intent)
        }

        val btnConfiguracionPJ = findViewById<ImageButton>(R.id.btn_confpj)
        btnConfiguracionPJ.setOnClickListener {
            val intent = Intent(this, ConfiguracionPJ::class.java)
            startActivity(intent)
        }
    }
}