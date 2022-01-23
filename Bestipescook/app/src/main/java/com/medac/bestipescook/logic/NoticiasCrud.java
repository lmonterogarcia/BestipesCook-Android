package com.medac.bestipescook.logic;


import android.util.Log;

import com.medac.bestipescook.controller.noticias.NoticiaStore;
import com.medac.bestipescook.model.Imagen;
import com.medac.bestipescook.model.noticia.INoticia;
import com.medac.bestipescook.model.noticia.Noticia;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class NoticiasCrud implements IHostingData, INoticia {

    public static void rellenarLstNoticias(List<Map<String, Object>> lstObjetos) {
        lstObjetos.forEach(noticia ->{
            NoticiaStore.lstNoticias.add(new Noticia(
                    Integer.parseInt(noticia.get("idNoticia").toString()),
                    LocalDateTime.parse(noticia.get("fechaCreacionNoticia").toString(), INoticia.dateTimeformatter),
                    noticia.get("tituloNoticia").toString(),
                    noticia.get("subtituloNoticia").toString(),
                    noticia.get("textoNoticia").toString(),
                    new Imagen(1,LocalDateTime.parse(noticia.get("fechaCreacionNoticia").toString(), INoticia.dateTimeformatter),"fotovacia.png") // ESTO   ES UNA PRUEBA
            ));
            Log.d("Pruebas",noticia.get("idNoticia").toString());
        });
    }

}
