package co.edu.udea.saludpublica

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import co.edu.udea.saludpublica.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {



    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.loginButton.setOnClickListener{
            val intent = Intent(this, ConsultorioActivity::class.java)
            startActivity(intent)
        }
    }
}
