/*
 * Descripción: Fragment de inicio para comenzar un nuevo pedido de comida
 * Autor: Delgado Chipana Piero Adrián
 * Fecha creación: 2025-10-1
 * Fecha última modificación: 2025-10-2
 */

package com.example.a5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.Button


class InicioFragment : Fragment() {

    private lateinit var btnNuevoPedido: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_inicio, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        inicializarVistas(view)
        configurarEventos()
    }

    private fun inicializarVistas(view: View) {
        btnNuevoPedido = view.findViewById(R.id.btnNuevoPedido)
    }

    private fun configurarEventos() {
        btnNuevoPedido.setOnClickListener {
            (activity as MainActivity).cargarFragment(SeleccionComidaFragment())
        }
    }
}