package com.example.solicitudeshttp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
            val Button_validarRed = findViewById<Button>(R.id.button_validarRed)
            Button_validarRed.setOnClickListener{
                //Código para validar si hay conexión a internet
                if (Network.hayRed(this)){
                    Toast.makeText(this, "Si hay red", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "Asegúrate de que tengas conexión a una red", Toast.LENGTH_SHORT).show()


                }

            }
    }
}