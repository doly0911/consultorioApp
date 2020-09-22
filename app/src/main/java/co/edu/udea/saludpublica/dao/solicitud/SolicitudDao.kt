package co.edu.udea.saludpublica.dao.solicitud

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import co.edu.udea.saludpublica.models.Solicitud

@Dao
interface SolicitudDao {

    @Insert
    fun insert(solicitud: Solicitud)

    @Query(value = "SELECT * from solicitud_table ORDER BY solicitudId DESC")
    fun getAll():LiveData<List<Solicitud>>

    @Update
    fun update(solicitud: Solicitud)

    @Query(value = "DELETE from solicitud_table where solicitudId = :key")
    fun delete(key:Long)
}