package co.edu.udea.saludpublica

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.databinding.DataBindingUtil
import co.edu.udea.saludpublica.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var btn: Button
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.loginButton.setOnClickListener{
            val intent = Intent(this, ConsultorioActivity::class.java)
            startActivity(intent)
        }

        //Lo envia al formulario de registro de usuario
        btn = findViewById(R.id.btn_registrarse)
        btn.setOnClickListener {
            val intent = Intent(this, RegistoUsuarioActivity::class.java)
            startActivity(intent)
        }
    }
}

