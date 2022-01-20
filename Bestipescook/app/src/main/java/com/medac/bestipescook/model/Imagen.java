package com.medac.bestipescook.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Imagen {

    private int iIdIamgen;
    private LocalDateTime fechaCreacionImagen;
    private String sRutaRelativaImagen;

    public Imagen(){
    }

    public Imagen(int iIdIamgen) {
        this.iIdIamgen = iIdIamgen;
    }

    public Imagen(int iIdIamgen, LocalDateTime fechaCreacionImagen, String sRutaRelativaImagen) {
        this.iIdIamgen = iIdIamgen;
        this.fechaCreacionImagen = fechaCreacionImagen;
        this.sRutaRelativaImagen = sRutaRelativaImagen;
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

    public String getsRutaRelativaImagen() {
        return sRutaRelativaImagen;
    }

    public void setsRutaRelativaImagen(String sRutaRelativaImagen) {
        this.sRutaRelativaImagen = sRutaRelativaImagen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Imagen)) return false;
        Imagen imagen = (Imagen) o;
        return getiIdIamgen() == imagen.getiIdIamgen() && getsRutaRelativaImagen().equals(imagen.getsRutaRelativaImagen());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getiIdIamgen(), getsRutaRelativaImagen());
    }
}
