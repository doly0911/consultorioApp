package co.edu.udea.saludpublica.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.edu.udea.saludpublica.dao.solicitud.DefaultSolicitudDao
import co.edu.udea.saludpublica.models.Solicitud

class SolicitudesViewModel : ViewModel(){

    val solicitudes= MutableLiveData<MutableList<Solicitud>>()
    private var solicitudDao = DefaultSolicitudDao()

    init{
        solicitudes.value = solicitudDao.getSolicitudes()
    }

    fun eliminarSolicitud(solicitud:Solicitud){
        val solicitdesCopy =  solicitudes.value
        solicitdesCopy?.remove(solicitud)
        solicitudes.value = solicitdesCopy
    }



}