package co.edu.udea.saludpublica.fragments.requests

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.edu.udea.saludpublica.dao.request.RequestDao

class RequestsViewModelFactory (private val dataSource: RequestDao, private val userId : Long) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RequestsViewModel::class.java)) {
            return RequestsViewModel(
                dataSource,
                userId
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

