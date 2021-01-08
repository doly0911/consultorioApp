package co.edu.udea.saludpublica.activities.registro

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import co.edu.udea.saludpublica.R
import co.edu.udea.saludpublica.activities.main.MainActivity
import co.edu.udea.saludpublica.dao.usuario.UsuarioDao
import co.edu.udea.saludpublica.database.ConsultarioDatabase
import co.edu.udea.saludpublica.databinding.ActivityRegistoUsuarioBinding
import co.edu.udea.saludpublica.models.Usuario
import co.edu.udea.saludpublica.viewmodels.RegistroViewModel
import co.edu.udea.saludpublica.viewmodels.RegistroViewModelFactory

class RegistoUsuarioActivity : AppCompatActivity() {

    private val activityTitle = "Registro de Usuarios"
    private lateinit var binding: ActivityRegistoUsuarioBinding
    private lateinit var usuarioDao : UsuarioDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_registo_usuario
        )
        //Modificar el titulo del actionBar
        this.supportActionBar?.title = activityTitle
        val database = ConsultarioDatabase.getInstance(this)
        usuarioDao = database.usuarioDao
        binding.lifecycleOwner = this
        val factory = RegistroViewModelFactory(usuarioDao)
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

    private fun convertFormToUser(binding : ActivityRegistoUsuarioBinding) : Usuario{
        val usuario = Usuario()
        usuario.numeroIdentificacion = binding.inputNumeroId.text.toString()
        usuario.nombres = binding.inputNombre.text.toString()
        usuario.apellidos = binding.inputApellidos.text.toString()
        usuario.correo = binding.inputCorreo.text.toString()
        usuario.password = binding.inputPassRegistro.text.toString()
        return usuario
    }
}
