package co.edu.udea.saludpublica.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.github.aakira.expandablelayout.ExpandableRelativeLayout
import co.edu.udea.saludpublica.R
import co.edu.udea.saludpublica.databinding.FragmentRespuestaBinding


/**
 * A simple [Fragment] subclass.
 */
class RespuestaFragment : Fragment() {

    lateinit var binding : FragmentRespuestaBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Modificar el titulo del actionBar
        (activity as AppCompatActivity).supportActionBar?.title = "Responder Solicitud"

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentRespuestaBinding>(inflater, R.layout.fragment_respuesta, container, false)

        val layoutDatosSolicitud : ExpandableRelativeLayout = binding.layoutDatosSolicitud
        val layoutRespuestaSolicitud : ExpandableRelativeLayout = binding.layoutRespuestaSolicitud
        val layoutTrazabilidad : ExpandableRelativeLayout = binding.layoutTrazabilidad

        toogleLayout(binding.btnDatosSolicitud,layoutDatosSolicitud )
        toogleLayout(binding.btnRespuestaSolicitud,layoutRespuestaSolicitud )
        toogleLayout(binding.btnTrazabilidad,layoutTrazabilidad )

        collapseLayout(arrayListOf(layoutRespuestaSolicitud,layoutTrazabilidad))

        return binding.root
    }

    private fun toogleLayout(view : View, layout : ExpandableRelativeLayout){
        view.setOnClickListener{
            layout.toggle()
        }
    }

    private fun collapseLayout(layouts : List<ExpandableRelativeLayout>){
       layouts.forEach{
           it.collapse()
       }
    }



}