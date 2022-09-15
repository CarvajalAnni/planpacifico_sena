package com.example.planpacifico.ui.modules.architecture.characterization

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController

import com.example.planpacifico.R
import com.example.planpacifico.data.localdb.AppDatabase
import com.example.planpacifico.data.localdb.ArchitectureDataBase
import com.example.planpacifico.data.models.entities.Characterization2Entity
import com.example.planpacifico.databinding.FragmentCharacterize2Binding


import kotlinx.coroutines.launch

class CharacterizeFragment2 : Fragment(R.layout.fragment_characterize2) {




    var res18 : String? = null
    var res19 : String? = null
    var res20 : String? = null
    var res21 : String? = null
    var res22 : String? = null
    var res23 : String? = null
    var res24 : String? = null
    var res25 : String? = null
    var res26 : String? = null
    var res27 : String? = null
    var res28 : String? = null
    var res29 : String? = null
    var res30 : String? = null
    var res31 : String? = null
    var res32 : String? = null
    var res33 : String? = null
    var res34 : String? = null
    var res35 : String? = null


    private lateinit var binding: FragmentCharacterize2Binding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCharacterize2Binding.bind(view)
        guardar()
        clicks()

    }



    private fun guardar() {

            binding.btnNext.setOnClickListener {
                //pregunta18
                lifecycleScope.launch {
                    ArchitectureDataBase.getDatabase(requireContext()).Characterization2Dao()
                    if (binding.si18.isChecked) {
                        res18 = binding.si18.text.toString()
                    } else {
                        (binding.no18.isChecked)
                        res18 = binding.si18.text.toString()
                    }

                    //pregunta19
                    var guar19 = binding.res191.text.toString()
                    var guar19a = binding.res192.text.toString()
                    var guar19b = binding.res193.text.toString()
                    var guar19c = binding.res194.text.toString()
                    var guar19d = binding.res195.text.toString()
                    var guar19e = binding.res196.text.toString()
                    var guar19f = binding.res197.text.toString()
                    var guar19g = binding.res198.text.toString()
                    var guar19h = binding.res199.text.toString()
                    var guar19i = binding.res1910.text.toString()
                    var guar19j = binding.res1911.text.toString()
                    var guar19k = binding.res1912.text.toString()
                    var guar19l = binding.res1913.text.toString()
                    var guar19m = binding.res1914.text.toString()
                    var guar19n = binding.res1915.text.toString()
                    var guar19ñ = binding.res1916.text.toString()
                    var guar19o = binding.res1917.text.toString()
                    var guar19p = binding.res1918.text.toString()
                    var guar19q = binding.res1919.text.toString()
                    var guar19r = binding.res1920.text.toString()
                    var guar19s = binding.res1921.text.toString()
                    var guar19t = binding.res1922.text.toString()
                    var guar19v = binding.ch19.text.toString()

                    res19 =
                        "${guar19}+${guar19a}+${guar19b}+${guar19c}+${guar19d}+${guar19e}+${guar19f}+${guar19g}+${guar19h}+${guar19i}+${guar19j}+${guar19k}+${guar19l}" +
                                "+${guar19m}${guar19n}+${guar19ñ}${guar19o}+${guar19p}${guar19q}+${guar19r}${guar19s}+${guar19t}+${guar19v}"


                    //pregunta20
                    var guar20 = binding.Largo.text.toString()
                    var guar20a = binding.ancho.text.toString()
                    var guar20b = binding.area.text.toString()
                    res20 = "${guar20}+${guar20a}+${guar20b}"


                    //pregunta21
                    var guar21 = binding.Largo.text.toString()
                    var guar21a = binding.ancho.text.toString()
                    var guar21b = binding.area.text.toString()
                    res20 = "${guar21}+${guar21a}+${guar21b}"


                    //pregunta22
                    if (binding.si22.isChecked) {
                        res22 = binding.si22.text.toString()
                    } else {
                        (binding.no22.isChecked)
                        res22 = binding.no22.text.toString()
                    }

                    //preginta23
                    if (binding.res23pri.isChecked) {
                        res23 = binding.res23pri.text.toString()
                    } else if (binding.res23a.isChecked){
                        res23 = res23 + "," + binding.res23a.text.toString()
                    }  else if (binding.res23b.isChecked){
                    res23 = res23 + "," + binding.res23b.text.toString()
                    }else if (binding.res23c.isChecked){
                        res23 = res23 + "," + binding.res23c.text.toString()
                    }else if (binding.res23d.isChecked){
                        res23 = res23 + "," + binding.res23d.text.toString()
                    }else if (binding.res23e.isChecked){
                        res23 = res23 + "," + binding.res23e.text.toString()
                    }





                    //pregunta24
                    res24 = binding.aqui24l.text.toString()

                    //pregunta25
                    if (binding.si25.isChecked) {
                        res25 = binding.si25.text.toString()
                    } else {
                        (binding.no25.isChecked)
                        res25 = binding.no25.text.toString()
                    }

                    //pregunta26
                    if (binding.res261.isChecked) {
                        res26 = binding.res261.text.toString()
                    } else if (binding.res262.isChecked) {
                        res26 = binding.res262.text.toString()
                    } else if (binding.res263.isChecked) {
                        res26 = binding.res263.text.toString()
                    } else if (binding.res264.isChecked) {
                        res26 = binding.res264.text.toString()
                    } else if (binding.res265.isChecked) {
                        res26 = binding.res265.text.toString()
                    } else if (binding.res266.isChecked) {
                        res26 = binding.res266.text.toString()
                    } else if (binding.res267.isChecked) {
                        res26 = binding.res267.text.toString()
                    }


                    //pregunta27
                    if (binding.si27.isChecked) {
                        res27 = binding.si27.text.toString()
                    } else if (binding.no27.isChecked) {
                        res27 = binding.no27.text.toString()

                    }


                    //pregunta28

                    if (binding.res28pri.isChecked) {
                        res28 = binding.res28pri.text.toString()
                    } else if (binding.res28a.isChecked){
                        res28 = res28 + "," + binding.res28a.text.toString()
                    }  else if (binding.res28b.isChecked){
                        res28 = res28 + "," + binding.res28b.text.toString()
                    }else if (binding.res28c.isChecked){
                        res28 = res28 + "," + binding.res28c.text.toString()
                    }else if (binding.res28d.isChecked){
                        res28 = res28 + "," + binding.res28d.text.toString()
                    }else if (binding.res28e.isChecked){
                        res28 = res28 + "," + binding.res28e.text.toString()
                    }



                    //prefgunta29
                    if (binding.res29pri.isChecked) {
                        res29 = binding.res29pri.text.toString()
                    } else if (binding.res29a.isChecked){
                        res29 = res29 + "," + binding.res29a.text.toString()
                    }  else if (binding.res29b.isChecked){
                        res29 = res29 + "," + binding.res29b.text.toString()
                    }else if (binding.res29c.isChecked){
                        res29 = res29 + "," + binding.res29c.text.toString()
                    }else if (binding.res29d.isChecked){
                        res29 = res29 + "," + binding.res29d.text.toString()
                    }else if (binding.res29e.isChecked){
                        res29 = res29 + "," + binding.res29e.text.toString()
                    }else if (binding.res29f.isChecked){
                        res29 = res29 + "," + binding.res29f.text.toString()
                    }else if (binding.res29g.isChecked){
                        res29 = res29 + "," + binding.res29g.text.toString()
                    }else if (binding.ch29.isChecked){
                        res29 = res29 + "," + binding.ch29.text.toString()
                    }



                    //pregunta30

                    if (binding.res30pri.isChecked) {
                        res30 = binding.res30pri.text.toString()
                    } else if (binding.res30a.isChecked){
                        res30 = res30 + "," + binding.res30a.text.toString()
                    }  else if (binding.res30b.isChecked){
                        res30 = res30 + "," + binding.res30b.text.toString()
                    }else if (binding.res30c.isChecked){
                        res30 = res30 + "," + binding.res30c.text.toString()
                    }else if (binding.res30d.isChecked){
                        res30 = res30 + "," + binding.res30d.text.toString()
                    }else if (binding.res30e.isChecked){
                        res30 = res30 + "," + binding.res30e.text.toString()
                    }else if (binding.res30f.isChecked){
                        res30 = res30 + "," + binding.res30f.text.toString()
                    }else if (binding.ch30.isChecked){
                        res30 = res30 + "," + binding.ch30.text.toString()
                    }


                    //pregunta31
                    if (binding.res31pri.isChecked) {
                        res31 = binding.res31pri.text.toString()
                    } else if (binding.res31a.isChecked){
                        res31 = res31 + "," + binding.res31a.text.toString()
                    }  else if (binding.res31b.isChecked){
                        res31 = res31 + "," + binding.res31b.text.toString()
                    }else if (binding.res31c.isChecked){
                        res31 = res31 + "," + binding.res31c.text.toString()
                    }else if (binding.res31d.isChecked){
                        res31 = res31 + "," + binding.res31d.text.toString()
                    }else if (binding.res31e.isChecked){
                        res31 = res31 + "," + binding.res31e.text.toString()
                    }else if (binding.res31f.isChecked){
                        res31 = res31 + "," + binding.res31f.text.toString()
                    }else if (binding.ch31.isChecked){
                        res31 = res31 + "," + binding.ch31.text.toString()
                    }



                    //pregunta32
                    if (binding.res32pri.isChecked) {
                        res32 = binding.res32pri.text.toString()
                    } else if (binding.res32a.isChecked){
                        res32 = res32 + "," + binding.res32a.text.toString()
                    }  else if (binding.res32b.isChecked){
                        res32 = res32 + "," + binding.res32b.text.toString()
                    }else if (binding.res32c.isChecked){
                        res32 = res32 + "," + binding.res32c.text.toString()
                    }else if (binding.res32d.isChecked){
                        res32 = res32 + "," + binding.res32d.text.toString()
                    }else if (binding.res32e.isChecked){
                        res32 = res32 + "," + binding.res32e.text.toString()
                    }else if (binding.res32f.isChecked){
                        res32 = res32 + "," + binding.res32f.text.toString()
                    }else if (binding.ch32.isChecked){
                        res32 = res32 + "," + binding.ch32.text.toString()
                    }

                    //pregunta33
                    if (binding.res31pri.isChecked) {
                        res33 = binding.res33pri.text.toString()
                    } else if (binding.res33a.isChecked){
                        res33 = res33 + "," + binding.res33a.text.toString()
                    }  else if (binding.res33b.isChecked){
                        res33 = res33 + "," + binding.res33b.text.toString()
                    }else if (binding.res33c.isChecked){
                        res33 = res33 + "," + binding.res33c.text.toString()
                    }else if (binding.res33d.isChecked){
                        res33 = res33 + "," + binding.res33d.text.toString()
                    }else if (binding.res33e.isChecked){
                        res33 = res33 + "," + binding.res33e.text.toString()
                    }else if (binding.res33f.isChecked){
                        res33 = res33 + "," + binding.res33f.text.toString()
                    }else if (binding.ch33.isChecked){
                        res33 = res33 + "," + binding.ch33.text.toString()
                    }

                    //pregunta34
                    if (binding.res31pri.isChecked) {
                        res34 = binding.res34pri.text.toString()
                    } else if (binding.res34a.isChecked){
                        res34 = res34 + "," + binding.res34a.text.toString()
                    }  else if (binding.res34b.isChecked){
                        res34 = res34 + "," + binding.res34b.text.toString()
                    }else if (binding.ch34.isChecked){
                        res34 = res34 + "," + binding.ch34.text.toString()
                    }


                    //pregunta35
                    if (binding.res31pri.isChecked) {
                        res35 = binding.res35pri.text.toString()
                    } else if (binding.res35a.isChecked){
                        res35 = res35 + "," + binding.res35a.text.toString()
                    }  else if (binding.res35b.isChecked){
                        res35 = res35 + "," + binding.res35b.text.toString()
                    }else if (binding.res35c.isChecked){
                        res35 = res35 + "," + binding.res35c.text.toString()
                    }else if (binding.res35d.isChecked){
                        res35 = res35 + "," + binding.res35d.text.toString()
                    }else if (binding.res35e.isChecked){
                        res35 = res35 + "," + binding.res35e.text.toString()
                    }else if (binding.ch35.isChecked){
                        res35 = res35 + "," + binding.ch35.text.toString()
                    }



                    ArchitectureDataBase.getDatabase(requireContext()).Characterization2Dao().saveCharacterization2(
                        Characterization2Entity(
                            0,
                            res18.toString(),
                            res19.toString(),
                            res20.toString(),
                            res21.toString(),
                            res22.toString(),
                            res23.toString(),
                            res24.toString(),
                            res25.toString(),
                            res26.toString(),
                            res27.toString(),
                            res28.toString(),
                            res29.toString(),
                            res30.toString(),
                            res31.toString(),
                            res32.toString(),
                            res33.toString(),
                            res34.toString(),
                            res35.toString()
                        )
                    )

                    Toast.makeText(context, "Datos guardados satisfactoriamente", Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack(R.id.menuModelsFragment,false)

                }
            }
    }




    private fun clicks() {
        binding.ch19.setOnClickListener {
            if (binding.ch19.isChecked) {
                binding.cual19.visibility = View.VISIBLE
            } else {
                binding.cual19.visibility = View.GONE
            }
        }
        binding.ch23.setOnClickListener {
            if (binding.ch23.isChecked) {
                binding.cual23.visibility = View.VISIBLE

            } else {
                binding.cual23.visibility = View.GONE
            }
        }

        binding.ch29.setOnClickListener {
            if (binding.ch29.isChecked) {
                binding.cual29.visibility = View.VISIBLE
            } else {
                binding.cual29.visibility = View.GONE
            }
        }
        binding.ch30.setOnClickListener {
            if (binding.ch30.isChecked) {
                binding.cual30.visibility = View.VISIBLE
            } else {
                binding.cual30.visibility = View.GONE
            }
        }

        binding.ch31.setOnClickListener {
            if (binding.ch31.isChecked) {
                binding.cual31.visibility = View.VISIBLE
            } else {
                binding.cual31.visibility = View.GONE
            }
        }

        binding.ch32.setOnClickListener {
            if (binding.ch32.isChecked) {
                binding.cual32.visibility = View.VISIBLE
            } else {
                binding.cual32.visibility = View.GONE
            }
        }

        binding.ch33.setOnClickListener {
            if (binding.ch33.isChecked) {
                binding.cual33.visibility = View.VISIBLE
            } else {
                binding.cual33.visibility = View.GONE
            }
        }

        binding.ch34.setOnClickListener {
            if (binding.ch34.isChecked) {
                binding.cual34.visibility = View.VISIBLE
            } else {
                binding.cual34.visibility = View.GONE
            }
        }

        binding.ch35.setOnClickListener {
            if (binding.ch35.isChecked) {
                binding.cual35.visibility = View.VISIBLE
            } else {
                binding.cual35.visibility = View.GONE
            }
        }
    }
}