package com.example.actividadsubirimagen

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_blank_registro.view.*


class BlankFragmentRegistro : Fragment() {

    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank_registro, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        requireView().btnRegistro.setOnClickListener{
            val email2 = requireView().txtUsuario2.text.toString().trim()
            val pass2 = requireView().txtpass2.text.toString().trim()
            val conpass = requireView().txtconpass2.text.toString().trim()

            if (email2.equals("")||pass2.equals("")||conpass.equals(""))
            {
                Toast.makeText(context,"Ingrese todos los datos...", Toast.LENGTH_LONG).show()
            }
            else
            {
                if (conpass.equals(pass2))
                {
                    if (pass2.length<6)
                    {
                        Toast.makeText(context,"La contraseña tiene que tener minimo 6 digitos...",
                            Toast.LENGTH_LONG).show()
                    }
                    else
                    {
                        auth.createUserWithEmailAndPassword(email2, pass2).addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(ContentValues.TAG, "createUserWithEmail:success")
                                val user = auth.currentUser
                                Toast.makeText(context,"Se guardo el registro...", Toast.LENGTH_LONG).show()
                                requireView().txtUsuario2.text=null
                                requireView().txtpass2.text=null
                                requireView().txtconpass2.text=null
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(ContentValues.TAG, "createUserWithEmail:failure", task.exception)
                                Toast.makeText(context, "No se pudo guardar el registro", Toast.LENGTH_SHORT).show()
                            }
                        }
                        //activityContenedora2!!.DatosBase(email2,pass2)
                    }
                }
                else
                {
                    Toast.makeText(context,"La contraseñas no coinciden...", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

}