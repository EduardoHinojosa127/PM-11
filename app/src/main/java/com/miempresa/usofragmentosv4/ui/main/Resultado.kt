package com.miempresa.usofragmentosv4.ui.main

import android.media.AudioMetadata.createMap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.textfield.TextInputLayout
import com.miempresa.usofragmentosv4.R
import com.miempresa.usofragmentosv4.databinding.FragmentResultadoBinding
import kotlinx.android.synthetic.main.fragment_fragmento2.*
import kotlinx.android.synthetic.main.fragment_resultado.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Resultado.newInstance] factory method to
 * create an instance of this fragment.
 */
class Resultado : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentResultadoBinding? = null
    private val binding: FragmentResultadoBinding
        get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentResultadoBinding.inflate(inflater, container, false)


        val feelings = resources.getStringArray(R.array.comboOperaciones)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.item_dropdown, feelings)

        binding.autoCompleteTextView.apply {
            setAdapter(arrayAdapter)
        }

        binding.btnEnviar2.setOnClickListener {
            var numero1:Double = binding.txtNumero1.text.toString().toDouble()
            var numero2:Double = binding.txtNumero2.text.toString().toDouble()
            var operador:String = binding.autoCompleteTextView.text.toString()
            var respuesta:Double? = null
            if(operador=="Sumar"){
                respuesta = numero1 + numero2
            }else if(operador=="Restar"){
                respuesta = numero1 - numero2
            }else if (operador=="Multiplicar"){
                respuesta = numero1 * numero2
            }else{
                respuesta = numero1 / numero2
            }
            var paquete = "El resultado de "+operador+" "+numero1+" y "+numero2+" es: "+respuesta
            val operacion = Operaciones()
            val args = Bundle()
            args.putString("respuesta", paquete)
            operacion.arguments = args
            val transaccion: FragmentTransaction =
                requireActivity().supportFragmentManager.beginTransaction()
            transaccion.replace(R.id.contenedor2, operacion)
            transaccion.commit()
        }

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Resultado.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Resultado().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}