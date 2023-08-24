package com.example.diario_aventura.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.example.diario_aventura.R

class ResumenFicha : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pnt_resumen_ficha)

        var i = 0;

        var txtValAtributosArray = arrayOf<TextView>(
            findViewById(R.id.txt_val_fue),
            findViewById(R.id.txt_val_des),
            findViewById(R.id.txt_val_con),
            findViewById(R.id.txt_val_int),
            findViewById(R.id.txt_val_sab),
            findViewById(R.id.txt_val_car)
        )

        var txtModAtributosArray = arrayOf<TextView>(
            findViewById(R.id.txt_mod_fue),
            findViewById(R.id.txt_mod_des),
            findViewById(R.id.txt_mod_con),
            findViewById(R.id.txt_mod_int),
            findViewById(R.id.txt_mod_sab),
            findViewById(R.id.txt_mod_car)
        )


        //Botones
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

        //Calculos
        for (txtMod in txtModAtributosArray) {
            if (((txtValAtributosArray[i].text.toString().toInt() - 10)/2) >= 0) {
                txtMod.text = "+" + ((txtValAtributosArray[i].text.toString().toInt() - 10) / 2).toString()
                i++
            } else {
                txtMod.text = ((txtValAtributosArray[i].text.toString().toInt() - 10) / 2).toString()
                i++
            }
            if (i == 6) {
                i = 0
            }
        }
    }
}