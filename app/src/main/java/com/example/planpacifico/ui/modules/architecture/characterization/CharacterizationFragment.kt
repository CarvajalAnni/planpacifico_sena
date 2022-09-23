package com.example.planpacifico.ui.modules.architecture.characterization

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.planpacifico.R
import com.example.planpacifico.core.Constants.ID_ZONE
import com.example.planpacifico.data.localdb.ArchitectureDataBase
import com.example.planpacifico.data.models.entities.CharacterizationEntity
import com.example.planpacifico.databinding.FragmentCharacterizationBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.launch

class CharacterizationFragment : Fragment(R.layout.fragment_characterization) {

    private lateinit var binding: FragmentCharacterizationBinding
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient


    var nameuser: String? = null
    var reslenght: String? = null
    var reslatitude: String? = null
    var resage: String? = null
    var resoccupation: String? = null
    var resgenere: String? = null
    var res1: String? = null
    var res2: String? = null
    var res3: String? = null
    var res4: String? = null
    var res5: String? = null
    var res6: String? = null
    var res7: String? = null
    var res8: String? = null
    var res9: String? = null
    var res10: String? = null
    var res11: String? = null
    var res12: String? = null
    var res13: String? = null
    var res14: String? = null
    var res15: String? = null
    var res16: String? = null
    var res17: String? = null
    var res18: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCharacterizationBinding.bind(view)

        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireActivity())
        res9 = "votacion:"
        res10 = "financiación:"
        res15 = "riesgo:"
        res16 = "vivienda:"
        res17 = "familia:"
        clicks()

        binding.btnUpdateCoordinates.setOnClickListener {
            getLocation();
        }

    }

    private fun clicks() {

        binding.btnSiguiCarac.setOnClickListener {
            lifecycleScope.launch {
                ArchitectureDataBase.getDatabase(requireContext()).CharacterizationDao()

                nameuser = binding.inputNombreUsuario.text.toString()
                reslenght = binding.length.text.toString()
                reslatitude = binding.latitude.text.toString()
                resage = binding.txtAge.text.toString()
                resoccupation = binding.txtOccupation.text.toString()


                if (binding.rbFem.isChecked){
                    resgenere = binding.rbFem.text.toString()
                }else if (binding.rbMas.isChecked){
                    resgenere = binding.rbMas.text.toString()
                }


                if (binding.rbRes1pre1.isChecked) {
                    res1 = binding.rbRes1pre1.text.toString()
                } else if (binding.rbRes2pre1.isChecked) {
                    res1 = binding.rbRes2pre1.text.toString()
                } else if (binding.rbRes3pre1.isChecked) {
                    res1 = binding.rbRes3pre1.text.toString()
                } else if (binding.rbRes4pre1.isChecked) {
                    res1 = binding.rbRes4pre1.text.toString()
                } else if (binding.rbRes5pre1.isChecked) {
                    res1 = binding.rbRes5pre1.text.toString()
                }

                if (binding.rbRes1pre2.isChecked) {
                    res2 = binding.rbRes1pre2.text.toString()
                } else if (binding.rbRes2pre2.isChecked) {
                    res2 = binding.rbRes2pre2.text.toString()
                } else if (binding.rbRes3pre2.isChecked) {
                    res2 = binding.rbRes3pre2.text.toString()
                } else if (binding.rbRes4pre2.isChecked) {
                    res2 = binding.rbRes4pre2.text.toString()
                } else if (binding.rbRes5pre2.isChecked) {
                    res2 = binding.rbRes5pre2.text.toString()
                } else if (binding.rbRes6pre2.isChecked) {
                    res2 = binding.rbRes6pre2.text.toString()
                } else if (binding.rbRes7pre2.isChecked) {
                    res2 = binding.txtRes7pre2.text.toString()
                }

                if (binding.rbRes1pre3.isChecked) {
                    res3 = binding.rbRes1pre3.text.toString()
                } else if (binding.rbRes2pre3.isChecked) {
                    res3 = binding.rbRes2pre3.text.toString()
                } else if (binding.rbRes3pre3.isChecked) {
                    res3 = binding.rbRes3pre3.text.toString()
                } else if (binding.rbRes4pre3.isChecked) {
                    res3 = binding.rbRes4pre3.text.toString()
                } else if (binding.rbRes5pre3.isChecked) {
                    res3 = binding.rbRes5pre3.text.toString()
                } else if (binding.rbRes6pre3.isChecked) {
                    res3 = binding.rbRes6pre3.text.toString()
                } else if (binding.rbRes7pre3.isChecked) {
                    res3 = binding.rbRes7pre3.text.toString()
                }

                if (binding.rbRes1pre4.isChecked) {
                    res4 = binding.rbRes1pre4.text.toString()
                } else if (binding.rbRes2pre4.isChecked) {
                    res4 = binding.rbRes2pre4.text.toString()
                } else if (binding.rbRes3pre4.isChecked) {
                    res4 = binding.rbRes3pre4.text.toString()
                } else if (binding.rbRes4pre4.isChecked) {
                    res4 = binding.rbRes4pre4.text.toString()
                } else if (binding.rbRes5pre4.isChecked) {
                    res4 = binding.rbRes5pre4.text.toString()
                } else if (binding.rbRes6pre4.isChecked) {
                    res4 = binding.rbRes6pre4.text.toString()
                } else if (binding.rbRes7pre4.isChecked) {
                    res4 = binding.rbRes7pre4.text.toString()
                }

                if (binding.rbRes1pre5.isChecked) {
                    res5 = binding.rbRes1pre5.text.toString()
                } else if (binding.rbRes2pre5.isChecked) {
                    res5 = binding.rbRes2pre5.text.toString()
                } else if (binding.rbRes3pre5.isChecked) {
                    res5 = binding.rbRes3pre5.text.toString()
                } else if (binding.rbRes4pre5.isChecked) {
                    res5 = binding.rbRes4pre5.text.toString()
                } else if (binding.rbRes5pre5.isChecked) {
                    res5 = binding.rbRes5pre5.text.toString()
                } else if (binding.rbRes6pre5.isChecked) {
                    res5 = binding.rbRes6pre5.text.toString()
                } else if (binding.rbRes7pre5.isChecked) {
                    res5 = binding.rbRes7pre5.text.toString()
                }

                if (binding.rbRes1pre6.isChecked) {
                    res6 = binding.rbRes1pre6.text.toString()
                } else if (binding.rbRes2pre6.isChecked) {
                    res6 = binding.rbRes2pre6.text.toString()
                } else if (binding.rbRes3pre6.isChecked) {
                    res6 = binding.rbRes3pre6.text.toString()
                } else if (binding.rbRes4pre6.isChecked) {
                    res6 = binding.rbRes4pre6.text.toString()
                } else if (binding.rbRes5pre6.isChecked) {
                    res6 = binding.rbRes5pre6.text.toString()
                } else if (binding.rbRes6pre6.isChecked) {
                    res6 = binding.rbRes6pre6.text.toString()
                } else if (binding.rbRes7pre6.isChecked) {
                    res6 = binding.rbRes7pre6.text.toString()
                } else if (binding.rbRes8pre6.isChecked) {
                    res6 = binding.rbRes8pre6.text.toString()
                }

                if (binding.rbRes1pre7.isChecked) {
                    res7 = binding.rbRes1pre7.text.toString()
                } else if (binding.rbRes2pre7.isChecked) {
                    res7 = binding.rbRes2pre7.text.toString()
                } else if (binding.rbRes3pre7.isChecked) {
                    res7 = binding.rbRes3pre7.text.toString()
                } else if (binding.rbRes4pre7.isChecked) {
                    res7 = binding.rbRes4pre7.text.toString()
                } else if (binding.rbRes5pre7.isChecked) {
                    res7 = binding.rbRes5pre7.text.toString()
                }

                if (binding.rbRes1pre8.isChecked) {
                    res8 = binding.rbRes1pre8.text.toString()
                } else if (binding.rbRes2pre8.isChecked) {
                    res8 = binding.rbRes2pre8.text.toString()
                } else if (binding.rbRes3pre8.isChecked) {
                    res8 = binding.rbRes3pre8.text.toString()
                } else if (binding.rbRes4pre8.isChecked) {
                    res8 = binding.rbRes4pre8.text.toString()
                } else if (binding.rbRes5pre8.isChecked) {
                    res8 = binding.rbRes5pre8.text.toString()
                }

                if (binding.chRes1pre9.isChecked) {
                    res9 = binding.chRes1pre9.text.toString()
                }
                if (binding.chRes2pre9.isChecked) {
                    res9 = res9+ "," + binding.chRes2pre9.text.toString()
                }
                if (binding.chRes3pre9.isChecked) {
                    res9 = res9+ "," + binding.chRes3pre9.text.toString()
                }
                if (binding.chRes4pre9.isChecked) {
                    res9 = res9+ "," + binding.chRes4pre9.text.toString()
                }
                if (binding.chRes5pre9.isChecked) {
                    res9 = res9+ "," + binding.chRes5pre9.text.toString()
                }
                if (binding.chRes6pre9.isChecked) {
                    res9 = res9+ "," + binding.chRes6pre9.text.toString()
                }
                if (binding.chRes7pre9.isChecked) {
                    res9 = res9+ "," + binding.chRes7pre9.text.toString()
                }
                if (binding.chRes8pre9.isChecked) {
                    res9 = res9+ "," + binding.txtRes8pre9.text.toString()
                }


                if (binding.chRes1pre10.isChecked) {
                    res10 = binding.chRes1pre10.text.toString()
                }
                if (binding.chRes2pre10.isChecked) {
                    res10 = res10+ "," + binding.chRes2pre10.text.toString()
                }
                if (binding.chRes3pre10.isChecked) {
                    res10 = res10+ "," + binding.chRes3pre10.text.toString()
                }
                if (binding.chRes4pre10.isChecked) {
                    res10 = res10+ "," + binding.chRes4pre10.text.toString()
                }
                if (binding.chRes5pre10.isChecked) {
                    res10 = res10+ "," + binding.chRes5pre10.text.toString()
                }
                if (binding.chRes6pre10.isChecked) {
                    res10 = res10+ "," + binding.chRes6pre10.text.toString()
                }
                if (binding.chRes7pre10.isChecked) {
                    res10 = res10+ "," + binding.chRes7pre10.text.toString()
                }
                if (binding.chRes8pre10.isChecked) {
                    res10 = res10+ "," + binding.chRes8pre10.text.toString()
                }
                if (binding.chRes9pre10.isChecked) {
                    res10 = res10+ "," + binding.chRes9pre10.text.toString()
                }
                if (binding.chRes8pre10.isChecked) {
                    res10 = res10+ "," + binding.txtRes10pre10.text.toString()
                }



                if (binding.rbRes1pre11.isChecked) {
                    res11 = binding.rbRes1pre11.text.toString()
                } else if (binding.rbRes2pre11.isChecked) {
                    res11 = binding.rbRes2pre11.text.toString()
                } else if (binding.rbRes3pre11.isChecked) {
                    res11 = binding.rbRes3pre11.text.toString()
                } else if (binding.rbRes4pre11.isChecked) {
                    res11 = binding.rbRes4pre11.text.toString()
                } else if (binding.rbRes5pre11.isChecked) {
                    res11 = binding.rbRes5pre11.text.toString()
                } else if (binding.rbRes6pre11.isChecked) {
                    res11 = binding.rbRes6pre11.text.toString()
                } else if (binding.rbRes7pre11.isChecked) {
                    res11 = binding.rbRes7pre11.text.toString()
                } else if (binding.rbRes8pre11.isChecked) {
                    res11 = binding.rbRes8pre11.text.toString()
                }


                if (binding.chboxp3A.isChecked) {
                    res12 = binding.chboxp3A.text.toString()
                }
                if (binding.chboxp3B.isChecked) {
                    res12 = res12+ "," + binding.chboxp3B.text.toString()
                }
                if (binding.chboxp3C.isChecked) {
                    res12 = res12+ "," + binding.chboxp3C.text.toString()
                }
                if (binding.chboxp3D.isChecked) {
                    res12 = res12+ "," + binding.chboxp3D.text.toString()
                }
                if (binding.chboxp3E.isChecked) {
                    res12 = res12+ "," + binding.chboxp3E.text.toString()
                }
                if (binding.chboxp3F.isChecked) {
                    res12 = res12+ "," + binding.chboxp3F.text.toString()
                }

                if (binding.chboxp7A.isChecked) {
                    res13 = binding.chboxp7A.text.toString()
                }
                if (binding.chboxp7B.isChecked) {
                    res13 = res13+ "," + binding.chboxp7B.text.toString()
                }
                if (binding.chboxp7C.isChecked) {
                    res13 = res13+ "," + binding.chboxp7C.text.toString()
                }
                if (binding.chboxp7D.isChecked) {
                    res13 = res13+ "," + binding.chboxp7D.text.toString()
                }
                if (binding.chboxp7F.isChecked) {
                    res13 = res13+ "," + binding.chboxp7F.text.toString()
                }
                if (binding.chboxp7G.isChecked) {
                    res13 = res13+ "," + binding.chboxp7G.text.toString()
                }

                /*res12 = binding.txtRes1pre12.text.toString()

                if (binding.chRes1pre13.isChecked) {
                    res13 = binding.chRes1pre13.text.toString()
                }
                if (binding.chRes2pre13.isChecked) {
                    res13 = res13+ "," + binding.chRes2pre13.text.toString()
                }
                if (binding.chRes3pre13.isChecked) {
                    res13 = res13+ "," + binding.chRes3pre13.text.toString()
                }
                if (binding.chRes4pre13.isChecked) {
                    res13 = res13+ "," + binding.chRes4pre13.text.toString()
                }
                if (binding.chRes5pre13.isChecked) {
                    res13 = res13+ "," + binding.chRes5pre13.text.toString()
                }
                if (binding.chRes6pre13.isChecked) {
                    res13 = res13+ "," + binding.chRes6pre13.text.toString()
                }
                if (binding.chRes7pre13.isChecked) {
                    res13 = res13+ "," + binding.chRes7pre13.text.toString()
                }
                if (binding.chRes8pre13.isChecked) {
                    res13 = res13+ "," + binding.chRes8pre13.text.toString()
                }
                if (binding.chRes9pre13.isChecked) {
                    res13 = res13+ "," + binding.txtRes9pre13.text.toString()
                }

                if (binding.rbRes1pre14.isChecked) {
                    res14 = binding.rbRes1pre14.text.toString()
                } else if (binding.rbRes2pre14.isChecked) {
                    res14 = binding.rbRes2pre14.text.toString()
                }*/

                if (binding.chRes1pre15.isChecked) {
                    res15 = binding.chRes1pre15.text.toString()
                }
                if (binding.chRes2pre15.isChecked) {
                    res15 = res15+ "," + binding.chRes2pre15.text.toString()
                }
                if (binding.chRes3pre15.isChecked) {
                    res15 = res15+ "," + binding.chRes3pre15.text.toString()
                }
                if (binding.chRes4pre15.isChecked) {
                    res15 = res15+ "," + binding.chRes4pre15.text.toString()
                }
                if (binding.chRes5pre15.isChecked) {
                    res15 = res15+ "," + binding.chRes5pre15.text.toString()
                }
                if (binding.chRes6pre15.isChecked) {
                    res15 = res15+ "," + binding.txtRes6pre15.text.toString()
                }
                if (binding.chRes7pre15.isChecked) {
                    res15 = res15+ "," + binding.chRes7pre15.text.toString()
                }

                if (binding.chRes1pre16.isChecked) {
                    res16 = binding.chRes1pre16.text.toString()
                }
                if (binding.chRes2pre16.isChecked) {
                    res16 = res16+ "," + binding.chRes2pre16.text.toString()
                }
                if (binding.chRes3pre16.isChecked) {
                    res16 = res16+ "," + binding.chRes3pre16.text.toString()
                }
                if (binding.chRes4pre15.isChecked) {
                    res16 = res16 + "," + binding.chRes4pre16.text.toString()
                }

                if (binding.chResPadreAPre17.isChecked) {
                    res17 = "A Padre"
                }
                if (binding.chResPadreMPre17.isChecked) {
                    res17 = res17 + "," + "M Padre"
                }
                if (binding.chResPadreDPre17.isChecked) {
                    res17 = res17 + "," + "D Padre"
                }
                if (binding.chResMadreAPre17.isChecked) {
                    res17 = res17 + "," + "A Madre"
                }
                if (binding.chResMadreMPre17.isChecked) {
                    res17 = res17 + "," + "M Madre"
                }
                if (binding.chResMadreDPre17.isChecked) {
                    res17 = res17 + "," + "D Madre"
                }
                if (binding.chResHijosAPre17.isChecked) {
                    res17 = res17 + ","+"A Hijos"
                }
                if (binding.chResHijosMPre17.isChecked) {
                    res17 = res17 + "," + "M Hijos"
                }
                if (binding.chResHijosDPre17.isChecked) {
                    res17 = res17 + "," + "D Hijos"
                }
                if (binding.chResAbuelosAPre17.isChecked) {
                    res17 = res17 + "," + "A Abuelos"
                }
                if (binding.chResAbuelosMPre17.isChecked) {
                    res17 = res17 + "," + "M Abuelos"
                }
                if (binding.chResAbuelosDPre17.isChecked) {
                    res17 = res17 + "," + "D Abuelos"
                }
                if (binding.chResOtrosAPre17.isChecked) {
                    res17 = res17 + "," + "A Otros"
                }
                if (binding.chResOtrosMPre17.isChecked) {
                    res17 = res17 + "," + "M Otros"
                }
                if (binding.chResOtrosDPre17.isChecked) {
                    res17 = res17 + "," + "D Otros"
                }

                res18 = binding.txtRes1pre12.text.toString()

                ArchitectureDataBase.getDatabase(requireContext()).CharacterizationDao()
                    .saveCharacterization(
                        CharacterizationEntity(
                            0,
                            ID_ZONE,
                            nameuser.toString(),
                            reslenght.toString(),
                            reslatitude.toString(),
                            resage.toString(),
                            resoccupation.toString(),
                            resgenere.toString(),
                            res1.toString(),
                            res2.toString(),
                            res3.toString(),
                            res4.toString(),
                            res5.toString(),
                            res6.toString(),
                            res7.toString(),
                            res8.toString(),
                            res9.toString(),
                            res10.toString(),
                            res11.toString(),
                            res12.toString(),
                            res13.toString(),
                            res15.toString(),
                            res16.toString(),
                            res17.toString(),
                            res18.toString()
                        )
                    )

                findNavController().navigate(R.id.action_characterizationFragment_to_characterizeFragment2)

            }
        }


        if (binding.rgPregunta2d.checkedRadioButtonId == R.id.rbRes7pre2) {
            binding.txRes7pre2.visibility = View.VISIBLE
        } else {
            binding.txRes7pre2.visibility = View.GONE
        }

        binding.rbRes7pre2.setOnClickListener {
            if (binding.rbRes7pre2.isChecked) {
                binding.txRes7pre2.visibility = View.VISIBLE
            }else{
                binding.txRes7pre2.visibility = View.GONE
            }
        }
        binding.chRes8pre9.setOnClickListener {
            if (binding.chRes8pre9.isChecked) {
                binding.txRes8pre9.visibility = View.VISIBLE
            } else {
                binding.txRes8pre9.visibility = View.GONE
            }
        }
        binding.chRes10pre10.setOnClickListener {
            if (binding.chRes10pre10.isChecked) {
                binding.txRes10pre10.visibility = View.VISIBLE
            } else {
                binding.txRes10pre10.visibility = View.GONE
            }
        }
        binding.chRes6pre15.setOnClickListener {
            if (binding.chRes6pre15.isChecked) {
                binding.txRes6pre15.visibility = View.VISIBLE
            } else {
                binding.txRes6pre15.visibility = View.GONE
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
}
