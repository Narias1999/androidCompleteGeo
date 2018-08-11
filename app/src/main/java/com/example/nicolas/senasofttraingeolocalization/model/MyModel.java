package com.example.nicolas.senasofttraingeolocalization.model;

import java.util.List;

public class MyModel {
    private String id_sitio, nombre, desc_corta, ubicacion, desc_larga, lat, lon,
            imagen, cat;


    public MyModel(String id_sitio, String nombre,String desc_corta, String ubicacion, String desc_larga,String lat,String lon,
                   String  imagen,String cat){
        this.id_sitio = id_sitio;
        this.nombre = nombre;
        this.desc_corta = desc_corta;
        this.ubicacion = ubicacion;
        this.desc_larga = desc_larga;
        this.lat = lat;
        this.lon = lon;
        this.imagen = imagen;
        this.cat = cat;
    }

    public String getId_sitio() {
        return id_sitio;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDesc_corta() {
        return desc_corta;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public String getDesc_larga() {
        return desc_larga;
    }

    public String getLat() {
        return lat;
    }

    public String getLon() {
        return lon;
    }

    public String getImagen() {
        return imagen;
    }

    public String getCat() {
        return cat;
    }
}
