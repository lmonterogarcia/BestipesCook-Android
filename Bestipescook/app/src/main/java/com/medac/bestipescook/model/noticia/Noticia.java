package com.medac.bestipescook.model.noticia;

import com.medac.bestipescook.model.Imagen;

import java.time.LocalDateTime;
import java.util.Objects;

public class Noticia {

    private int idNoticia;
    private LocalDateTime fechaCreacionNoticia;
    private String sTituloNoticia, sSubtituloNoticia, sTextoNoticia;
    private Imagen imagen;

    public Noticia() {
    }

    public Noticia(int idNoticia) {
        this.idNoticia = idNoticia;
    }

    public Noticia(int idNoticia, LocalDateTime fechaCreacionNoticia, String sTituloNoticia, String sSubtituloNoticia, String sTextoNoticia, Imagen imagen) {
        this.idNoticia = idNoticia;
        this.fechaCreacionNoticia = fechaCreacionNoticia;
        this.sTituloNoticia = sTituloNoticia;
        this.sSubtituloNoticia = sSubtituloNoticia;
        this.sTextoNoticia = sTextoNoticia;
        this.imagen = imagen;
    }

    public int getIdNoticia() {
        return idNoticia;
    }

    public void setIdNoticia(int idNoticia) {
        this.idNoticia = idNoticia;
    }

    public LocalDateTime getFechaCreacionNoticia() {
        return fechaCreacionNoticia;
    }

    public void setFechaCreacionNoticia(LocalDateTime fechaCreacionNoticia) {
        this.fechaCreacionNoticia = fechaCreacionNoticia;
    }

    public String getsTituloNoticia() {
        return sTituloNoticia;
    }

    public void setsTituloNoticia(String sTituloNoticia) {
        this.sTituloNoticia = sTituloNoticia;
    }

    public String getsSubtituloNoticia() {
        return sSubtituloNoticia;
    }

    public void setsSubtituloNoticia(String sSubtituloNoticia) {
        this.sSubtituloNoticia = sSubtituloNoticia;
    }

    public String getsTextoNoticia() {
        return sTextoNoticia;
    }

    public void setsTextoNoticia(String sTextoNoticia) {
        this.sTextoNoticia = sTextoNoticia;
    }

    public Imagen getImagen() {
        return imagen;
    }

    public void setiImagen(Imagen imagen) {
        this.imagen = imagen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Noticia)) return false;
        Noticia noticia = (Noticia) o;
        return getIdNoticia() == noticia.getIdNoticia() && getsTituloNoticia().equals(noticia.getsTituloNoticia());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdNoticia(), getsTituloNoticia());
    }
}
