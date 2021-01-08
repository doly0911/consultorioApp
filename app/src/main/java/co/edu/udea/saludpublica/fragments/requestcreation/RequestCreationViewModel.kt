package co.edu.udea.saludpublica.fragments.requestcreation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.edu.udea.saludpublica.dao.request.RequestDao
import co.edu.udea.saludpublica.dao.user.UserDao
import co.edu.udea.saludpublica.models.Request
import co.edu.udea.saludpublica.models.User
import kotlinx.coroutines.*

class RequestCreationViewModel(private val requestDao : RequestDao, private val userDao : UserDao,
                               private val userId : Long?) : ViewModel() {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private var _navigateToRequestsFragment =  MutableLiveData<Boolean>()
    val navigateToRequestsFragment : LiveData<Boolean>
        get() = _navigateToRequestsFragment
    private var _currentUser =  MutableLiveData<User?>()
    val currentUser : LiveData<User?>
        get() = _currentUser

    fun getUser(userId : Long){
        uiScope.launch {
            _currentUser.value = withContext(Dispatchers.IO){
                userDao.getUser(userId)
            }
        }
    }

    init {
        _navigateToRequestsFragment.value = false
        userId?.let {
            getUser(it)
        }
    }

    fun doneNavigation(){
        _navigateToRequestsFragment.value = false
    }

    fun insert(request: Request){
        uiScope.launch {
            withContext(Dispatchers.IO){
                requestDao.insert(request)
            }
        }
        _navigateToRequestsFragment.value = true
    }

    fun update(request: Request){
        uiScope.launch {
            withContext(Dispatchers.IO){
                requestDao.update(request)
            }
        }
        _navigateToRequestsFragment.value = true
    }

    override fun onCleared() {
        viewModelJob.cancel()
        super.onCleared()
    }
}