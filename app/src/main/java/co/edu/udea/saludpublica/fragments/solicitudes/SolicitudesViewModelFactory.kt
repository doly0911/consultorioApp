package co.edu.udea.saludpublica.fragments.solicitudes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.edu.udea.saludpublica.dao.solicitud.SolicitudDao

class SolicitudesViewModelFactory (private val dataSource: SolicitudDao, private val usuarioId : Long) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SolicitudesViewModel::class.java)) {
            return SolicitudesViewModel(
                dataSource,
                usuarioId
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

