package com.example.paises.paises.vista;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.paises.R;
import com.example.paises.paises.clases.modelos.Modelo_pais;
import com.example.paises.paises.clases.modelos.Modelo_paises;

import java.util.ArrayList;

public class Vista_pais extends AppCompatActivity {

    Toolbar toolbar;
    Modelo_pais pais;
    TextView nombre,region,poblacion,capital,moneda,lenguaje,fron;
    ImageView bandera;
    CheckBox favorito;
    ProgressBar progressBar_bandera;
    Modelo_paises modelo_paises;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_pais);
        pais=(Modelo_pais) getIntent().getParcelableExtra("pais");
        modelo_paises=(Modelo_paises) getApplication();
        inicializar();

    }

    private void inicializar() {
        toolbar= findViewById(R.id.toolbar);
        showToolbar();
          pais=(Modelo_pais) getIntent().getParcelableExtra("pais");

            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setTitle("Informacion detallada");
            nombre= findViewById(R.id.vista_pais_nombre);
            bandera= findViewById(R.id.vista_pais_bandera);
            poblacion=findViewById(R.id.vista_pais_poblacion);
            capital=findViewById(R.id.vista_pais_capital);
            lenguaje=findViewById(R.id.vista_pais_lenguaje);
            fron=findViewById(R.id.vista_pais_frontera);
            moneda=findViewById(R.id.vista_pais_moneda);

            region=findViewById(R.id.vista_pais_region);
            progressBar_bandera= findViewById(R.id.progressBar_bandera);
            favorito=findViewById(R.id.vista_pais_favorito);
            pintar_datos();
        favorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!favorito.isChecked()){
                    Toast.makeText(Vista_pais.this,"eliminado de favoritos",Toast.LENGTH_SHORT).show();
                    favorito.setChecked(false);
                    modelo_paises.remover_codigo(pais.getCodigo());
                    modelo_paises. guardar_favoritos();
                }else{
                    Toast.makeText(Vista_pais.this,"agregado a favoritos",Toast.LENGTH_SHORT).show();
                    favorito.setChecked(true);
                    modelo_paises.getCodigos_favoritos().add(pais.getCodigo());
                    modelo_paises.guardar_favoritos();
                }
            }
        });


        }

    private void pintar_datos() {

        favorito.setChecked(pais.isFavorito());
            nombre.setText(pais.getNombre());
            favorito.setSelected(pais.isFavorito());
            poblacion.setText(pais.getPoblacion()+"");
            capital.setText(pais.getCapital());
            lenguaje.setText(pais.getLenguaje());
            moneda.setText(pais.getMoneda());
            region.setText(pais.getRegion());
            pintar_fronteras(pais.getFronteras());
            pais.nombres_completo_fronteras(pais.getFronteras(),this);

           montar_imagen();
    }

    public void pintar_fronteras(ArrayList<String> fronteras) {
        String resultado = "";
        for (String fron: fronteras) {
                resultado+=fron+",";
        }
        //if(resultado.length()>0){
          //  resultado= resultado.substring(0, lenguaje.length() - 1);
        //}
        fron.setText(resultado);



    }

    private  void  montar_imagen (){



     Glide.with(this).load(pais.getLink_bandera()).addListener(new RequestListener<Drawable>() {
         @Override
         public boolean onLoadFailed(@Nullable  GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
             return false;
         }

         @Override
         public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
             progressBar_bandera.setVisibility(View.INVISIBLE);
             return false;
         }
     }).into(bandera);

}
    public  void showToolbar() {
        //  aca declaramos una variable toolbar y traemos el tooblar de view


        //  aca enviamos el soporte el toolbar para asi personalizarlo
        setSupportActionBar(toolbar);

        //se le pone el titulo


        //se le pone el boton de regreso (hay que configurar la jerarquia )
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        toolbar.setTitle("Informacion detallada");


    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
}


