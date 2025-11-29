/*
 * Descripción: Adapter para mostrar lista de insumos con nombre y precio
 * Autor: Delgado Chipana Piero Adrián
 * Fecha creación: 2024-11-20
 * Fecha última modificación: 2024-11-28
 */

package com.example.afinal

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class InsumosAdapter(
    private val context: Context,
    private val insumos: List<Insumo>
) : BaseAdapter() {

    override fun getCount(): Int = insumos.size

    override fun getItem(position: Int): Any = insumos[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_insumo, parent, false)

        val insumo = insumos[position]
        
        val tvNombre = view.findViewById<TextView>(R.id.tvNombreInsumo)
        val tvPrecio = view.findViewById<TextView>(R.id.tvPrecioInsumo)

        tvNombre.text = insumo.nombre
        tvPrecio.text = "S/. ${String.format("%.2f", insumo.precio)}"

        return view
    }
}