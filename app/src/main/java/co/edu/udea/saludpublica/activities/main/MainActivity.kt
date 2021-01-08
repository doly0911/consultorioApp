package co.edu.udea.saludpublica.activities.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import co.edu.udea.saludpublica.R
import co.edu.udea.saludpublica.activities.consultorio.ConsultorioActivity
import co.edu.udea.saludpublica.activities.registro.RegistoUsuarioActivity
import co.edu.udea.saludpublica.database.ConsultarioDatabase
import co.edu.udea.saludpublica.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_main
        )
        val database = ConsultarioDatabase.getInstance(this)
        val factory =
            AutenticacionViewModelFactory(
                database.usuarioDao
            )
        val viewModel = ViewModelProvider(this, factory).get(AutenticacionViewModel::class.java)

        //Lo envia al formulario de registro de usuario
        binding.btnRegistrarse.setOnClickListener {
            val intent = Intent(this, RegistoUsuarioActivity::class.java)
            startActivity(intent)
        }

        binding.loginButton.setOnClickListener{
            val correo : String = binding.emailEditText.text.toString()
            val password : String = binding.passwordEditText.text.toString()
            viewModel.authenticate(correo, password)
        }

        viewModel.isAuthenticated.observe(this, Observer { isAuth ->
            if(isAuth){
                val intent = Intent(this, ConsultorioActivity::class.java).apply {
                    putExtra("usuarioId",viewModel.usuario.value?.usuarioId)
                }
                startActivity(intent)
            }
        })

        viewModel.showAuthError.observe(this, Observer {
            if(it  == true){
                Toast.makeText(this, "El usuario y/o contraseña no es válido", Toast.LENGTH_SHORT)
                    .show()
                viewModel.showAuthErrorIsDone()
            }
        })

    }
}

