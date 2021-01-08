package co.edu.udea.saludpublica.fragments.requestcreation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.edu.udea.saludpublica.dao.request.RequestDao
import co.edu.udea.saludpublica.models.Request
import kotlinx.coroutines.*

class RequestCreationViewModel(private val database : RequestDao) : ViewModel() {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private var _navigateToRequestsFragment =  MutableLiveData<Boolean>()
    val navigateToRequestsFragment : LiveData<Boolean>
        get() = _navigateToRequestsFragment

    init {
        _navigateToRequestsFragment.value = false
    }

    fun doneNavigation(){
        _navigateToRequestsFragment.value = false
    }

    fun insert(request: Request){
        uiScope.launch {
            withContext(Dispatchers.IO){
                database.insert(request)
            }
        }
        _navigateToRequestsFragment.value = true
    }

    fun update(request: Request){
        uiScope.launch {
            withContext(Dispatchers.IO){
                database.update(request)
            }
        }
        _navigateToRequestsFragment.value = true
    }

    override fun onCleared() {
        viewModelJob.cancel()
        super.onCleared()
    }
}