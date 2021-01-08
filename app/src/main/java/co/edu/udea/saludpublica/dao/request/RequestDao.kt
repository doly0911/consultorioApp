package co.edu.udea.saludpublica.dao.request

import androidx.lifecycle.LiveData
import androidx.room.*
import co.edu.udea.saludpublica.models.Request
import co.edu.udea.saludpublica.models.UserxRequest

@Dao
interface RequestDao {

    @Insert
    fun insert(request: Request)

    @Query(value = "SELECT * from request_table ORDER BY requestId DESC")
    fun getAll(): LiveData<List<Request>>

    @Update
    fun update(request: Request)

    @Query(value = "DELETE from request_table where requestId = :key")
    fun delete(key:Long)

    @Transaction
    @Query("SELECT * FROM user_table WHERE userId = :userId LIMIT 1")
    fun getUserByRequest(userId : Long): LiveData<UserxRequest?>
}