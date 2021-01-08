package co.edu.udea.saludpublica.activities.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import co.edu.udea.saludpublica.dao.usuario.UsuarioDao
import co.edu.udea.saludpublica.models.Usuario
import kotlinx.coroutines.*

class AutenticacionViewModel (private val database : UsuarioDao) : ViewModel() {
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val _usuario = MutableLiveData<Usuario?>()
    val usuario : LiveData<Usuario?>
        get() = _usuario

    val isAuthenticated = Transformations.map(_usuario) {
        it != null
    }

    private var _showAuthError =  MutableLiveData<Boolean>()
    val showAuthError : LiveData<Boolean>
        get() = _showAuthError

    init {
        _showAuthError.value = false
    }

    fun showAuthErrorIsDone(){
        _showAuthError.value = false
    }

    fun authenticate(correo: String, password : String) {
        uiScope.launch {
            _usuario.value = withContext(Dispatchers.IO) {
                database.autenticar(correo, password)
            }
            _showAuthError.value = _usuario.value == null
        }
    }

    override fun onCleared() {
        viewModelJob.cancel()
        super.onCleared()
    }
}