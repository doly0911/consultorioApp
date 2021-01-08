package co.edu.udea.saludpublica.fragments.requests


import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.edu.udea.saludpublica.R
import co.edu.udea.saludpublica.adapters.RequestAdapter
import co.edu.udea.saludpublica.database.ConsultarioDatabase
import co.edu.udea.saludpublica.databinding.FragmentSolicitudesBinding
import co.edu.udea.saludpublica.models.Request

/**
 * A simple [Fragment] subclass.
 */
class RequestsFragment : Fragment(), RequestAdapter.RequestAdapterOnClickListener {

    private lateinit var binding: FragmentSolicitudesBinding
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: RequestsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_solicitudes,
            container,
            false
        )
        binding.lifecycleOwner = viewLifecycleOwner
        //le asigno el id al boton flotante y al dar click lo redirijo al fragmento crear solicitud
        binding.fabCrearSolicitud.setOnClickListener {
            view?.findNavController()?.navigate(
                RequestsFragmentDirections.actionRequestsFragmentToRequestCreationFragment(null)
            )
        }

        viewManager = LinearLayoutManager(context)
        val viewAdapter = RequestAdapter(this)
        recyclerView = binding.recyclerViewSolicitudes.apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }

        val database = ConsultarioDatabase.getInstance(requireContext())
        val userId = requireActivity().intent.extras?.get("userId") as Long
        val factory =
            RequestsViewModelFactory(
                database.requestDao,
                userId
            )
        viewModel = ViewModelProvider(this, factory).get(RequestsViewModel::class.java)

        viewModel.requests.observe(viewLifecycleOwner, Observer {
            it?.let {
                viewAdapter.submitList(it)
            }
        })

        return binding.root
    }

    //MENU PARA FILTRAR
    //enable options menu in this fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    //inflate the menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.filtrar_solicitudes_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    //handle item clicks of menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        //handle item clicks
        if (id == R.id.filtro_menu) {
            //mostrar el Dialog
            val mAlertDialog = AlertDialog.Builder(this.view?.context)
                .setView(R.layout.layout_filtro_solicitud)
                .setNegativeButton("Cancelar") { dialog: DialogInterface?, _: Int ->
                    dialog?.dismiss()
                }
                .setPositiveButton("Aplicar") { _: DialogInterface?, _: Int ->
                    Log.d("Tag", "Prueba")
                }

            // Create the AlertDialog object and return it
            mAlertDialog.show()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun btnEditOnClick(request: Request, view: View) {
        view.findNavController().navigate(
            RequestsFragmentDirections.actionRequestsFragmentToRequestCreationFragment(request)
        )

    }

    override fun btnForwardOnClick(request: Request, view: View) {
        view.findNavController().navigate(
            RequestsFragmentDirections.actionRequestsFragmentToForwardFragment(request)
        )
    }

    override fun btnRemoveOnClick(request: Request, view: View) {
        viewModel.delete(request)
    }
}
