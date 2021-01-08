package co.edu.udea.saludpublica.fragments.requestcreation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.edu.udea.saludpublica.dao.request.RequestDao
import co.edu.udea.saludpublica.dao.user.UserDao

class RequestCreationViewModelFactory (private val requestDao : RequestDao, private val userDao : UserDao,
                                       private val userId : Long?)
    : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RequestCreationViewModel::class.java)) {
            return RequestCreationViewModel(requestDao, userDao, userId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
