package com.medac.bestipescook.model.noticia;

import com.medac.bestipescook.model.Imagen;

import java.time.LocalDateTime;
import java.util.Objects;

public class Noticia {

    private int idNoticia;
    private LocalDateTime fechaCreacionNoticia;
    private String tituloNoticia, subtituloNoticia, textoNoticia;
    private Imagen imagen;

    public Noticia() {
    }

    public Noticia(int idNoticia) {
        this.idNoticia = idNoticia;
    }

    public Noticia(int idNoticia, LocalDateTime fechaCreacionNoticia, String sTituloNoticia, String sSubtituloNoticia, String sTextoNoticia, Imagen imagen) {
        this.idNoticia = idNoticia;
        this.fechaCreacionNoticia = fechaCreacionNoticia;
        this.tituloNoticia = sTituloNoticia;
        this.subtituloNoticia = sSubtituloNoticia;
        this.textoNoticia = sTextoNoticia;
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

    public String getTituloNoticia() {
        return tituloNoticia;
    }

    public void setTituloNoticia(String tituloNoticia) {
        this.tituloNoticia = tituloNoticia;
    }

    public String getSubtituloNoticia() {
        return subtituloNoticia;
    }

    public void setSubtituloNoticia(String subtituloNoticia) {
        this.subtituloNoticia = subtituloNoticia;
    }

    public String getTextoNoticia() {
        return textoNoticia;
    }

    public void setTextoNoticia(String textoNoticia) {
        this.textoNoticia = textoNoticia;
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
        return getIdNoticia() == noticia.getIdNoticia() && getTituloNoticia().equals(noticia.getTituloNoticia());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdNoticia(), getTituloNoticia());
    }
}
