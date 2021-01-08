package co.edu.udea.saludpublica.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import co.edu.udea.saludpublica.converters.ChannelConverter
import co.edu.udea.saludpublica.converters.PriorityConverter
import co.edu.udea.saludpublica.dao.request.RequestDao
import co.edu.udea.saludpublica.dao.user.UserDao
import co.edu.udea.saludpublica.models.Request
import co.edu.udea.saludpublica.models.User

@Database(entities = [Request::class, User::class], version = 1, exportSchema = false)
@TypeConverters(ChannelConverter::class,PriorityConverter::class)
abstract class ConsultarioDatabase : RoomDatabase(){

    abstract val requestDao : RequestDao
    abstract val userDao : UserDao

    companion object {
        //avoid to cache the value and ensure that all threads are gonna be updated when the value changes
        @Volatile
        private var INSTANCE: ConsultarioDatabase? = null

        fun getInstance(context: Context): ConsultarioDatabase {
            /*Use synchronized block to prevent to initialize the db more than once.
            * synchronized allows to only one thread to access to this block of code*/
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext, ConsultarioDatabase::class.java,
                        "sleep_history_database")
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}