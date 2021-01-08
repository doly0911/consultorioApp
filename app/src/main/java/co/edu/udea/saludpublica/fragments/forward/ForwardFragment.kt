package co.edu.udea.saludpublica.fragments.forward


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import co.edu.udea.saludpublica.R
import co.edu.udea.saludpublica.database.ConsultarioDatabase
import co.edu.udea.saludpublica.databinding.FragmentRespuestaBinding
import co.edu.udea.saludpublica.fragments.requestcreation.RequestCreationFragmentArgs
import co.edu.udea.saludpublica.models.Request
import co.edu.udea.saludpublica.populator.UserPopulator
import com.github.aakira.expandablelayout.ExpandableRelativeLayout


/**
 * A simple [Fragment] subclass.
 */
class ForwardFragment : Fragment() {

    lateinit var binding : FragmentRespuestaBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val args = arguments?.let {
            RequestCreationFragmentArgs.fromBundle(it)
        }
        val request : Request? = args?.request

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentRespuestaBinding>(inflater, R.layout.fragment_respuesta, container, false)
        populateFragment(request)

        binding.lifecycleOwner = viewLifecycleOwner
        val database = ConsultarioDatabase.getInstance(requireContext())
        val factory = ForwardViewModelFactory(database.userDao, request?.owner)
        val viewModel = ViewModelProvider(this, factory).get(ForwardViewModel::class.java)
        viewModel.currentUser.observe(viewLifecycleOwner, Observer {
            it?.let {
                UserPopulator.populate(it, binding.layoutDatosSolicitud.datosSolicitudLayout)
            }
        })

        val requestDataLayout : ExpandableRelativeLayout = binding.expandablelayoutDatosSolicitud
        val requestForwardLayout : ExpandableRelativeLayout = binding.expandablelayoutRespuestaSolicitud
        val traceLayout : ExpandableRelativeLayout = binding.expandablelayoutTrazabilidad

        toogleLayout(binding.btnDatosSolicitud,requestDataLayout )
        toogleLayout(binding.btnRespuestaSolicitud,requestForwardLayout )
        toogleLayout(binding.btnTrazabilidad,traceLayout )

        collapseLayout(arrayListOf(requestForwardLayout,traceLayout))

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

    private fun populateFragment(request: Request?){
        binding.layoutDatosSolicitud.apply {
            txtAsuntoValue.text = request?.topic
            txtDescripcionValue.text = request?.description
            txtMedioRespuestaValue.text = request?.channel.toString()
            txtPrioridadValue.text = request?.priority.toString()
        }
    }





}