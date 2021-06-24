package com.example.paises.paises.clases.modelos;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.paises.MainActivity;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Modelo_paises extends Application {
    public  final String CONTINENTE_AFRICA="Africa",CONTINENTE_AMERICA="Americas",CONTINENTE_ASIA="Asia",CONTINENTE_EUROPA="Europe",CONTINENTE_OCEANIA="Oceania";
    MainActivity activity ;
    private RequestQueue request;
    private ArrayList<Modelo_pais> paises_auropa,paises_america,paises_asia,paises_africa,paises_oceania;
    private  String[] continentes={ CONTINENTE_AFRICA, CONTINENTE_AMERICA, CONTINENTE_ASIA, CONTINENTE_EUROPA, CONTINENTE_OCEANIA};
    private  int peticiones_terminadas=0;
    private  ArrayList<String>codigos_favoritos;

    public ArrayList<String> getCodigos_favoritos() {
        return codigos_favoritos;
    }

    public void setCodigos_favoritos(ArrayList<String> codigos_favoritos) {
        this.codigos_favoritos = codigos_favoritos;
    }

    public void buscar_paises(MainActivity activity) {
            this.activity= activity;
        request= Volley.newRequestQueue(activity);
        for (int i = 0; i < continentes.length; i++) {
            cargar_paises(continentes[i]);
        }
        this.codigos_favoritos= buscar_favoritos();

    }

    private void cargar_paises(String continente) {


        String url = "https://restcountries.eu/rest/v2/region/"+continente;
        Log.e("link",url);
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                guardar_paises(response,continente);
                peticiones_terminadas++;
                if(peticiones_terminadas==continentes.length){
                        activity.ir_paises();
                }else{
                    Log.e("peticiones terminadas",peticiones_terminadas+""+continentes.length);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e( "hay un error con e", continente);
                error.printStackTrace();
            }
        });
        request.add(jsonArrayRequest);

    }



    private void guardar_paises(JSONArray response, String continente) {
        ArrayList<Modelo_pais> paises= DeJson_aPaises(response);
        Log.e("cantidad de paises",paises.size()+"");
        switch (continente){
            case CONTINENTE_EUROPA:

                    paises_auropa=paises;

                break;

            case CONTINENTE_AFRICA:
                paises_africa= paises;
                break;
            case CONTINENTE_AMERICA:
                paises_america= paises;
                break;

            case CONTINENTE_ASIA:
                paises_asia=paises;
                break;

            case CONTINENTE_OCEANIA:
                paises_oceania=paises;
                break;

            default:
                Log.e("cargar paises","no hay una opcion correcta");
                break;


        }



    }

    private ArrayList<Modelo_pais> DeJson_aPaises(JSONArray response) {
        ArrayList<Modelo_pais> paises = new ArrayList<>();
        for (int i = 0; i < response.length(); i++) {

            try {
                Modelo_pais pais = DeJson_aPais(response.getJSONObject(i));
                paises.add(pais);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }


        return  paises;
    }

    private Modelo_pais DeJson_aPais(JSONObject jsonObject) {
     Modelo_pais pais= new Modelo_pais();
        Double latitud= 0.0;
        Double longitud=0.0;
        try {
            String nombre= jsonObject.getString("name");
            JSONArray coordenadas = jsonObject.getJSONArray("latlng");

                //Log.e("tamaño coor",coordenadas.getString(0)+"");
            if(coordenadas.length()>0){
                latitud= coordenadas.getDouble(0);
                longitud = coordenadas.getDouble(1);
            }else{
                Log.e("no tiene coordenadas",nombre);
            }





            String capital= jsonObject.getString("capital");
            String codigo=jsonObject.getString("numericCode");


            if(codigo=="null"){
            codigo="0";
            }

            String region = jsonObject.getString("region");
            String moneda = jsonObject.getJSONArray("currencies").getJSONObject(0).getString("name");
            String link_bandera= jsonObject.getString("flag");
            ArrayList<String> fronteras = new ArrayList<>();
            JSONArray json_fronteras=jsonObject.getJSONArray("borders");
            for (int i = 0; i < json_fronteras.length(); i++) {
                    String fontera = json_fronteras.getString(i);
                    fronteras.add(fontera);
            }
            JSONArray lenguaje_json  = jsonObject.getJSONArray("languages");
            String lenguaje="";
            for (int i = 0; i < lenguaje_json.length(); i++) {
                  lenguaje+=lenguaje_json.getJSONObject(i).getString("name")+",";
            }
            if(lenguaje.length()>0){
                lenguaje= lenguaje.substring(0, lenguaje.length() - 1);
            }


            long poblacion = jsonObject.getLong("population");

            pais.setCodigo(codigo);
            pais.setNombre(nombre);
            pais.setCapital(capital);
            pais.setLatitud(latitud);
            pais.setLongitud(longitud);
            pais.setLenguaje(lenguaje);
            pais.setRegion(region);
            pais.setMoneda(moneda);
            pais.setLink_bandera(link_bandera);
            pais.setPoblacion(poblacion);
            pais.setFronteras(fronteras);






        } catch (JSONException e) {
            e.printStackTrace();
        }

        return pais;

    }

    public String[] getContinentes() {
        return continentes;
    }

    public void setContinentes(String[] continentes) {
        this.continentes = continentes;
    }

    public  ArrayList<Modelo_pais>  buscar_paises_continente(String continente){
        switch (continente){
            case CONTINENTE_EUROPA:
               // aca esta null
                return paises_auropa;


            case CONTINENTE_AFRICA:
                // aca esta null
                return  paises_africa;

            case CONTINENTE_AMERICA:
                // aca esta null

                return  paises_america;


            case CONTINENTE_ASIA:
                // aca esta null
                return  paises_asia;


            case CONTINENTE_OCEANIA:
             // aca esta null
                return paises_oceania;


            default:
                Log.e("cargar paises","no hay una opcion correcta");
                return null;



        }


    }
    public void remover_codigo(String codigo) {
        for (int i = 0; i < codigos_favoritos.size(); i++) {
            String fav= codigos_favoritos.get(i)+"";
            if(fav.equals(codigo)){
                codigos_favoritos.remove(i);
                i--;
            }

        }
    }

    public boolean es_favorito(String codigo) {
        // codigos_favoritos.clear();
        //codigos_favoritos.add("33");
        for (int i = 0; i < codigos_favoritos.size(); i++) {
            String fav= codigos_favoritos.get(i)+"";
            if(fav.equals(codigo)){
                return true;
            }

        }

        return false;

    };

    private ArrayList<String> buscar_favoritos() {
        ArrayList<String> restultado;
        // aca se inicializa las preferences
        SharedPreferences preferences = activity.getSharedPreferences("favoritos", Context.MODE_PRIVATE);
        String json = preferences.getString("favoritos","[]");
        Log.e("jons a recibir",json);
        Gson gson= new Gson();
        restultado=gson.fromJson(json,ArrayList.class);
        Log.e("cantidad de favoritos",restultado.size()+"");
        return restultado;

    }

    public void  guardar_favoritos(){
        Gson gson= new Gson();
        String json =  gson.toJson(codigos_favoritos);
        SharedPreferences preferences =activity.getSharedPreferences("favoritos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= preferences.edit();
        Log.e("jons a guardar",json);
        editor.putString("favoritos",json);
        editor.commit();
    }
}
