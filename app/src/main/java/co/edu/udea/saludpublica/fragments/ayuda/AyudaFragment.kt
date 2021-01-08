package co.edu.udea.saludpublica.fragments.ayuda


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import co.edu.udea.saludpublica.R

/**
 * A simple [Fragment] subclass.
 */
class AyudaFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ayuda, container, false)
    }


}
