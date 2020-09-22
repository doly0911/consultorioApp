package co.edu.udea.saludpublica.viewmodels

import androidx.lifecycle.ViewModel
import co.edu.udea.saludpublica.dao.solicitud.SolicitudDao
import co.edu.udea.saludpublica.models.Solicitud
import kotlinx.coroutines.*

class CreacionSolicitudViewModel(private val database : SolicitudDao) : ViewModel() {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun insert(solicitud: Solicitud){
        uiScope.launch {
            withContext(Dispatchers.IO){
                database.insert(solicitud)
            }
        }
    }

    fun update(solicitud: Solicitud){
        uiScope.launch {
            withContext(Dispatchers.IO){
                database.update(solicitud)
            }
        }
    }

    override fun onCleared() {
        viewModelJob.cancel()
        super.onCleared()
    }
}