package co.edu.udea.saludpublica.fragments.solicitudes

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import co.edu.udea.saludpublica.dao.solicitud.SolicitudDao
import co.edu.udea.saludpublica.models.Solicitud
import kotlinx.coroutines.*

class SolicitudesViewModel(private val database : SolicitudDao, private val usuarioId : Long) : ViewModel(){

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    val solicitudesUsuario = database.getByUserId(usuarioId)

    val solicitudes = Transformations.map(solicitudesUsuario) {
        it?.solicitudes
    }

    init {
        //initSolicitudesUsuario()
    }


    /*fun initSolicitudesUsuario(){
        uiScope.launch {
            _solicitudesUsuario.value = withContext(Dispatchers.IO){
                database.getByUserId(usuarioId)
            }
        }
    }
     */

    fun delete(solicitud:Solicitud){
        uiScope.launch {
            withContext(Dispatchers.IO){
                database.delete(solicitud.solicitudId)
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}