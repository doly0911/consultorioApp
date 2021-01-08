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
import co.edu.udea.saludpublica.activities.register.RegisterUserActivity
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
            AuthViewModelFactory(
                database.userDao
            )
        val viewModel = ViewModelProvider(this, factory).get(AuthViewModel::class.java)

        //Lo envia al formulario de registro de usuario
        binding.btnRegistrarse.setOnClickListener {
            val intent = Intent(this, RegisterUserActivity::class.java)
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
                    putExtra("userId",viewModel.user.value?.userId)
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

