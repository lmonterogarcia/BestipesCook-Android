package com.medac.bestipescook.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Reto {

    private int iIdReto;
    private LocalDateTime fechaCreacionReto, fechaFinalizacionReto;
    private String sTituloReto, sSubtituloReto, sTextoReto;
    private Imagen imagen;

    public Reto() {
    }

    public Reto(int idReto) {
        this.iIdReto = idReto;
    }

    public Reto(int idReto, LocalDateTime fechaCreacionReto, LocalDateTime fechaFinalizacionReto, String sTituloReto, String sSubtituloReto, String sTextoReto, Imagen imagen) {
        this.iIdReto = idReto;
        this.fechaCreacionReto = fechaCreacionReto;
        this.fechaFinalizacionReto = fechaFinalizacionReto;
        this.sTituloReto = sTituloReto;
        this.sSubtituloReto = sSubtituloReto;
        this.sTextoReto = sTextoReto;
        this.imagen = imagen;
    }

    public int getiIdReto() {
        return iIdReto;
    }

    public void setiIdReto(int iIdReto) {
        this.iIdReto = iIdReto;
    }

    public LocalDateTime getFechaCreacionReto() {
        return fechaCreacionReto;
    }

    public void setFechaCreacionReto(LocalDateTime fechaCreacionReto) {
        this.fechaCreacionReto = fechaCreacionReto;
    }

    public String getsTituloReto() {
        return sTituloReto;
    }

    public void setsTituloReto(String sTituloReto) {
        this.sTituloReto = sTituloReto;
    }

    public String getsSubtituloReto() {
        return sSubtituloReto;
    }

    public void setsSubtituloReto(String sSubtituloReto) {
        this.sSubtituloReto = sSubtituloReto;
    }

    public String getsTextoReto() {
        return sTextoReto;
    }

    public void setsTextoReto(String sTextoReto) {
        this.sTextoReto = sTextoReto;
    }

    public Imagen getImagen() {
        return imagen;
    }

    public void setiImagen(Imagen imagen) {
        this.imagen = imagen;
    }

    public LocalDateTime getFechaFinalizacionReto() {
        return fechaFinalizacionReto;
    }

    public void setFechaFinalizacionReto(LocalDateTime fechaFinalizacionReto) {
        this.fechaFinalizacionReto = fechaFinalizacionReto;
    }

    public void setImagen(Imagen imagen) {
        this.imagen = imagen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reto)) return false;
        Reto Reto = (Reto) o;
        return getiIdReto() == Reto.getiIdReto() && getsTituloReto().equals(Reto.getsTituloReto());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getiIdReto(), getsTituloReto());
    }

}
