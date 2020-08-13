package com.example.solicitudeshttp
import android.app.DownloadManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import okhttp3.Call
import okhttp3.OkHttpClient
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity(), CompletadoListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val Button_validarRed = findViewById<Button>(R.id.button_validarRed)
        val b_solicitudhttp = findViewById<Button>(R.id.b_solicitudhttp)
        val bVolley = findViewById<Button>(R.id.bVolley)
        val bOk = findViewById<Button>(R.id.bVolley)
        Button_validarRed.setOnClickListener {
            //Código para validar si hay conexión a internet
            if (Network.hayRed(this)) {
                Toast.makeText(this, "Si hay red", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(
                    this,
                    "Asegúrate de que tengas conexión a una red",
                    Toast.LENGTH_SHORT
                ).show()


            }

        }

        b_solicitudhttp.setOnClickListener {
            if (Network.hayRed(this)) {
                // Log.d("b_solicitudOnClick", descargardatos("http://www.google.com"))
                DescargaURL(this).execute("http://www.google.com")
            } else {
                Toast.makeText(
                    this,
                    "Asegúrate de que tengas conexión a una red",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        bVolley.setOnClickListener {
            if (Network.hayRed(this)) {
                solicitudesHTTP("https://www.google.com")
            } else {
                Toast.makeText(
                    this,
                    "Asegúrate de que tengas conexión a una red",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
        bOk.setOnClickListener {
            if (Network.hayRed(this)){
                solicitudHTTPOkHTTP("https://www.google.com")
            }else{
                Toast.makeText(this, "Asegúrate de que tengas conexión a una red", Toast.LENGTH_SHORT).show()
            }
        }
    }
    //Método Volley
    private fun solicitudesHTTP(url: String) {
        val queve = Volley.newRequestQueue(this)
        val solicitud =
            StringRequest(Request.Method.GET, url, Response.Listener<String> { response ->
                try {
                    Log.d("SolicitudHTTPVolley", response)
                } catch (e: Exception) {

                }
            }, Response.ErrorListener { })
        queve.add(solicitud)
    }
    //Método OKHTTP
    private fun solicitudHTTPOkHTTP(url:String){
        val cliente = OkHttpClient()
        val solicitud = okhttp3.Request.Builder().url(url).build()
        cliente.newCall(solicitud).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                //Manejo de errores
            }

            override fun onResponse(call: Call?, response: okhttp3.Response) {
                    val resultado = response.body().string()
                this@MainActivity.runOnUiThread{
                    try {
                        Log.d("SolictudHTTPOkHTTP", resultado)
                    }catch (e:Exception){

                    }
                }
            }
        })
    }


    override fun descargaCompletada(resultado: String) {
        Log.d("descargaCompleta", resultado)
    }

}