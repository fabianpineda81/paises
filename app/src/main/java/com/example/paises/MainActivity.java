package com.example.paises;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.paises.paises.clases.modelos.Modelo_paises;
import com.example.paises.paises.vista.Vista_paises;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializar();
        //Modelo_paises modelo_paises= new Modelo_paises(this);
        Modelo_paises modelo_paises = (Modelo_paises) this.getApplication();
        modelo_paises.buscar_paises(this);


    }

    private void inicializar() {
        progressBar= findViewById(R.id.progressBar);


    }

    public  void ir_paises(){
        progressBar.setVisibility(View.INVISIBLE);
        Intent i = new Intent(this, Vista_paises.class);
        startActivity(i);



    }
}