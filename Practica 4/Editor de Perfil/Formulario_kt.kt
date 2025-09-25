/*
 * Descripción: Editor de perfil con confirmación - Captura datos del usuario
 * Autor: Delgado Chipana Piero Adrián
 * Fecha creación: 2025-09-24
 * Fecha última modificación: 2025-09-24
 */

package com.example.editorperfil

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import java.io.Serializable

data class Usuario(
    val nombre: String,
    val edad: String,
    val ciudad: String,
    val correo: String
) : Serializable

class FormularioActivity : AppCompatActivity() {

    private lateinit var etNombre: EditText
    private lateinit var etEdad: EditText
    private lateinit var etCiudad: EditText
    private lateinit var etCorreo: EditText
    private lateinit var btnContinuar: Button

    private val resumenLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val confirmed = result.data?.getBooleanExtra("confirmed", false) ?: false
            if (confirmed) {
                Toast.makeText(this, "Perfil guardado correctamente", Toast.LENGTH_LONG).show()
                limpiarCampos()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario)

        inicializarVistas()
        configurarEventos()
    }

    private fun inicializarVistas() {
        etNombre = findViewById(R.id.etNombre)
        etEdad = findViewById(R.id.etEdad)
        etCiudad = findViewById(R.id.etCiudad)
        etCorreo = findViewById(R.id.etCorreo)
        btnContinuar = findViewById(R.id.btnContinuar)
    }

    private fun configurarEventos() {
        btnContinuar.setOnClickListener {
            if (validarCampos()) {
                val usuario = Usuario(
                    nombre = etNombre.text.toString().trim(),
                    edad = etEdad.text.toString().trim(),
                    ciudad = etCiudad.text.toString().trim(),
                    correo = etCorreo.text.toString().trim()
                )

                val intent = Intent(this, ResumenActivity::class.java)
                intent.putExtra("usuario", usuario)
                resumenLauncher.launch(intent)
            }
        }
    }

    private fun validarCampos(): Boolean {
        return when {
            etNombre.text.toString().trim().isEmpty() -> {
                etNombre.error = "El nombre es requerido"
                etNombre.requestFocus()
                false
            }
            etEdad.text.toString().trim().isEmpty() -> {
                etEdad.error = "La edad es requerida"
                etEdad.requestFocus()
                false
            }
            etCiudad.text.toString().trim().isEmpty() -> {
                etCiudad.error = "La ciudad es requerida"
                etCiudad.requestFocus()
                false
            }
            etCorreo.text.toString().trim().isEmpty() -> {
                etCorreo.error = "El correo es requerido"
                etCorreo.requestFocus()
                false
            }
            else -> true
        }
    }

    private fun limpiarCampos() {
        etNombre.setText("")
        etEdad.setText("")
        etCiudad.setText("")
        etCorreo.setText("")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("nombre", etNombre.text.toString())
        outState.putString("edad", etEdad.text.toString())
        outState.putString("ciudad", etCiudad.text.toString())
        outState.putString("correo", etCorreo.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        etNombre.setText(savedInstanceState.getString("nombre", ""))
        etEdad.setText(savedInstanceState.getString("edad", ""))
        etCiudad.setText(savedInstanceState.getString("ciudad", ""))
        etCorreo.setText(savedInstanceState.getString("correo", ""))
    }
}