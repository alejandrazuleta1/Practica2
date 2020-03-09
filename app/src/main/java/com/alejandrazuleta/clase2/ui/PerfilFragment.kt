package com.alejandrazuleta.clase2.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alejandrazuleta.clase2.R


/**
 * A simple [Fragment] subclass.
 */
class PerfilFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_perfil, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //ponerle la actionbar mas grande
        //val actionBar: ActionBar? = (activity as AppCompatActivity?)!!.supportActionBar

    }


}
