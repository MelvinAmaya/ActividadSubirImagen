package com.example.actividadsubirimagen

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class Adapter2 (fragment: FragmentActivity, private val itesm:List<String> ):
    FragmentStateAdapter(fragment){
    override fun getItemCount(): Int {
        return itesm.size
    }

    override fun createFragment(position: Int): Fragment {
        val FragmentUno = UpImageFragment()
        val fragmentDos = CreadorFragment()

        return when(position){
            0->{
                FragmentUno
            }
            1->{
                fragmentDos
            }
            else-> FragmentUno
        }

    }
}