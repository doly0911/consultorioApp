package co.edu.udea.saludpublica.dao.usuario

interface UsuarioDao {
    fun autenticar(correo : String, password : String) : Boolean
}