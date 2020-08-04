package com.example.solicitudeshttp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.widget.Button
import android.widget.Toast
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
            val Button_validarRed = findViewById<Button>(R.id.button_validarRed)
            val b_solicitudhttp = findViewById<Button>(R.id.b_solicitudhttp)
            Button_validarRed.setOnClickListener{
                //Código para validar si hay conexión a internet
                if (Network.hayRed(this)){
                    Toast.makeText(this, "Si hay red", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "Asegúrate de que tengas conexión a una red", Toast.LENGTH_SHORT).show()


                }

            }

        b_solicitudhttp.setOnClickListener {
            if (Network.hayRed(this)){
                Log.d("bSolicitudOnClick", descargardatos("http://www.google.com"))
            }else{
                Toast.makeText(this, "Asegúrate de que tengas conexión a una red", Toast.LENGTH_SHORT).show()
            }
        }
    }
    @Throws(IOException::class)
    private fun descargardatos(url:String):String{
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        var InputStream:InputStream? = null
        try {
            val url = URL(url)
            val con = url.openConnection() as HttpURLConnection
            con.requestMethod = "GET"
            con.connect()

            InputStream = con.inputStream
            return InputStream.bufferedReader().use {
                it.readText()
            }
        }finally {
            if (InputStream != null){
                InputStream.close()
            }
        }
    }
}