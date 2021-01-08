package co.edu.udea.saludpublica.activities.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.edu.udea.saludpublica.dao.user.UserDao
import co.edu.udea.saludpublica.models.User
import kotlinx.coroutines.*

class RegistroViewModel (private val database : UserDao) : ViewModel() {
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

    fun insert(user: User){
        uiScope.launch {
            withContext(Dispatchers.IO){
                database.insert(user)
            }
        }
        _navigateToLogin.value = true
    }

    override fun onCleared() {
        viewModelJob.cancel()
        super.onCleared()
    }
}