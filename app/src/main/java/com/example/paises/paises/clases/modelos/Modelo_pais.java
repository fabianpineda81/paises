package com.example.paises.paises.clases.modelos;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.paises.paises.vista.Vista_pais;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Modelo_pais implements Parcelable {
    //https://restcountries.eu/rest/v2/alpha/DZA
    private  Double latitud,longitud;
    private  int peticiones_completadas=0;
    private  String nombre,capital,lenguaje, region,moneda,link_bandera,codigo ;
    private ArrayList<String> fronteras;
    private Long poblacion;
    private  boolean favorito= false;
    private RequestQueue request;
    private  ArrayList<String> fronteras_completas;
    public Modelo_pais() {
    }

    protected Modelo_pais(Parcel in) {
        if (in.readByte() == 0) {
            latitud = null;
        } else {
            latitud = in.readDouble();
        }
        if (in.readByte() == 0) {
            longitud = null;
        } else {
            longitud = in.readDouble();
        }
        peticiones_completadas = in.readInt();
        nombre = in.readString();
        capital = in.readString();
        lenguaje = in.readString();
        region = in.readString();
        moneda = in.readString();
        link_bandera = in.readString();
        codigo = in.readString();
        fronteras = in.createStringArrayList();
        if (in.readByte() == 0) {
            poblacion = null;
        } else {
            poblacion = in.readLong();
        }
        favorito = in.readByte() != 0;
        fronteras_completas = in.createStringArrayList();
    }

    public static final Creator<Modelo_pais> CREATOR = new Creator<Modelo_pais>() {
        @Override
        public Modelo_pais createFromParcel(Parcel in) {
            return new Modelo_pais(in);
        }

        @Override
        public Modelo_pais[] newArray(int size) {
            return new Modelo_pais[size];
        }
    };

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public int getPeticiones_completadas() {
        return peticiones_completadas;
    }

    public void setPeticiones_completadas(int peticiones_completadas) {
        this.peticiones_completadas = peticiones_completadas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getLink_bandera() {
        return link_bandera;
    }

    public void setLink_bandera(String link_bandera) {
        this.link_bandera = link_bandera;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public ArrayList<String> getFronteras() {
        return fronteras;
    }

    public void setFronteras(ArrayList<String> fronteras) {
        this.fronteras = fronteras;
    }

    public Long getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(Long poblacion) {
        this.poblacion = poblacion;
    }

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }

    public RequestQueue getRequest() {
        return request;
    }

    public void setRequest(RequestQueue request) {
        this.request = request;
    }

    public ArrayList<String> getFronteras_completas() {
        return fronteras_completas;
    }

    public void setFronteras_completas(ArrayList<String> fronteras_completas) {
        this.fronteras_completas = fronteras_completas;
    }

    @Override
    public int describeContents() {
        return CONTENTS_FILE_DESCRIPTOR;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (latitud == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(latitud);
        }
        if (longitud == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(longitud);
        }
        parcel.writeInt(peticiones_completadas);
        parcel.writeString(nombre);
        parcel.writeString(capital);
        parcel.writeString(lenguaje);
        parcel.writeString(region);
        parcel.writeString(moneda);
        parcel.writeString(link_bandera);
        parcel.writeString(codigo);
        parcel.writeStringList(fronteras);
        if (poblacion == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(poblacion);
        }
        parcel.writeByte((byte) (favorito ? 1 : 0));
        parcel.writeStringList(fronteras_completas);
    }


























    public  void nombres_completo_fronteras(ArrayList<String> front, Vista_pais vista_pais){
         request= Volley.newRequestQueue(vista_pais);
         fronteras_completas= new ArrayList<>();
        for (String frontera:front ) {
                buscar_frontera_completa(frontera,front.size(),vista_pais);
        }
    }

    private void buscar_frontera_completa(String frontera,int limite,Vista_pais  vista_pais) {
        String url = "https://restcountries.eu/rest/v2/alpha/"+frontera;
        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String nombre = response.getString("name");
                    fronteras_completas.add(nombre);

                    if(limite==fronteras_completas.size()){
                        vista_pais.pintar_fronteras(fronteras_completas);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("errro nombre_comple","error");
                error.printStackTrace();
            }
        });
        request.add(jsonObjectRequest);
    }
}
