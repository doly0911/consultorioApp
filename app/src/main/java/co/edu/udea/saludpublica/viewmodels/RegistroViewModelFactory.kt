package co.edu.udea.saludpublica.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.edu.udea.saludpublica.dao.usuario.UsuarioDao

class RegistroViewModelFactory (private val database : UsuarioDao) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegistroViewModel::class.java)) {
            return RegistroViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}