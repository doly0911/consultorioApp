package co.edu.udea.saludpublica.dao.solicitud

import androidx.lifecycle.LiveData
import androidx.room.*
import co.edu.udea.saludpublica.models.Solicitud
import co.edu.udea.saludpublica.models.SolicitudesUsuario

@Dao
interface SolicitudDao {

    @Insert
    fun insert(solicitud: Solicitud)

    @Query(value = "SELECT * from solicitud_table ORDER BY solicitudId DESC")
    fun getAll(): LiveData<List<Solicitud>>

    @Update
    fun update(solicitud: Solicitud)

    @Query(value = "DELETE from solicitud_table where solicitudId = :key")
    fun delete(key:Long)

    @Transaction
    @Query("SELECT * FROM usuario_table WHERE usuarioId = :usuarioId LIMIT 1")
    fun getByUserId(usuarioId : Long): LiveData<SolicitudesUsuario?>
}