/*
 * Descripción: Fragment para mostrar el resumen completo del pedido con opciones de confirmar o editar
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
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult

class ResumenPedidoFragment : Fragment() {

    private lateinit var tvResumenPedido: TextView
    private lateinit var btnConfirmarPedido: Button
    private lateinit var btnEditarPedido: Button
    
    private var comida: String = ""
    private var extras: String = ""
    private var bebida: Boolean = false
    private var papas: Boolean = false
    private var postre: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_resumen_pedido, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        inicializarVistas(view)
        obtenerDatos()
        mostrarResumen()
        configurarEventos()
    }

    private fun inicializarVistas(view: View) {
        tvResumenPedido = view.findViewById(R.id.tvResumenPedido)
        btnConfirmarPedido = view.findViewById(R.id.btnConfirmarPedido)
        btnEditarPedido = view.findViewById(R.id.btnEditarPedido)
    }

    private fun obtenerDatos() {
        comida = arguments?.getString("comida") ?: "Sin seleccionar"
        extras = arguments?.getString("extras") ?: "Sin extras"
        bebida = arguments?.getBoolean("bebida", false) ?: false
        papas = arguments?.getBoolean("papas", false) ?: false
        postre = arguments?.getBoolean("postre", false) ?: false
    }

    private fun mostrarResumen() {
        val resumen = """
             RESUMEN DE TU PEDIDO
            
             Comida Principal:
            $comida
            
             Extras Seleccionados:
            ${if (extras.isNotEmpty() && extras != "Sin extras") extras else "Ninguno"}
            
            ¿Todo está correcto?
        """.trimIndent()
        
        tvResumenPedido.text = resumen
    }

    private fun configurarEventos() {
        btnConfirmarPedido.setOnClickListener {
            Toast.makeText(
                requireContext(),
                " Pedido confirmado exitosamente",
                Toast.LENGTH_LONG
            ).show()
            
            (activity as MainActivity).cargarFragmentSinBackStack(InicioFragment())
        }

        btnEditarPedido.setOnClickListener {
            val bundle = bundleOf(
                "comida" to comida,
                "bebida" to bebida,
                "papas" to papas,
                "postre" to postre
            )
            
            setFragmentResult("pedido_editado", bundle)
            
            parentFragmentManager.popBackStack()
            parentFragmentManager.popBackStack()
        }
    }
}