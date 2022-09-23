package com.example.planpacifico.ui.modules.architecture.observation

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.planpacifico.R
import com.example.planpacifico.data.localdb.ArchitectureDataBase
import com.example.planpacifico.data.models.entities.ObservationEntity
import com.example.planpacifico.databinding.FragmentObservatiionBinding
import com.example.planpacifico.databinding.FragmentObservationBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.launch


class ObservatiionFragment : Fragment(R.layout.fragment_observatiion) {

    private lateinit var binding: FragmentObservatiionBinding
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    var resnameuser: String? = null;
    var reslenght: String? = null;
    var reslaltitude: String? = null;
    var res1: String? = null;
    var res2: String? = null;
    var res3: String? = null;
    var res4: String? = null;
    var res5: String? = null;
    var res6: String? = null;
    var res7: String? = null;
    var res8: String? = null;
    var res9: String? = null;
    var res10: String? = null;
    var res11: String? = null;
    var res12: String? = null;
    var res13: String? = null;
    var res14: String? = null;
    var res15: String? = null;
    var res16: String? = null;
    var res17: String? = null;
    var res18: String? = null;


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentObservatiionBinding.bind(view)
        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireActivity())

        clicks()
        binding.btnUpdateCoordinates.setOnClickListener {
            getLocation()
        }
        others();

    }

    private fun clicks() {

        //Guardar datos en la base de datos local
        binding.btnSaveObservation.setOnClickListener {

            lifecycleScope.launch {
                ArchitectureDataBase.getDatabase(requireContext()).ObservationDao()

                //VARIABLE ALMACENA NOMBRE USUARIO
                resnameuser = binding.inputNombreUsuario.text.toString();

                //VARIABLE ALMACENA LONGITUD
                reslenght = binding.length.text.toString()

                //VARIABLE ALMACENA LATIITUD
                reslaltitude = binding.latitude.text.toString()


                //VARIABLE ALMACENA RESPUESTA UNICA PREGUNTA 1
                if (binding.rbp1A.isChecked) {
                    res1 = binding.rbp1A.text.toString();
                } else if (binding.rbp1B.isChecked) {
                    res1 = binding.rbp1B.text.toString();
                } else if (binding.rbp1C.isChecked) {
                    res1 = binding.rbp1C.text.toString();
                } else if (binding.rbp1D.isChecked) {
                    res1 = binding.rbp1D.text.toString();
                }

/*
                //VARIABLE ALMACENA RESPUESTA MÚLTIPLE PREGUNTA 3
                if (binding.chboxp3A.isChecked) {
                    res3 = binding.chboxp3A.text.toString()
                }
                if (binding.chboxp3B.isChecked) {
                    res3 = res3+ "," + binding.chboxp3B.text.toString()
                }
                if (binding.chboxp3C.isChecked) {
                    res3 = res3+ "," + binding.chboxp3C.text.toString()
                }
                if (binding.chboxp3D.isChecked) {
                    res3 = res3+ "," + binding.chboxp3D.text.toString()
                }
                if (binding.chboxp3E.isChecked) {
                    res3 = res3+ "," + binding.chboxp3E.text.toString()
                }
                if (binding.chboxp3F.isChecked) {
                    res3 = res3+ "," + binding.chboxp3F.text.toString()
                }
*/

                //VARIABLE ALMACENA RESPUESTA MÚLTIPLE PREGUNTA 4
                if (binding.chboxp4A.isChecked) {
                    res3 = binding.chboxp4A.text.toString()
                }
                if (binding.chboxp4B.isChecked) {
                    res3 = res3+ "," + binding.chboxp4B.text.toString()
                }
                if (binding.chboxp4C.isChecked) {
                    res3 = res3+ "," + binding.chboxp4C.text.toString()
                }
                if (binding.chboxp4D.isChecked) {
                    res3 = res3+ "," + binding.chboxp4D.text.toString()
                }
                if (binding.chboxp4E.isChecked) {
                    res3 = res3+ "," + binding.chboxp4E.text.toString()
                }

                //VARIABLE ALMACENA RESPUESTA MÚLTIPLE PREGUNTA 5
                if (binding.chboxp5A.isChecked) {
                    res4 = binding.chboxp5A.text.toString()
                }
                if (binding.chboxp5B.isChecked) {
                    res4 = res4+ "," + binding.chboxp5B.text.toString()
                }


                //VARIABLE ALMACENA RESPUESTA MÚLTIPLE PREGUNTA 6
                if (binding.chboxp6A.isChecked) {
                    res5 = binding.chboxp6A.text.toString()
                }
                if (binding.chboxp6B.isChecked) {
                    res5 = res5+ "," + binding.chboxp6B.text.toString()
                }

                /*

                //VARIABLE ALMACENA RESPUESTA MÚLTIPLE PREGUNTA 7
                if (binding.chboxp7A.isChecked) {
                    res7 = binding.chboxp7A.text.toString()
                }
                if (binding.chboxp7B.isChecked) {
                    res7 = res7+ "," + binding.chboxp7B.text.toString()
                }
                if (binding.chboxp7C.isChecked) {
                    res7 = res7+ "," + binding.chboxp7C.text.toString()
                }
                if (binding.chboxp7D.isChecked) {
                    res7 = res7+ "," + binding.chboxp7D.text.toString()
                }
                if (binding.chboxp7F.isChecked) {
                    res7 = res7+ "," + binding.chboxp7F.text.toString()
                }
                if (binding.chboxp7G.isChecked) {
                    res7 = res7+ "," + binding.chboxp7G.text.toString()
                }
*/

                //VARIABLE ALMACENA RESPUESTA MÚLTIPLE PREGUNTA 8
                if (binding.chboxp8A.isChecked) {
                    res6 = binding.chboxp8A.text.toString()
                }
                if (binding.chboxp8B.isChecked) {
                    res6 = res6+ "," + binding.chboxp8B.text.toString()
                }

                //VARIABLE ALMACENA RESPUESTA MÚLTIPLE PREGUNTA 9
                if (binding.chboxp9A.isChecked) {
                    res7 = binding.chboxp9A.text.toString()
                }
                if (binding.chboxp9B.isChecked) {
                    res7 = res7+ "," + binding.chboxp9B.text.toString()
                }
                if (binding.chboxp9C.isChecked) {
                    res7 = res7+ "," + binding.chboxp9C.text.toString()
                }
                if (binding.chboxp9D.isChecked) {
                    res7 = res7+ "," + binding.chboxp9D.text.toString()
                }
                if (binding.chboxp9E.isChecked) {
                    res7 = res7+ "," + binding.chboxp9E.text.toString()
                }
                if (binding.chboxp9F.isChecked) {
                    res7 = res7+ "," + binding.chboxp9F.text.toString()
                }
                if (binding.chboxp9G.isChecked) {
                    res7 = res7+ "," + binding.chboxp9G.text.toString()
                }
                if (binding.chboxp9H.isChecked) {
                    res7 = res7+ "," + binding.chboxp9H.text.toString()
                }

                //VARIABLE ALMACENA RESPUESTA MÚLTIPLE PREGUNTA 10
                if (binding.chboxp10A.isChecked) {
                    res8 = binding.chboxp10A.text.toString()
                }
                if (binding.chboxp10B.isChecked) {
                    res8 = res8+ "," + binding.chboxp10B.text.toString()
                }
                if (binding.chboxp10C.isChecked) {
                    res8 = res8+ "," + binding.chboxp10C.text.toString()
                }
                if (binding.chboxp10D.isChecked) {
                    res8 = res8+ "," + binding.chboxp10D.text.toString()
                }
                if (binding.chboxp10E.isChecked) {
                    res8 = res8+ "," + binding.chboxp10E.text.toString()
                }
                if (binding.chboxp10F.isChecked) {
                    res8 = res8+ "," + binding.chboxp10F.text.toString()
                }
                if (binding.chboxp10G.isChecked) {
                    res8 = res8+ "," + binding.chboxp10G.text.toString()
                }
                if (binding.chboxp10H.isChecked) {
                    res8 = res8+ "," + binding.chboxp10H.text.toString()
                }
                if (binding.chboxp10I.isChecked) {
                    res8 = res8+ "," + binding.chboxp10I.text.toString()
                }
                if (binding.chboxp10J.isChecked) {
                    res8 = res8+ "," + binding.chboxp10J.text.toString()
                }

                //VARIABLE ALMACENA RESPUESTA MÚLTIPLE PREGUNTA 11
                if (binding.chboxp11A.isChecked) {
                    res9 = binding.chboxp11A.text.toString()
                }
                if (binding.chboxp11B.isChecked) {
                    res9 = res9+ "," + binding.chboxp11B.text.toString()
                }
                if (binding.chboxp11C.isChecked) {
                    res9 = res9+ "," + binding.chboxp11C.text.toString()
                }
                if (binding.chboxp11D.isChecked) {
                    res9 = res9+ "," + binding.chboxp11D.text.toString()
                }
                if (binding.chboxp11E.isChecked) {
                    res9 = res9+ "," + binding.chboxp11E.text.toString()
                }
                if (binding.chboxp11F.isChecked) {
                    res9 = res9+ "," + binding.chboxp11F.text.toString()
                }
                if (binding.chboxp11G.isChecked) {
                    res9 = res9+ "," + binding.chboxp11G.text.toString()
                }
                if (binding.chboxp11H.isChecked) {
                    res9 = res9+ "," + binding.chboxp11H.text.toString()
                }
                if (binding.chboxp11I.isChecked) {
                    res9 = res9+ "," + binding.chboxp11I.text.toString()
                }
                if (binding.chboxp11J.isChecked) {
                    res9 = res9+ "," + binding.chboxp11J.text.toString()
                }
                if (binding.chboxp11K.isChecked) {
                    res9 = res9+ "," + binding.chboxp11K.text.toString()
                }
                if (binding.chboxp11L.isChecked) {
                    res9 = res9+ "," + binding.chboxp11L.text.toString()
                }
                if (binding.chboxp11M.isChecked) {
                    res9 = res9+ "," + binding.chboxp11M.text.toString()
                }
                if (binding.chboxp11N.isChecked) {
                    res9 = res9+ "," + binding.chboxp11N.text.toString()
                }

                //VARIABLE ALMACENA RESPUESTA MÚLTIPLE PREGUNTA 12
                if (binding.chboxp12A.isChecked) {
                    res10 = binding.chboxp12A.text.toString()
                }
                if (binding.chboxp12B.isChecked) {
                    res10 = res10+ "," + binding.chboxp12B.text.toString()
                }
                if (binding.chboxp12C.isChecked) {
                    res10 = res10+ "," + binding.chboxp12C.text.toString()
                }
                if (binding.chboxp12D.isChecked) {
                    res10 = res10+ "," + binding.chboxp12D.text.toString()
                }
                if (binding.chboxp12E.isChecked) {
                    res10 = res10+ "," + binding.chboxp12E.text.toString()
                }
                if (binding.chboxp12F.isChecked) {
                    res10 = res10+ "," + binding.chboxp12F.text.toString()
                }
                if (binding.chboxp12G.isChecked) {
                    res10 = res10+ "," + binding.chboxp12G.text.toString()
                }
                if (binding.chboxp12H.isChecked) {
                    res10 = res10+ "," + binding.chboxp12H.text.toString()
                }
                if (binding.chboxp12I.isChecked) {
                    res10 = res10+ "," + binding.chboxp12I.text.toString()
                }
                if (binding.chboxp12J.isChecked) {
                    res10 = res10+ "," + binding.chboxp12J.text.toString()
                }

                //VARIABLE ALMACENA RESPUESTA MÚLTIPLE PREGUNTA 13
                if (binding.chboxp13A.isChecked) {
                    res11 = binding.chboxp13A.text.toString()
                }
                if (binding.chboxp13B.isChecked) {
                    res11 = res11+ "," + binding.chboxp13B.text.toString()
                }
                if (binding.chboxp13C.isChecked) {
                    res11 = res11+ "," + binding.chboxp13C.text.toString()
                }
                if (binding.chboxp13D.isChecked) {
                    res11 = res11+ "," + binding.chboxp13D.text.toString()
                }
                if (binding.chboxp13E.isChecked) {
                    res11 = res11+ "," + binding.chboxp13E.text.toString()
                }
                if (binding.chboxp13F.isChecked) {
                    res11 = res11+ "," + binding.chboxp13F.text.toString()
                }
                if (binding.chboxp13G.isChecked) {
                    res11 = res11+ "," + binding.chboxp13G.text.toString()
                }

                //VARIABLE ALMACENA RESPUESTA UNICA PREGUNTA 14
                if (binding.rbp14A.isChecked) {
                    binding.rbp14A.text.toString();
                } else if (binding.rbp14B.isChecked) {
                    res12 = binding.rbp14B.text.toString();
                } else if (binding.rbp14C.isChecked) {
                    res12 = binding.rbp14C.text.toString();
                } else if (binding.rbp14D.isChecked) {
                    res12 = binding.rbp14D.text.toString();
                } else if (binding.rbp14E.isChecked) {
                    res12 = binding.rbp14E.text.toString();
                } else if (binding.rbp14F.isChecked) {
                    res12 = binding.rbp14F.text.toString();
                }

                //VARIABLE ALMACENA RESPUESTA UNICA PREGUNTA 15
                res13 = binding.editTxtp15.text.toString();

                    //VARIABLE ALMACENA RESPUESTA MÚLTIPLE PREGUNTA 16
                if (binding.chboxp16A.isChecked) {
                    res14 = binding.chboxp16A.text.toString()
                }
                if (binding.chboxp16B.isChecked) {
                    res14 = res14+ "," + binding.chboxp16B.text.toString()
                }
                if (binding.chboxp16C.isChecked) {
                    res14 = res14+ "," + binding.chboxp16C.text.toString()
                }
                if (binding.chboxp16D.isChecked) {
                    res14 = res14+ "," + binding.chboxp16D.text.toString()
                }
                if (binding.chboxp16E.isChecked) {
                    res14 = res14+ "," + binding.chboxp16E.text.toString()
                }
                if (binding.chboxp16F.isChecked) {
                    res14 = res14+ "," + binding.chboxp16F.text.toString()
                }
                if (binding.chboxp16G.isChecked) {
                    res14 = res14+ "," + binding.chboxp16G.text.toString()
                }
                if (binding.chboxp16H.isChecked) {
                    res14 = res14+ "," + binding.chboxp16H.text.toString()
                }
                if (binding.chboxp16I.isChecked) {
                    res14 = res14+ "," + binding.chboxp16I.text.toString()
                }

                //VARIABLE ALMACENA RESPUESTA UNICA PREGUNTA 17
                if (binding.rbYesp17A.isChecked) {
                    res15 = binding.rbYesp17A.text.toString();
                } else if (binding.rbNop17B.isChecked) {
                    res15 = binding.rbNop17B.text.toString();
                }


                //VARIABLE ALMACENA RESPUESTA UNICA PREGUNTA 15
                res16 = binding.editTxtp18.text.toString();

                ArchitectureDataBase.getDatabase(requireContext()).ObservationDao().saveObservation(
                    ObservationEntity(0, resnameuser.toString(),reslenght.toString(),reslaltitude.toString(), res1.toString(),res3.toString()
                        ,res4.toString(), res5.toString(),res6.toString() ,res7.toString(),res8.toString(),res9.toString() ,res10.toString()
                        ,res11.toString() ,res12.toString() ,res13.toString(),res14.toString()
                        ,res15.toString(),res16.toString()))


                Toast.makeText(context, "Datos guardados satisfactoriamente", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack(R.id.menuModelsFragment,false)
            }

        }
    }

    private fun getLocation() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) else {
            fusedLocationProviderClient.lastLocation?.addOnSuccessListener {
                if (it == null) {
                    Toast.makeText(
                        requireContext(),
                        "no se puede obtener la locacion",
                        Toast.LENGTH_SHORT
                    ).show()
                } else it.apply {
                    binding.latitude?.text = it.latitude.toString()
                    binding.length?.text = it.longitude.toString()
                }
            }
        }
    }


    private fun others() {


        //PREGUNTA 1 OPCION D (Otro)
        binding.rbp1D.setOnClickListener {
            if (binding.rbp1D.isChecked) {
                binding.txtOtherp1.visibility = View.VISIBLE
            } else {
                binding.txtOtherp1.visibility = View.GONE
            }
        }

        //PREGUNTA 2 OPCION A (Cúantos)
        binding.rbYesp2A.setOnClickListener {
            if (binding.rbYesp2A.isChecked) {
                binding.txtCuantosp2A.visibility = View.VISIBLE
            } else {
                binding.txtCuantosp2A.visibility = View.GONE
            }
        }

        /*
        //PREGUNTA 7 OPCION G (Otro)
        binding.chboxp7G.setOnClickListener {
            if (binding.chboxp7G.isChecked) {
                binding.txtOtherp7.visibility = View.VISIBLE
            } else {
                binding.txtOtherp7.visibility = View.GONE
            }
        }
*/
        //PREGUNTA 10 OPCION J (Otro)
        binding.chboxp10J.setOnClickListener {
            if (binding.chboxp10J.isChecked) {
                binding.txtOtherp10.visibility = View.VISIBLE
            } else {
                binding.txtOtherp10.visibility = View.GONE
            }
        }

        //PREGUNTA 10 OPCION J (Otro)
        binding.chboxp11N.setOnClickListener {
            if (binding.chboxp11N.isChecked) {
                binding.txtOtherp11.visibility = View.VISIBLE
            } else {
                binding.txtOtherp11.visibility = View.GONE
            }
        }

        //PREGUNTA 12 OPCION J (Otro)
        binding.chboxp12J.setOnClickListener {
            if (binding.chboxp12J.isChecked) {
                binding.txtOtherp12.visibility = View.VISIBLE
            } else {
                binding.txtOtherp12.visibility = View.GONE
            }
        }

        //PREGUNTA 16 OPCION I (Otro)
        binding.chboxp16I.setOnClickListener {
            if (binding.chboxp16I.isChecked) {
                binding.txtOtherp16.visibility = View.VISIBLE
            } else {
                binding.txtOtherp16.visibility = View.GONE
            }
        }
    }
}