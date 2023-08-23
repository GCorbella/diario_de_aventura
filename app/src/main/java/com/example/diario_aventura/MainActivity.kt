package com.example.diario_aventura

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) 
        
        val btnConfiguracion = findViewById<ImageButton>(R.id.btn_conf)
        btnConfiguracion.setOnClickListener { 
            val intent = Intent(this, Configuracion::class.java)
            startActivity(intent)
        }

        val btnFicha = findViewById<Button>(R.id.btn_ficha)
        btnFicha.setOnClickListener {
            val intent = Intent(this, ResumenFicha::class.java)
            startActivity(intent)
        }
    }
}