package co.edu.udea.saludpublica.fragments.forward

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.edu.udea.saludpublica.dao.user.UserDao

class ForwardViewModelFactory  (private val database : UserDao, private val userId : Long?)
    : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ForwardViewModel::class.java)) {
            return ForwardViewModel(database, userId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}