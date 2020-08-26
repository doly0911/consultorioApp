package co.edu.udea.saludpublica

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class RegistoUsuarioActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Modificar el titulo del actionBar
        this.supportActionBar?.title = "Registro de Usuarios"

        setContentView(R.layout.activity_registo_usuario)


    }
}
