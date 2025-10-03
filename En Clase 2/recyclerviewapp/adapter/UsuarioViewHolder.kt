package com.example.recyclerviewapp.adapter

import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewapp.R
import com.example.recyclerviewapp.Usuario

class UsuarioViewHolder(view:View): RecyclerView.ViewHolder(view){

    private val txtNombre = view.findViewById<TextView>(R.id.txtNombre)
    private val txtEdad = view.findViewById<TextView>(R.id.txtEdad)
    private val txtCorreo = view.findViewById<TextView>(R.id.txtCorreo)
    private val btnDelete = view.findViewById<ImageButton>(R.id.btnEliminar)

    fun bind(user: Usuario, onDelete: (Int) -> Unit){
        txtNombre.text = user.nombre
        txtEdad.text = user.edad.toString()
        txtCorreo.text = user.email
        btnDelete.setOnClickListener {
            val pos = bindingAdapterPosition
            if( pos != RecyclerView.NO_POSITION)
            {
                onDelete(pos)
            }
        }
    }


}