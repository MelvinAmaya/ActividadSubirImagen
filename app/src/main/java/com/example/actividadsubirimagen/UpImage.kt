package com.example.actividadsubirimagen

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_up_image.*

class UpImage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_up_image)

        val lista = listOf(
            "Subir Imagen",
            "Creador"
        )
        val icons = listOf(
            R.drawable.ic_baseline_login_24,
            R.drawable.ic_baseline_how_to_reg_24
        )
        pagina2.adapter = Adapter2(this, lista)

        TabLayoutMediator(tab_Layout2,pagina2){tab,position ->
            tab.text=lista[position]
            tab.icon= ContextCompat.getDrawable(this,icons[position])

        }.attach()


    }
}