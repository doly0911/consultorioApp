package co.edu.udea.saludpublica.fragments


import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import co.edu.udea.saludpublica.R
import co.edu.udea.saludpublica.adapters.SolicitudAdapter
import co.edu.udea.saludpublica.dao.DefaultSolicitudDao
import co.edu.udea.saludpublica.databinding.FragmentSolicitudesBinding
import co.edu.udea.saludpublica.models.Solicitud

/**
 * A simple [Fragment] subclass.
 */
class SolicitudesFragment : Fragment(),  SolicitudAdapter.SolicitudAdapterOnClickListener {

    private lateinit var binding: FragmentSolicitudesBinding
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //Modificar el titulo del actionBar
        (activity as AppCompatActivity).supportActionBar?.title = "Lista de Solicitudes"

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentSolicitudesBinding>(inflater, R.layout.fragment_solicitudes, container, false)

        viewManager = LinearLayoutManager(context)
        val viewAdapter = SolicitudAdapter(getSolicitudes(),this)
        recyclerView = binding.recyclerViewSolicitudes.apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }
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
        val id = item!!.itemId
        //handle item clicks
        if (id == R.id.filtro_menu){
            //mostrar el Dialog
            val mAlertDialog = AlertDialog.Builder(this.getView()?.context)
                .setTitle("Búsqueda Avanzada")
                .setView(R.layout.layout_filtro_solicitud)
                .setNegativeButton("Cancelar") { dialog: DialogInterface?, which: Int ->
                    dialog?.dismiss()
                }
                .setPositiveButton("Aplicar") { dialog: DialogInterface?, which: Int ->
                    Log.d("Tag","Prueba")
                }

            // Create the AlertDialog object and return it
            mAlertDialog.show()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getSolicitudes() : List<Solicitud>{
        return DefaultSolicitudDao().getSolicitudes()
    }

    override fun btnEditarOnClick(solicitud: Solicitud, view : View) {
        Log.i("SolicitudesFragment", "Btn editar")
    }

    override fun btnResponderOnClick(solicitud: Solicitud, view: View) {
        view.findNavController().navigate(R.id.action_solicitudesFragment_to_respuestaFragment)
    }
}
