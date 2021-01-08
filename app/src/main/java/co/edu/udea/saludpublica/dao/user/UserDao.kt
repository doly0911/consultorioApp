package co.edu.udea.saludpublica.dao.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import co.edu.udea.saludpublica.models.User

@Dao
interface UserDao {

    @Query(value = "SELECT * from user_table where email = :email AND password = :password LIMIT 1")
    fun authenticate(email : String, password : String) : User?

    @Query(value = "SELECT * from user_table where userId = :userId LIMIT 1")
    fun getUser(userId : Long) : User?

    @Insert
    fun insert(user: User)

}