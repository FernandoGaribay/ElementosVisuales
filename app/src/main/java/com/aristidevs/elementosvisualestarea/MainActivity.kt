package com.aristidevs.elementosvisualestarea

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.Toast
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var nombre: EditText
    private lateinit var correo: EditText
    private lateinit var telefono: EditText
    private lateinit var puesto: AutoCompleteTextView
    private lateinit var radioSi: RadioButton
    private lateinit var radioNo: RadioButton
    private lateinit var algoritmos: CheckBox
    private lateinit var estructurada: CheckBox
    private lateinit var problemas: CheckBox
    private lateinit var comunicacion: CheckBox
    private lateinit var disponibilidad: EditText
    private lateinit var notificaciones: ToggleButton

    private lateinit var btnEnviar: Button

    private lateinit var opcionesPuestos: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initVar()
        initEvents()
        initAutoComplete()
        initSpinner()
    }

    fun initVar(){
        nombre = findViewById(R.id.cpNombre)
        correo = findViewById(R.id.cpCorreo)
        telefono = findViewById(R.id.cpTelefono)
        puesto = findViewById(R.id.cpPuesto)
        radioSi = findViewById(R.id.radioOptionSi)
        radioNo = findViewById(R.id.radioOptionNo)
        algoritmos = findViewById(R.id.desAlgoritmos)
        estructurada = findViewById(R.id.desEstructurada)
        problemas = findViewById(R.id.desProblemas)
        comunicacion = findViewById(R.id.desComunicacion)
        disponibilidad = findViewById(R.id.cpDisponibilidad)
        notificaciones = findViewById(R.id.toggleNotifi)

        opcionesPuestos = arrayOf(
            "Desarrollador de Software",
            "Ingeniero de Pruebas",
            "Diseñador de UX/UI",
            "Gerente de Proyecto",
            "Analista de Datos",
            "Arquitecto de Software",
            "Ingeniero de Seguridad",
            "Administrador de Bases de Datos",
            "Especialista en DevOps",
            "Scrum Master"
        )

        btnEnviar = findViewById(R.id.btnEnviar)
    }

    fun initEvents(){
        btnEnviar.setOnClickListener {
            if(camposVacios()){
                Toast.makeText(this, "Algunos campos se encuentran vacios, favor de completarlos", Toast.LENGTH_LONG).show()
            } else {
                    val intent = Intent(this, FormMandado::class.java)
                    intent.putExtra("nombre", nombre.text.toString())
                    intent.putExtra("correo", correo.text.toString())
                    intent.putExtra("telefono", telefono.text.toString())
                    intent.putExtra("puesto", puesto.text.toString())
                    intent.putExtra("disponibilidad", disponibilidad.text.toString())
                    intent.putExtra("radioSi", radioSi.isChecked)
                    intent.putExtra("radioNo", radioNo.isChecked)
                    intent.putExtra("notificaciones", notificaciones.isChecked)

                    intent.putExtra("algoritmos", if (algoritmos.isChecked) algoritmos.text.toString() else "")
                    intent.putExtra("estructurada", if (estructurada.isChecked) estructurada.text.toString() else "")
                    intent.putExtra("problemas", if (problemas.isChecked) problemas.text.toString() else "")
                    intent.putExtra("comunicacion", if (comunicacion.isChecked) comunicacion.text.toString() else "")

                startActivity(intent)
            }
        }
    }

    fun initAutoComplete(){
        val autoCompleteTextView = findViewById<AutoCompleteTextView>(R.id.cpPuesto)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, opcionesPuestos)
        autoCompleteTextView.setAdapter(adapter)
    }

    fun initSpinner(){
        val spinnerEducacion = findViewById<Spinner>(R.id.cpEducacion)
        val opcionesEducacion = arrayOf("Seleccione su nivel de educación", "Bachillerato", "Licenciatura", "Maestría", "Doctorado", "Otro")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, opcionesEducacion)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerEducacion.adapter = adapter

        spinnerEducacion.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }
    }

    fun camposVacios(): Boolean {
        /*if (nombre.text.isBlank() ||
            correo.text.isBlank() ||
            telefono.text.isBlank() ||
            puesto.text.isBlank() ||
            disponibilidad.text.isBlank()
        ) {
            return true
        }

        if (!radioSi.isChecked && !radioNo.isChecked) {
            return true
        }*/

        if (!(algoritmos.isChecked || estructurada.isChecked || problemas.isChecked || comunicacion.isChecked)) {
            return true
        }

        return false
    }

}