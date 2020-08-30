package co.edu.udea.saludpublica

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import co.edu.udea.saludpublica.dao.usuario.DefaultUsuarioDao
import co.edu.udea.saludpublica.dao.usuario.UsuarioDao
import co.edu.udea.saludpublica.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var usuarioDao : UsuarioDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.loginButton.setOnClickListener{
            autenticar()
        }
        usuarioDao = DefaultUsuarioDao()
        //Lo envia al formulario de registro de usuario
        binding.btnRegistrarse.setOnClickListener {
            val intent = Intent(this, RegistoUsuarioActivity::class.java)
            startActivity(intent)
        }
    }

    private fun autenticar(){
        val correo : String = binding.emailEditText.text.toString()
        val password : String = binding.passwordEditText.text.toString()
        if(usuarioDao.autenticar(correo,password)){
            val intent = Intent(this, ConsultorioActivity::class.java)
            startActivity(intent)
        }else{
            Toast.makeText(this, "El usuario o contraseña no es válido", Toast.LENGTH_SHORT)
                .show()
        }

    }
}

