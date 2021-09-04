package co.edu.udea.saludpublica.fragments.requestcreation


import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import co.edu.udea.saludpublica.R
import co.edu.udea.saludpublica.database.ConsultarioDatabase
import co.edu.udea.saludpublica.databinding.FragmentCreacionSolicitudBinding
import co.edu.udea.saludpublica.enums.ChannelEnum
import co.edu.udea.saludpublica.models.Request
import co.edu.udea.saludpublica.populator.PriorityPopulator
import co.edu.udea.saludpublica.populator.RequestPopulator
import co.edu.udea.saludpublica.populator.UserPopulator

/**
 * A simple [Fragment] subclass.
 */
class RequestCreationFragment : Fragment() {

    private lateinit var binding : FragmentCreacionSolicitudBinding
    private lateinit var viewModel: RequestCreationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val args = arguments?.let {
            RequestCreationFragmentArgs.fromBundle(
                it
            )
        }
        val request : Request? = args?.request

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_creacion_solicitud, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        binding.spinner.adapter = ArrayAdapter<ChannelEnum>(
            context!!,
            android.R.layout.simple_spinner_dropdown_item,
            ChannelEnum.values()
        )
        populateActivity(request)

        val database = ConsultarioDatabase.getInstance(requireContext())
        val userId = requireActivity().intent.extras?.get("userId") as Long
        val factory = RequestCreationViewModelFactory(database.requestDao, database.userDao,userId)
        viewModel = ViewModelProvider(this, factory).get(RequestCreationViewModel::class.java)
        binding.viewModel = viewModel

        binding.imageBtnGuardar.setOnClickListener{
            if(request != null){
                RequestPopulator.populate(request,binding)
                viewModel.update(request)
            }else{
                val newRequest = Request()
                newRequest.owner = userId
                RequestPopulator.populate(newRequest,binding)
                viewModel.insert(newRequest)
            }
        }

        viewModel.navigateToRequestsFragment.observe(viewLifecycleOwner, Observer {
            if(it == true){
                this.findNavController().navigate(RequestCreationFragmentDirections.actionRequestCreationFragmentToRequestsFragment())
                viewModel.doneNavigation()
            }
        })

        viewModel.currentUser.observe(viewLifecycleOwner, Observer {
            it?.let {
                UserPopulator.populate(it, binding.datosSolicitudLayout)
            }
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSubirArchivo.setOnClickListener {
            loadImage()
        }
    }

    private fun populateActivity(request: Request?){
        binding.apply {
            editTxtAsuntoConsulta.setText(request?.topic)
            editTxtDescripcionConsulta.setText(request?.description)
            request?.let {
                binding.radioBtnPrioridad.check(PriorityPopulator.populate(request.priority))
                binding.spinner.setSelection(request.channel.value)
            }
        }
    }

    private fun loadImage(){
        val intent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        intent.setType("image/")

        startActivityForResult(Intent.createChooser(intent, "Seleccionar imagen"), 10)
    }


}