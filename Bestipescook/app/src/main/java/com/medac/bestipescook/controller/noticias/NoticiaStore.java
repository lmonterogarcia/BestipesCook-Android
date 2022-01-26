package com.medac.bestipescook.controller.noticias;

import com.medac.bestipescook.model.Noticia;

import java.util.ArrayList;

public class NoticiaStore {

    public static ArrayList<Noticia> lstNoticias = new ArrayList<Noticia>();
    public static int iNoticiaSeleccionada;

    /**
     * @param noticia
     * Introduce una noticia en un lugar ordenando por idNoticia
     */
    public static void aniadirNoticia(Noticia noticia){
        if (lstNoticias.size() > 1) {
            int iPosicion = 0;
            boolean noticiaAniadida = false;
            do {
                if (noticia.getIdNoticia() < lstNoticias.get(iPosicion).getIdNoticia()){
                    lstNoticias.add(iPosicion, noticia);
                    noticiaAniadida = true;
                }
                iPosicion++;
            }while(!noticiaAniadida && iPosicion < lstNoticias.size());
            if (!noticiaAniadida){
                lstNoticias.add(noticia);
            }
        } else if (lstNoticias.size() == 1 && lstNoticias.get(0).getIdNoticia() > noticia.getIdNoticia()) {
            lstNoticias.add(0, noticia);
        } else {
            lstNoticias.add(noticia);
        }
        frNoticias.adaptador.notifyDataSetChanged();
    }

}
