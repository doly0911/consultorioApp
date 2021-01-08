package co.edu.udea.saludpublica.activities.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import co.edu.udea.saludpublica.dao.user.UserDao
import co.edu.udea.saludpublica.models.User
import kotlinx.coroutines.*

class AuthViewModel (private val database : UserDao) : ViewModel() {
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val _usuario = MutableLiveData<User?>()
    val user : LiveData<User?>
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
                database.authenticate(correo, password)
            }
            _showAuthError.value = _usuario.value == null
        }
    }

    override fun onCleared() {
        viewModelJob.cancel()
        super.onCleared()
    }
}