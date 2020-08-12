package com.example.solicitudeshttp

import android.os.AsyncTask
import android.os.StrictMode
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class DescargaURL(var completadoListener: CompletadoListener?): AsyncTask<String, Void, String>() {
    override fun doInBackground(vararg params: String): String? {
        try {
            return descargardatos(params[0])
        }catch (e:IOException){
            return null
        }
    }

    override fun onPostExecute(result: String) {
        try {
            completadoListener?.descargaCompletada(result)
        }catch(e:Exception){

        }

    }
    @Throws(IOException::class)
    private fun descargardatos(url:String):String{
        var InputStream: InputStream? = null
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