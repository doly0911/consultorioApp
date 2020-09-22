package co.edu.udea.saludpublica.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.edu.udea.saludpublica.dao.solicitud.SolicitudDao

class CreacionSolicitudViewModelFactory (private val database : SolicitudDao)
    : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CreacionSolicitudViewModel::class.java)) {
            return CreacionSolicitudViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
