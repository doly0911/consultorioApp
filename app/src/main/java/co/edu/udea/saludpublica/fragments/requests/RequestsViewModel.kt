package co.edu.udea.saludpublica.fragments.requests

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import co.edu.udea.saludpublica.dao.request.RequestDao
import co.edu.udea.saludpublica.models.Request
import kotlinx.coroutines.*

class RequestsViewModel(private val database : RequestDao, private val userId : Long) : ViewModel(){

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val userByRequest = database.getUserByRequest(userId)

    val requests = Transformations.map(userByRequest) {
        it?.requests
    }


    fun delete(request:Request){
        uiScope.launch {
            withContext(Dispatchers.IO){
                database.delete(request.requestId)
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}