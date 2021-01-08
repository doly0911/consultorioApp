package co.edu.udea.saludpublica.activities.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.edu.udea.saludpublica.dao.user.UserDao

class RegistroViewModelFactory (private val database : UserDao) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegistroViewModel::class.java)) {
            return RegistroViewModel(
                database
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}