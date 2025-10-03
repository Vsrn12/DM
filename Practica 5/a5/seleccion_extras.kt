/*
 * Descripción: Fragment para seleccionar extras del pedido (bebida, papas, postre)
 * Autor: Delgado Chipana Piero Adrián
 * Fecha creación: 2025-10-1
 * Fecha última modificación: 2025-10-3
 */

package com.example.a5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener

class SeleccionExtrasFragment : Fragment() {

    private lateinit var cbBebida: CheckBox
    private lateinit var cbPapas: CheckBox
    private lateinit var cbPostre: CheckBox
    private lateinit var btnSiguiente: Button
    
    private var comidaSeleccionada: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_seleccion_extras, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        inicializarVistas(view)
        obtenerDatos()
        configurarFragmentResultListener()
        configurarEventos()
    }

    private fun inicializarVistas(view: View) {
        cbBebida = view.findViewById(R.id.cbBebida)
        cbPapas = view.findViewById(R.id.cbPapas)
        cbPostre = view.findViewById(R.id.cbPostre)
        btnSiguiente = view.findViewById(R.id.btnSiguiente)
    }

    private fun obtenerDatos() {
        comidaSeleccionada = arguments?.getString("comida") ?: ""
    }

    private fun configurarFragmentResultListener() {
        setFragmentResultListener("pedido_editado") { _, bundle ->
            comidaSeleccionada = bundle.getString("comida", "")
            cbBebida.isChecked = bundle.getBoolean("bebida", false)
            cbPapas.isChecked = bundle.getBoolean("papas", false)
            cbPostre.isChecked = bundle.getBoolean("postre", false)
        }
    }

    private fun configurarEventos() {
        btnSiguiente.setOnClickListener {
            val extrasSeleccionados = mutableListOf<String>()
            
            if (cbBebida.isChecked) extrasSeleccionados.add("Bebida")
            if (cbPapas.isChecked) extrasSeleccionados.add("Papas")
            if (cbPostre.isChecked) extrasSeleccionados.add("Postre")

            val fragment = ResumenPedidoFragment()
            fragment.arguments = bundleOf(
                "comida" to comidaSeleccionada,
                "extras" to extrasSeleccionados.joinToString(", "),
                "bebida" to cbBebida.isChecked,
                "papas" to cbPapas.isChecked,
                "postre" to cbPostre.isChecked
            )
            
            (activity as MainActivity).cargarFragment(fragment)
        }
    }
}