package com.example.villamizar_villamizar;

import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.PropertyName;

import java.io.Serializable;

public class Producto implements Serializable {
    @Exclude
    public String getId() {
        return id;
    }

    @Exclude
    public void setId(String id) {
        this.id = id;
    }

    private String id;
    private String nombre;
    private Double precio;
    private String urlImagen;

    public Producto(String nombre, Double precio, String urlImagen) {
        this.nombre = nombre;
        this.precio = precio;
        this.urlImagen = urlImagen;
    }

    public Producto() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    @PropertyName("url_image")
    public String getUrlImagen() {
        return urlImagen;
    }

    @PropertyName("url_image")
    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }
}
