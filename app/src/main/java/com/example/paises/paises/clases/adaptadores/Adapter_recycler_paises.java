package com.example.paises.paises.clases.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.paises.R;
import com.example.paises.mapa.MapsActivity;
import com.example.paises.paises.clases.modelos.Modelo_pais;
import com.example.paises.paises.clases.modelos.Modelo_paises;
import com.example.paises.paises.vista.Framents.Continentes_paises_fragment;
import com.example.paises.paises.vista.Vista_pais;
import com.google.gson.Gson;

import java.util.ArrayList;

public class Adapter_recycler_paises extends RecyclerView.Adapter<Adapter_recycler_paises.Pais_view_holer> implements Filterable {
    private ArrayList<Modelo_pais> paises ;
    private ArrayList<Modelo_pais> todos_paises;
    private Continentes_paises_fragment fragment;
    private Modelo_paises modelo_paises;




    public Adapter_recycler_paises(ArrayList<Modelo_pais> paises ,Continentes_paises_fragment fragment) {
        this.paises = paises;
        this.todos_paises= paises;
        this.fragment= fragment;
        this.modelo_paises=(Modelo_paises) fragment.getActivity().getApplication();


    }



    @Override
    public Adapter_recycler_paises.Pais_view_holer onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_paises,parent,false);
        return new Pais_view_holer(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  Adapter_recycler_paises.Pais_view_holer holder, int position) {
        holder.asignar_datos(paises.get(position));


    }

    @Override
    public int getItemCount() {
        return paises.size();
    }



    @Override
    public Filter getFilter() {
        return lista_filtrada;
    }
    // este viene a hacer el filtro
    private Filter lista_filtrada=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<Modelo_pais> resultado =new ArrayList<>();
            if(charSequence==null ||charSequence.length()==0){
                    resultado.addAll(todos_paises);

            }else{
                String palabra =charSequence.toString().toLowerCase().trim();
                    for(Modelo_pais pais :todos_paises){
                        String nombre= pais.getNombre().toLowerCase();
                        String capital=pais.getCapital().toLowerCase();
                        if(nombre.contains(palabra)|| capital.contains(palabra)){
                            resultado.add(pais);
                        }
                    }
            }

            FilterResults results=new FilterResults();
            results.values=resultado;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            paises=(ArrayList<Modelo_pais>)filterResults.values;
            notifyDataSetChanged();
        }
    };








    public class Pais_view_holer extends RecyclerView.ViewHolder {
        TextView nombre,capital;
        ImageView img_mapa ;
        CardView card_pais;
        CheckBox favorito;

        public Pais_view_holer(@NonNull  View itemView) {
            super(itemView);
           nombre= itemView.findViewById(R.id.nombre_pais_recycler);
           capital= itemView.findViewById(R.id.capital_pais_recycler);
           img_mapa= itemView.findViewById(R.id.img_map);
           card_pais=itemView.findViewById(R.id.pais_card_view);
           favorito= itemView.findViewById(R.id.favorito_card);
        }

        public void asignar_datos(Modelo_pais pais) {
            nombre.setText(pais.getNombre());
            capital.setText(pais.getCapital());

            favorito.setChecked(modelo_paises.es_favorito(pais.getCodigo()));
            favorito.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!favorito.isChecked()){
                        Toast.makeText(fragment.getActivity(),"eliminado de favoritos",Toast.LENGTH_SHORT).show();
                        favorito.setChecked(false);
                      modelo_paises.remover_codigo(pais.getCodigo());
                      modelo_paises. guardar_favoritos();
                    }else{
                        Toast.makeText(fragment.getActivity(),"agregado a favoritos",Toast.LENGTH_SHORT).show();
                        favorito.setChecked(true);
                       modelo_paises.getCodigos_favoritos().add(pais.getCodigo());
                       modelo_paises.guardar_favoritos();
                    }
                }
            });

            card_pais.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(fragment.getContext(), Vista_pais.class);
                    pais.setFavorito(favorito.isChecked());
                    i.putExtra("pais",pais);
                    fragment.startActivity(i);
                }
            });

            img_mapa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(fragment.getContext(), MapsActivity.class);
                    i.putExtra("pais",pais);
                    fragment.getActivity().startActivity(i);
                }
            });

        }
    }


}
