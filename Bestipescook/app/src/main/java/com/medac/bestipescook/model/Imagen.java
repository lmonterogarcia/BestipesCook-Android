package com.medac.bestipescook.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Imagen {

    private int iIdIamgen;
    private LocalDateTime fechaCreacionImagen;
    private String sRutaUrlImagen;

    public Imagen(){
    }

    public Imagen(int iIdIamgen) {
        this.iIdIamgen = iIdIamgen;
    }

    public Imagen(int iIdIamgen, LocalDateTime fechaCreacionImagen, String sRutaRelativaImagen) {
        this.iIdIamgen = iIdIamgen;
        this.fechaCreacionImagen = fechaCreacionImagen;
        this.sRutaUrlImagen = sRutaRelativaImagen;
    }

    public int getiIdIamgen() {
        return iIdIamgen;
    }

    public void setiIdIamgen(int iIdIamgen) {
        this.iIdIamgen = iIdIamgen;
    }

    public LocalDateTime getFechaCreacionImagen() {
        return fechaCreacionImagen;
    }

    public void setFechaCreacionImagen(LocalDateTime fechaCreacionImagen) {
        this.fechaCreacionImagen = fechaCreacionImagen;
    }

    public String getsRutaUrlImagen() {
        return sRutaUrlImagen;
    }

    public void setsRutaUrlImagen(String sRutaUrlImagen) {
        this.sRutaUrlImagen = sRutaUrlImagen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Imagen)) return false;
        Imagen imagen = (Imagen) o;
        return getiIdIamgen() == imagen.getiIdIamgen() && getsRutaUrlImagen().equals(imagen.getsRutaUrlImagen());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getiIdIamgen(), getsRutaUrlImagen());
    }
}
