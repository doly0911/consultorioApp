package co.edu.udea.saludpublica.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.edu.udea.saludpublica.dao.usuario.UsuarioDao
import co.edu.udea.saludpublica.models.Usuario
import kotlinx.coroutines.*

class RegistroViewModel (private val database : UsuarioDao) : ViewModel() {
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private var _navigateToLogin =  MutableLiveData<Boolean>()
    val navigateToLogin : LiveData<Boolean>
        get() = _navigateToLogin

    init {
        _navigateToLogin.value = false
    }

    fun onNavigateToLoginSuccess(){
        _navigateToLogin.value = false
    }

    fun insert(usuario: Usuario){
        uiScope.launch {
            withContext(Dispatchers.IO){
                database.insert(usuario)
            }
        }
        _navigateToLogin.value = true
    }

    override fun onCleared() {
        viewModelJob.cancel()
        super.onCleared()
    }
}