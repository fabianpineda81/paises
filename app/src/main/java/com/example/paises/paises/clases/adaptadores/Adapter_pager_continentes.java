package com.example.paises.paises.clases.adaptadores;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.paises.paises.clases.modelos.Modelo_pais;
import com.example.paises.paises.clases.modelos.Modelo_paises;
import com.example.paises.paises.vista.Framents.Continentes_paises_fragment;
import com.example.paises.paises.vista.Vista_paises;

import java.util.ArrayList;


public class Adapter_pager_continentes extends FragmentStateAdapter {
    Modelo_paises modelo_paises;
    Vista_paises vista_paises;
    ArrayList<Continentes_paises_fragment> fragments=new ArrayList<>();


    public Adapter_pager_continentes(@NonNull Vista_paises fragment, Modelo_paises modelo_paises) {

        super(fragment);
        this.vista_paises=fragment;
        this.modelo_paises = modelo_paises;
    }

    @NonNull

    @Override
    public Fragment createFragment(int position) {

            String[] continentes = modelo_paises.getContinentes();
            String continente = continentes[position];
            ArrayList<Modelo_pais> paises = modelo_paises.buscar_paises_continente(continentes[position]);
        Continentes_paises_fragment  fragment = Continentes_paises_fragment.newInstance(continente, paises);
            fragments.add(fragment);
            Log.e("creado fragmento",fragments.size()+"");


        return fragment;




    }



    @Override
    public int getItemCount() {
        return modelo_paises.getContinentes().length ;
    }
}
