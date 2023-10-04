package com.aristidevs.elementosvisualestarea

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView

class FormMandado : AppCompatActivity() {

    private lateinit var incomingIntent: Intent

    private lateinit var nombre: String
    private lateinit var correo: String
    private lateinit var telefono: String
    private lateinit var puesto: String
    private lateinit var algoritmos: String
    private lateinit var estructurada: String
    private lateinit var problemas: String
    private lateinit var comunicacion: String
    private lateinit var disponibilidad: String
    private lateinit var radioSi: String
    private lateinit var radioNo: String
    private lateinit var notificaciones: String

    private lateinit var lblResumen: TextView
    private lateinit var resumen: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_mandado)

        getDatos()
        initVar()
        genResumen()
    }

    fun getDatos(){
        incomingIntent = intent

        nombre = incomingIntent.getStringExtra("nombre").toString()
        correo = incomingIntent.getStringExtra("correo").toString()
        telefono = incomingIntent.getStringExtra("telefono").toString()
        puesto = incomingIntent.getStringExtra("puesto").toString()
        algoritmos = incomingIntent.getStringExtra("algoritmos").toString()
        estructurada = incomingIntent.getStringExtra("estructurada").toString()
        problemas = incomingIntent.getStringExtra("problemas").toString()
        comunicacion = incomingIntent.getStringExtra("comunicacion").toString()
        disponibilidad = incomingIntent.getStringExtra("disponibilidad").toString()

        radioSi = incomingIntent.getBooleanExtra("radioSi", false).toString()
        radioNo = incomingIntent.getBooleanExtra("radioNo", false).toString()
        notificaciones = incomingIntent.getBooleanExtra("notificaciones", false).toString()
    }

    fun initVar(){
        lblResumen = findViewById(R.id.cpResumen)
        resumen = ""
    }

    fun genResumen(){
        resumen += "Nombre: " + nombre + "\n\n"
        resumen += "E-Mail: " + correo + "\n\n"
        resumen += "Telefono: " + telefono + "\n\n"
        resumen += "Puesto aspirado: " + puesto + "\n\n"
        resumen += "Habilidades: " +
                (if (algoritmos.isNotEmpty()) algoritmos + ", " else "") +
                (if (estructurada.isNotEmpty()) estructurada + ", " else "") +
                (if (problemas.isNotEmpty()) problemas + ", " else "") +
                (if (comunicacion.isNotEmpty()) comunicacion else "") + "\n\n"
        resumen += "Disponibilidad: " + disponibilidad + "\n\n"
        resumen += "Experiencia previa: " + (if (radioSi == "true" || radioNo == "true") "true" else "false") + "\n\n"
        resumen += "Notificaciones: " + notificaciones

        lblResumen.setText(resumen)
    }
}