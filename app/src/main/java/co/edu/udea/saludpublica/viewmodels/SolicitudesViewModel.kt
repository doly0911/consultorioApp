package co.edu.udea.saludpublica.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import co.edu.udea.saludpublica.dao.solicitud.SolicitudDao
import co.edu.udea.saludpublica.models.Solicitud
import kotlinx.coroutines.*

class SolicitudesViewModel(private val database : SolicitudDao) : ViewModel(){

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    val solicitudes : LiveData<List<Solicitud>> = database.getAll()

    fun delete(solicitud:Solicitud){
        uiScope.launch {
            withContext(Dispatchers.IO){
                database.delete(solicitud.solicitudId)
            }
        }
    }

}