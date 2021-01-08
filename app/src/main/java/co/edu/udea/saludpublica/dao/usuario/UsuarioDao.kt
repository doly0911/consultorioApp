package co.edu.udea.saludpublica.dao.usuario

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import co.edu.udea.saludpublica.models.Usuario

@Dao
interface UsuarioDao {

    @Query(value = "SELECT * from usuario_table where correo = :correo AND password = :password LIMIT 1")
    fun autenticar(correo : String, password : String) : Usuario?

    @Insert
    fun insert(usuario: Usuario)

}