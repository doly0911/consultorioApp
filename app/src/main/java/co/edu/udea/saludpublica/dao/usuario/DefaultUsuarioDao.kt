package co.edu.udea.saludpublica.dao.usuario

import co.edu.udea.saludpublica.models.Usuario
import kotlin.collections.ArrayList

class DefaultUsuarioDao : UsuarioDao {
    override fun autenticar(correo: String, password: String): Boolean {
        val usuarios = getUsuarios()
         return usuarios.any {
            it.correo == correo && it.password == password
        }
    }

    private fun getUsuarios() : List<Usuario>{
        val usuarios = ArrayList<Usuario>()
        usuarios.add(Usuario("dolly.jimenez@udea.edu.co","123"))
        return usuarios
    }
}