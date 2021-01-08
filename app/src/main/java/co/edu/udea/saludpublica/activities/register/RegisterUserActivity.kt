package co.edu.udea.saludpublica.activities.register

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import co.edu.udea.saludpublica.R
import co.edu.udea.saludpublica.activities.main.MainActivity
import co.edu.udea.saludpublica.dao.user.UserDao
import co.edu.udea.saludpublica.database.ConsultarioDatabase
import co.edu.udea.saludpublica.databinding.ActivityRegistoUsuarioBinding
import co.edu.udea.saludpublica.models.User

class RegisterUserActivity : AppCompatActivity() {

    private val activityTitle = "Registro de Usuarios"
    private lateinit var binding: ActivityRegistoUsuarioBinding
    private lateinit var userDao : UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_registo_usuario
        )
        //Modificar el titulo del actionBar
        this.supportActionBar?.title = activityTitle
        val database = ConsultarioDatabase.getInstance(this)
        userDao = database.userDao
        binding.lifecycleOwner = this
        val factory =
            RegistroViewModelFactory(
                userDao
            )
        val viewModel = ViewModelProvider(this, factory).get(RegistroViewModel::class.java)
        viewModel.navigateToLogin.observe(this, Observer { isDone ->
            if(isDone){
                viewModel.onNavigateToLoginSuccess()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        })

        binding.imageBtnRegistrar.setOnClickListener{
            viewModel.insert(convertFormToUser(binding))
        }
    }

    private fun convertFormToUser(binding : ActivityRegistoUsuarioBinding) : User{
        val usuario = User()
        usuario.idNumber = binding.inputNumeroId.text.toString()
        usuario.names = binding.inputNombre.text.toString()
        usuario.lastNames = binding.inputApellidos.text.toString()
        usuario.email = binding.inputCorreo.text.toString()
        usuario.password = binding.inputPassRegistro.text.toString()
        return usuario
    }
}
