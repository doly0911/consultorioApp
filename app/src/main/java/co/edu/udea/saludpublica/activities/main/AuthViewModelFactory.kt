package co.edu.udea.saludpublica.activities.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.edu.udea.saludpublica.dao.user.UserDao

class AuthViewModelFactory (private val database : UserDao) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            return AuthViewModel(
                database
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}