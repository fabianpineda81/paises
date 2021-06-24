package com.example.paises.paises.vista;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.paises.R;
import com.example.paises.paises.clases.adaptadores.Adapter_pager_continentes;
import com.example.paises.paises.clases.modelos.Modelo_paises;

public class Vista_paises extends AppCompatActivity {
    ViewPager2 pager ;
    LinearLayout linearLayout_puntos;
    private TextView[] puntos;
    TextView nombre_continente;
    Modelo_paises modelo_paises;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_paises);
        pager=findViewById(R.id.view_pager_continentes);
         modelo_paises = (Modelo_paises)  getApplication();
        Adapter_pager_continentes adapter_pager_continentes= new Adapter_pager_continentes(this,modelo_paises);
        pager.setAdapter(adapter_pager_continentes);
        inicializar();





    }

    private void inicializar() {
    linearLayout_puntos=findViewById(R.id.contenedor_puntos);
    nombre_continente=findViewById(R.id.nombre_continente);
        agregar_indicador_puntos(0);

        // se activa cuando se cambia de pagina
     ViewPager2.OnPageChangeCallback onPageChangeCallback= new ViewPager2.OnPageChangeCallback() {
         @Override
         public void onPageSelected(int position) {
             super.onPageSelected(position);
             agregar_indicador_puntos(position);
         }
     };
       pager.registerOnPageChangeCallback(onPageChangeCallback);

        //pager.setCurrentItem(0,false);


    }

    private void agregar_indicador_puntos(int i) {

        linearLayout_puntos.removeAllViews();
        puntos= new TextView[5];
        Log.e("posicion punto",i+"" );
        for (int j = 0; j < puntos.length; j++) {
            puntos[j]=new TextView(this);
            puntos[j].setText(Html.fromHtml("&#8226"));
            puntos[j].setTextSize(45);
            puntos[j].setTextColor(getColor(R.color.trasparente));
            linearLayout_puntos.addView(puntos[j]);
        }

        puntos[i].setTextColor(getColor(R.color.design_default_color_secondary_variant));
        nombre_continente.setText(modelo_paises.getContinentes()[i]);
    }

    @Override
    public void onBackPressed() {

    }
}