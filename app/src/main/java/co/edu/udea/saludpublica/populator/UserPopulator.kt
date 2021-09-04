package co.edu.udea.saludpublica.populator

import co.edu.udea.saludpublica.databinding.DatosSolicitanteLayoutBinding
import co.edu.udea.saludpublica.models.User

class UserPopulator {

    companion object{
        fun populate(user: User, binding: DatosSolicitanteLayoutBinding){
            binding.apply {
                txtIdValue.text = user.idNumber
                txtNombreSolicitanteValue.text = user.names.plus(" ").plus(user.lastNames)
                txtCorreoValue.text = user.email
                txtCelValue.text = user.phoneNumber
            }
        }
    }
}