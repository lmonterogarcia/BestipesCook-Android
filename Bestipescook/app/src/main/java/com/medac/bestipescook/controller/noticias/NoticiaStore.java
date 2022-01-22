package com.medac.bestipescook.controller.noticias;

import com.medac.bestipescook.model.noticia.Noticia;

import java.util.ArrayList;
import java.util.List;

public class NoticiaStore {

    public static ArrayList<Noticia> lstNoticias;

    static {
        lstNoticias = new ArrayList<>();
    }

    public static int iNoticiaSeleccionada;

}
