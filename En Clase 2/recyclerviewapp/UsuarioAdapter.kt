package com.example.recyclerviewapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewapp.R
import com.example.recyclerviewapp.Usuario

// Se requiere el import de 'android.view.View' y otros componentes
import android.widget.Toast

// Se define el adapter y se expone la lista de usuarios para su gestión externa (opcional)
class UsuarioAdapter(
    val usuarios: ArrayList<Usuario>
) : RecyclerView.Adapter<UsuarioAdapter.UsuarioViewHolder>() {

    // 1. Callbacks (listeners) para que el Fragment maneje la edición y eliminación a nivel de datos
    private lateinit var onUserEdit: (Int, Usuario) -> Unit
    private lateinit var onUserDelete: (Int) -> Unit

    fun setOnUserEditListener(listener: (Int, Usuario) -> Unit) {
        onUserEdit = listener
    }

    fun setOnUserDeleteListener(listener: (Int) -> Unit) {
        onUserDelete = listener
    }

    // Métodos de gestión de datos que serán llamados desde el Fragment
    fun addUser(usuario: Usuario) {
        usuarios.add(usuario)
        notifyItemInserted(usuarios.size - 1)
    }

    fun deleteUser(position: Int) {
        if (position >= 0 && position < usuarios.size) {
            usuarios.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun editUser(position: Int, updatedUser: Usuario) {
        if (position >= 0 && position < usuarios.size) {
            usuarios[position] = updatedUser
            notifyItemChanged(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuarioViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return UsuarioViewHolder(layoutInflater.inflate(R.layout.item_usuario, parent, false))
    }

    override fun getItemCount(): Int = usuarios.size

    override fun onBindViewHolder(holder: UsuarioViewHolder, position: Int) {
        holder.render(usuarios[position], onUserEdit, onUserDelete)
    }

    // --- INNER CLASS: UsuarioViewHolder ---
    inner class UsuarioViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val txtNombre: TextView = view.findViewById(R.id.txtNombre)
        private val txtEdad: TextView = view.findViewById(R.id.txtEdad)
        private val txtCorreo: TextView = view.findViewById(R.id.txtCorreo)
        private val btnEliminar: ImageButton = view.findViewById(R.id.btnEliminar)

        fun render(
            usuario: Usuario,
            onUserEdit: (Int, Usuario) -> Unit,
            onUserDelete: (Int) -> Unit
        ) {
            txtNombre.text = usuario.nombre
            txtEdad.text = "Edad: ${usuario.edad}"
            txtCorreo.text = usuario.email

            btnEliminar.setOnClickListener {
                val pos = bindingAdapterPosition
                if (pos != RecyclerView.NO_POSITION) {
                    onUserDelete(pos)
                }
            }

            // --- Implementación de Long Click (Paso 3) ---
            itemView.setOnLongClickListener {
                val pos = bindingAdapterPosition
                if (pos != RecyclerView.NO_POSITION) {
                    val context = itemView.context
                    AlertDialog.Builder(context)
                        .setTitle("Acción")
                        .setItems(arrayOf("Editar", "Eliminar")) { _, which ->
                            when (which) {
                                0 -> showEditDialog(usuario, pos, onUserEdit) // Muestra el diálogo de edición
                                1 -> onUserDelete(pos) // Llama al callback de eliminación
                            }
                        }
                        .show()
                }
                true // Consume el evento
            }
        }

        private fun showEditDialog(
            usuario: Usuario,
            position: Int,
            onUserEdit: (Int, Usuario) -> Unit
        ) {
            val context = itemView.context
            val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_edit_usuario, null)

            val editNombre = dialogView.findViewById<EditText>(R.id.editNombre)
            val editEdad = dialogView.findViewById<EditText>(R.id.editEdad)
            val editCorreo = dialogView.findViewById<EditText>(R.id.editCorreo)

            editNombre.setText(usuario.nombre)
            editEdad.setText(usuario.edad.toString())
            editCorreo.setText(usuario.email)

            AlertDialog.Builder(context)
                .setTitle("Editar usuario")
                .setView(dialogView)
                .setPositiveButton("Guardar") { _, _ ->
                    val newNombre = editNombre.text.toString()
                    val newEdad = editEdad.text.toString().toIntOrNull() ?: usuario.edad
                    val newCorreo = editCorreo.text.toString()

                    val updatedUser = usuario.copy(
                        nombre = newNombre,
                        edad = newEdad,
                        email = newCorreo
                    )

                    onUserEdit(position, updatedUser)
                    Toast.makeText(context, "Guardado: ${newNombre}", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("Cancelar", null)
                .show()
        }
    }
}