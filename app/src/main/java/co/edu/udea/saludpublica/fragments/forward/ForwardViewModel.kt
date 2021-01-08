package co.edu.udea.saludpublica.fragments.forward

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.edu.udea.saludpublica.dao.user.UserDao
import co.edu.udea.saludpublica.models.User
import kotlinx.coroutines.*

class ForwardViewModel (private val database : UserDao,  private val userId : Long?) : ViewModel() {
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private var _currentUser =  MutableLiveData<User?>()
    val currentUser : LiveData<User?>
        get() = _currentUser

    init {
        userId?.let {
            getUser(it)
        }
    }

    fun getUser(userId : Long){
        uiScope.launch {
            _currentUser.value = withContext(Dispatchers.IO){
                database.getUser(userId)
            }
        }
    }

    override fun onCleared() {
        viewModelJob.cancel()
        super.onCleared()
    }
}