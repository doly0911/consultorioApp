package co.edu.udea.saludpublica.fragments.requestcreation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.edu.udea.saludpublica.dao.request.RequestDao

class RequestCreationViewModelFactory (private val database : RequestDao)
    : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RequestCreationViewModel::class.java)) {
            return RequestCreationViewModel(
                database
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
