package co.edu.udea.saludpublica.fragments.creacionsolicitud

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.edu.udea.saludpublica.dao.solicitud.SolicitudDao
import co.edu.udea.saludpublica.models.Solicitud
import kotlinx.coroutines.*

class CreacionSolicitudViewModel(private val database : SolicitudDao) : ViewModel() {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private var _navigateToSolicitudesFragment =  MutableLiveData<Boolean>()
    val navigateToSolicitudesFragment : LiveData<Boolean>
        get() = _navigateToSolicitudesFragment

    init {
        _navigateToSolicitudesFragment.value = false
    }

    fun doneNavigation(){
        _navigateToSolicitudesFragment.value = false
    }

    fun insert(solicitud: Solicitud){
        uiScope.launch {
            withContext(Dispatchers.IO){
                database.insert(solicitud)
            }
        }
        _navigateToSolicitudesFragment.value = true
    }

    fun update(solicitud: Solicitud){
        uiScope.launch {
            withContext(Dispatchers.IO){
                database.update(solicitud)
            }
        }
        _navigateToSolicitudesFragment.value = true
    }

    override fun onCleared() {
        viewModelJob.cancel()
        super.onCleared()
    }
}