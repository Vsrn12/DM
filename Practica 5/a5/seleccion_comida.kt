/*
 * Descripción: Fragment para seleccionar el tipo de comida del pedido
 * Autor: Delgado Chipana Piero Adrián
 * Fecha creación: 2025-10-2
 * Fecha última modificación: 2025-10-2
 */

package com.example.a5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener

class SeleccionComidaFragment : Fragment() {

    private lateinit var rgComidas: RadioGroup
    private lateinit var rbPizza: RadioButton
    private lateinit var rbHamburguesa: RadioButton
    private lateinit var rbEnsalada: RadioButton
    private lateinit var btnSiguiente: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_seleccion_comida, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        inicializarVistas(view)
        configurarFragmentResultListener()
        configurarEventos()
    }

    private fun inicializarVistas(view: View) {
        rgComidas = view.findViewById(R.id.rgComidas)
        rbPizza = view.findViewById(R.id.rbPizza)
        rbHamburguesa = view.findViewById(R.id.rbHamburguesa)
        rbEnsalada = view.findViewById(R.id.rbEnsalada)
        btnSiguiente = view.findViewById(R.id.btnSiguiente)
    }

    private fun configurarFragmentResultListener() {
        setFragmentResultListener("pedido_editado") { _, bundle ->
            val comida = bundle.getString("comida", "")
            when (comida) {
                "Pizza" -> rbPizza.isChecked = true
                "Hamburguesa" -> rbHamburguesa.isChecked = true
                "Ensalada" -> rbEnsalada.isChecked = true
            }
        }
    }

    private fun configurarEventos() {
        btnSiguiente.setOnClickListener {
            val comidaSeleccionada = when (rgComidas.checkedRadioButtonId) {
                R.id.rbPizza -> "Pizza"
                R.id.rbHamburguesa -> "Hamburguesa"
                R.id.rbEnsalada -> "Ensalada"
                else -> null
            }

            if (comidaSeleccionada != null) {
                val fragment = SeleccionExtrasFragment()
                fragment.arguments = bundleOf("comida" to comidaSeleccionada)
                (activity as MainActivity).cargarFragment(fragment)
            } else {
                Toast.makeText(requireContext(), "Selecciona una comida", Toast.LENGTH_SHORT).show()
            }
        }
    }
}