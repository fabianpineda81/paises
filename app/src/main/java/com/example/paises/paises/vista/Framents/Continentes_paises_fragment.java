package com.example.paises.paises.vista.Framents;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.paises.R;
import com.example.paises.paises.clases.adaptadores.Adapter_recycler_paises;
import com.example.paises.paises.clases.modelos.Modelo_pais;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Continentes_paises_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Continentes_paises_fragment extends Fragment {
    private String titulo;
    private RecyclerView  recycler_paises;
    private  ArrayList<Modelo_pais> paises;
    Adapter_recycler_paises adapter_recycler_paises;
    SearchView searchView;


    public Continentes_paises_fragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static Continentes_paises_fragment newInstance(String titulo,ArrayList<Modelo_pais> paises) {
        Continentes_paises_fragment fragment = new Continentes_paises_fragment();
        Bundle args = new Bundle();
        args.putString("titulo", titulo);
        args.putParcelableArrayList("paises",paises);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            titulo = getArguments().getString("titulo");
            paises = getArguments().getParcelableArrayList("paises");



        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_continentes_paises_fragment, container, false);
        inicializar(view);

        //TextView textView =   view.findViewById(R.id.numero_pager);
      //  textView.setText(titulo);

        return view;
    }

    private void inicializar(View view) {
        recycler_paises= view.findViewById(R.id.recycler_paises);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);

         adapter_recycler_paises= new Adapter_recycler_paises(paises,this);


        recycler_paises.setLayoutManager(linearLayoutManager);
        recycler_paises.setAdapter(adapter_recycler_paises);
        searchView=view.findViewById(R.id.buscador);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.e("palabra",newText);
                adapter_recycler_paises.getFilter().filter(newText);
                return false;
            }
        });






    }

    public  void filtrar_adapter(String palabra ){
        adapter_recycler_paises.getFilter().filter(palabra);
    }
}