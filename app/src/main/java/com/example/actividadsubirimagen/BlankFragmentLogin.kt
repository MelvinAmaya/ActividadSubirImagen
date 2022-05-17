package com.example.actividadsubirimagen

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_blank_login.view.*


class BlankFragmentLogin : Fragment() {

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
        return inflater.inflate(R.layout.fragment_blank_login, container, false)
    }

    interface Comunicador{
        fun devolverDato(dato:String)
    }
    private var activityContenedora:Comunicador?=null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is Comunicador)
        {
            activityContenedora = context
        }
        else throw RuntimeException(
            context.toString()+" debe Implementat comunicador"
        )
    }

    override fun onDetach() {
        super.onDetach()
        activityContenedora=null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        requireView().loginbutton.setOnClickListener {
            if (!requireView().txtUsuario.text.toString().isEmpty() || !requireView().txtpass.text.toString().isEmpty()) {
                val email = requireView().txtUsuario.text.toString().trim()
                val pass = requireView().txtpass.text.toString().trim()

                auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener {
                    if (it.isSuccessful){
                        activityContenedora!!.devolverDato("1")

                    }
                    else{
                        Toast.makeText(context, "Acceso no autorizado! ", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else
            {
                Toast.makeText(context, "Ingrese todos los datos...", Toast.LENGTH_LONG).show()
            }
        }
    }
}